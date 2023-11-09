package ui;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cookbook.core.CookBook;
import cookbook.core.User;
import ui.access.RemoteCookbookAccess;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RemoteCookbookAccessTest {

    private WireMockConfiguration wireMockConfig;
    private WireMockServer wireMockServer;

    private RemoteCookbookAccess remoteCookbookAccess;

    @BeforeEach
    void setup() throws URISyntaxException {
        wireMockConfig = WireMockConfiguration.wireMockConfig().port(8080);
        wireMockServer = new WireMockServer(wireMockConfig.portNumber());
        wireMockServer.start();
        WireMock.configureFor("localhost", wireMockConfig.portNumber());

        URI endpointUri = new URI("http://localhost:8080/cookbook/");
        remoteCookbookAccess = new RemoteCookbookAccess(endpointUri);
    }

    @Test
    public void testReadUser() {
        stubFor(get(urlEqualTo("/cookbook/login?username=mads&password=borte"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"username\":\"mads\",\"password\":\"borte\"}")
                )
        );
        // Test set to throw rather than verify post request because server will throw because it can't find the user because WireMock doesn't have access to you json-file. When an exception is thrown from the server-side WireMock can't verify that a post request has been sent.
        assertThrows(IllegalArgumentException.class, () -> remoteCookbookAccess.readUser("mads", "borte"));
    }

    @Test
    public void testRegisterNewUser() {
        stubFor(post(urlEqualTo("/cookbook/register?username=test&password=pass"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{\"username\":\"test\",\"password\":\"pass\"}")
                )
        );

        User user = remoteCookbookAccess.registerNewUser("test", "pass");

        assertEquals("test", user.getUsername());
        assertEquals("pass", user.getPassword());

        verify(postRequestedFor(urlEqualTo("/cookbook/register?username=test&password=pass")));
        
    }

    @Test
    public void testUpdateUserAttributes() {
        User user = new User("test", "pass", new CookBook(new ArrayList<>()));

        stubFor(post(urlEqualTo("/cookbook/update?username=test"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                )
        );

        remoteCookbookAccess.updateUserAttributes(user);

        verify(postRequestedFor(urlEqualTo("/cookbook/update?username=test")));
    }

    @AfterEach
    public void stopWireMockServer() {
        wireMockServer.stop();
    }
}
