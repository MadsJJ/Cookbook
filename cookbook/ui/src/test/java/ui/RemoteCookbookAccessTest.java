package ui;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import cookbook.core.CookBook;
import cookbook.core.User;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.access.RemoteCookbookAccess;

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

    URI endpointUri = new URI("http://localhost:8080/cookbook");
    remoteCookbookAccess = new RemoteCookbookAccess(endpointUri);
  }

  @Test
  public void testReadUserLoginSuccess() {
    stubFor(post(urlEqualTo("/cookbook/login")).willReturn(
        aResponse().withStatus(200).withHeader("Content-Type", "application/json").withBody(
            "{\"username\":\"test\",\"password\":\"test\",\"cookBook\":{\"recipes\":[]}}")));

    User user = assertDoesNotThrow(() -> remoteCookbookAccess.readUser("test", "test"));

    assertEquals("test", user.getUsername());
    assertEquals("test", user.getPassword());
    assertNotNull(user.getCookBook());
    assertEquals(0, user.getCookBook().getRecipes().size());

    verify(postRequestedFor(urlEqualTo("/cookbook/login")));
  }

  @Test
  public void testReadUserCrash() {
    stubFor(post(urlEqualTo("/cookbook/login"))
        .willReturn(aResponse().withStatus(418).withHeader("Content-Type", "application/json")));

    assertThrows(RuntimeException.class, () -> remoteCookbookAccess.readUser("test", "test"));

    verify(postRequestedFor(urlEqualTo("/cookbook/login")));
  }


  @Test
  public void testRegisterNewUserSuccess() {
    stubFor(post(urlEqualTo("/cookbook/register"))
        .willReturn(aResponse().withStatus(200).withHeader("Content-Type", "application/json")
            .withBody("{\"username\":\"test\",\"password\":\"pass\"}")));

    User user = remoteCookbookAccess.registerNewUser("test", "pass");

    assertEquals("test", user.getUsername());
    assertEquals("pass", user.getPassword());

    verify(postRequestedFor(urlEqualTo("/cookbook/register")));
  }

  @Test
  public void testRegisterNewUserExistingUsername() {
    stubFor(post(urlEqualTo("/cookbook/register"))
        .willReturn(aResponse().withStatus(409).withHeader("Content-Type", "application/json")));

    assertThrows(IllegalArgumentException.class,
        () -> remoteCookbookAccess.registerNewUser("test", "test"), "Username already exists");

    verify(postRequestedFor(urlEqualTo("/cookbook/register")));
  }

  @Test
  public void testRegisterNewUserInvalidFormat() {
    stubFor(post(urlEqualTo("/cookbook/register"))
        .willReturn(aResponse().withStatus(400).withHeader("Content-Type", "application/json")));

    assertThrows(IllegalArgumentException.class,
        () -> remoteCookbookAccess.registerNewUser("test", "test"), User.outputSignup);

    verify(postRequestedFor(urlEqualTo("/cookbook/register")));
  }

  @Test
  public void testRegisterNewUserUnexpected() {
    stubFor(post(urlEqualTo("/cookbook/register"))
        .willReturn(aResponse().withStatus(418).withHeader("Content-Type", "application/json")));

    assertThrows(RuntimeException.class,
        () -> remoteCookbookAccess.registerNewUser("test", "test"));

    verify(postRequestedFor(urlEqualTo("/cookbook/register")));
  }

  @Test
  public void testUpdateUserAttributes() {
    User user = new User("test", "pass", new CookBook(new ArrayList<>()));

    stubFor(post(urlEqualTo("/cookbook/update"))
        .willReturn(aResponse().withStatus(200).withHeader("Content-Type", "application/json")));

    remoteCookbookAccess.updateUserAttributes(user);

    verify(postRequestedFor(urlEqualTo("/cookbook/update")));
  }

  @Test
  public void testUpdateUserAttributesError() {
    User user = new User("test", "pass", new CookBook(new ArrayList<>()));

    stubFor(post(urlEqualTo("/cookbook/update"))
        .willReturn(aResponse().withStatus(500).withHeader("Content-Type", "application/json")));

    assertThrows(RuntimeException.class, () -> remoteCookbookAccess.updateUserAttributes(user));

    verify(postRequestedFor(urlEqualTo("/cookbook/update")));
  }

  @AfterEach
  public void stopWireMockServer() {
    wireMockServer.stop();
  }
}
