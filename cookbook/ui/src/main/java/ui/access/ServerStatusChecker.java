package ui.access;


import cookbook.core.UserDataFilehandling;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Class that pings the server to check if it is running 
 *and configures accessType for controllers based on ping response.
 */
public class ServerStatusChecker {

  // The endpoint URI for the server
  static String endpointUri = "http://localhost:8080/cookbook/";

  // The local file path for cookbook data
  static String localFilePath = "/src/main/resources/ui/UserData.json";

  /**
   * Send a request to the server endpoint and checks if the server is running.
   *
   * @return true if the server is running, false otherwise
   * 
   */
  public static Boolean serverStatus() {
    try {
      // Create an HttpClient
      HttpClient httpClient = HttpClient.newHttpClient();

      // Create an HttpRequest to the server's endpoint
      HttpRequest request = HttpRequest.newBuilder().uri(new URI(endpointUri)).GET().build();

      // Send the request and get the response
      HttpResponse<String> response =
          httpClient.send(request, HttpResponse.BodyHandlers.ofString());

      // Check the response status code
      if (response.statusCode() == 200) {
        System.out.println("Server is running. Using remote access: ");
        return true;
      } else {
        System.out.println(
            "Server returned an error. Response code: " + response.statusCode());
        return false;
      }

    } catch (Exception e) {
      System.err.println("Error checking server status: " + e.getMessage());
      return false;
    }
  }

  /**
   * Determine the appropriate CookbookAccess type based on the server status.
   *
   * @param filepath the local file path for cookbook data
   * @return a CookbookAccess object representing the appropriate access type
   */
  public static CookbookAccess setAccessType(String filepath) {
    CookbookAccess accessType;
    try {
      if (serverStatus()) {
        RemoteCookbookAccess remoteAccess;
        System.out.println("Using remote endpoint @ " + endpointUri);
        remoteAccess = new RemoteCookbookAccess(new URI(endpointUri));
        accessType = remoteAccess;
      } else {
        System.out.println("Failed to establish contact with the server. \n"
            + "Using data directly from file \n" + "@" + filepath);

        LocalCookbookAccess localAccess =
            new LocalCookbookAccess(new UserDataFilehandling(filepath));
        accessType = localAccess;
      }

    } catch (Exception e) {
      System.out.println("Error occurred when attempting contact with the server. \n"
          + "Using data directly from file \n" + "@" + filepath);

      LocalCookbookAccess localAccess =
          new LocalCookbookAccess(new UserDataFilehandling(filepath));
      accessType = localAccess;
    }

    return accessType;
  }
}
