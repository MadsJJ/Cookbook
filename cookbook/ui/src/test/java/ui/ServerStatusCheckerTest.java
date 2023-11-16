package ui;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.net.URISyntaxException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import ui.access.CookbookAccess;
import ui.access.LocalCookbookAccess;
import ui.access.RemoteCookbookAccess;
import ui.access.ServerStatusChecker;

public class ServerStatusCheckerTest {
  private WireMockConfiguration wireMockConfig;
  private WireMockServer wireMockServer;

  @BeforeEach
  void setup() throws URISyntaxException {
    wireMockConfig = WireMockConfiguration.wireMockConfig().port(8080);
    wireMockServer = new WireMockServer(wireMockConfig.portNumber());
    wireMockServer.start();
    WireMock.configureFor("localhost", wireMockConfig.portNumber());



  }

  @AfterEach
  public void stopWireMockServer() {
    wireMockServer.stop();
  }

  @Test
  public void testserverStatusSuccess() {
    stubFor(get(urlEqualTo("/cookbook/"))
        .willReturn(aResponse().withStatus(200).withHeader("Content-Type", "application/json")));

    assertTrue(ServerStatusChecker.serverStatus());



    verify(getRequestedFor(urlEqualTo("/cookbook/")));
  }

  @Test
  public void testserverStatusNoConnection() {
    stubFor(get(urlEqualTo("/cookbook/")).willReturn(aResponse().withStatus(418)));

    assertFalse(ServerStatusChecker.serverStatus());



    verify(getRequestedFor(urlEqualTo("/cookbook/")));
  }

  @Test
  public void testSetAccessTypeLocal() {
    stubFor(get(urlEqualTo("/cookbook/")).willReturn(aResponse().withStatus(418))); // Simulating
                                                                                    // server not
                                                                                    // available

    CookbookAccess accessType = ServerStatusChecker.setAccessType("/path/to/local/file");

    assertNotNull(accessType);
    assertTrue(accessType instanceof LocalCookbookAccess);
  }

  @Test
  public void testSetAccessTypeRemote() {
    stubFor(get(urlEqualTo("/cookbook/")).willReturn(aResponse().withStatus(200))); // Simulating
                                                                                    // server not
                                                                                    // available

    CookbookAccess accessType = ServerStatusChecker.setAccessType("/path/to/local/file");

    assertNotNull(accessType);
    assertTrue(accessType instanceof RemoteCookbookAccess);
  }



}
