package ui.access;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cookbook.core.User;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;



/**
 * Provides access to the cookbook server using remote API calls.
 */
public class RemoteCookbookAccess implements CookbookAccess {

  private URI endpointUri;
  private static final String APPLICATION_JSON = "application/json";
  private static final String ACCEPT_HEADER = "Accept";
  private static final String CONTENT_TYPE_HEADER = "Content-Type";

  /**
   * Constructs a RemoteCookbookAccess instance with the specified endpoint URI.
   *
   * @param endpointUri The URI of the cookbook server.
   */
  public RemoteCookbookAccess(URI endpointUri) {
    this.endpointUri = endpointUri;
  }

  /**
   * Creates a URI by resolving the input string against the URI for fetching from the server.
   *
   * @param uri The path.
   * @return The URI on the server with the given path.
   */
  public URI resolveUriAccounts(String uri) {
    return endpointUri.resolve("/" + uri);
  }

  /**
   * Sends a POST-request to activate a new user login.
   *
   * @param username The username of the user.
   * @param password The password of the user.
   * @return The user information if login is successful.
   * @throws IllegalArgumentException If the username or password is incorrect.
   * @throws RuntimeException         If an unexpected error occurs during login.
   */
  @Override
  public User readUser(String username, String password) {
    String postMappingPath = "cookbook/login";

    try {
      // Create a JSON payload for the request body
      String requestBody = 
          "{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}";

      HttpRequest httpRequest = HttpRequest.newBuilder(resolveUriAccounts(postMappingPath))
            .header("Content-Type", APPLICATION_JSON)
            .header("Accept", APPLICATION_JSON)
            .POST(BodyPublishers.ofString(requestBody))
            .build();

      final HttpResponse<String> httpResponse =
           HttpClient.newBuilder()
                .build()
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());

      if (httpResponse.statusCode() == 200) {
        return new Gson().fromJson(httpResponse.body(), User.class);
      } else if (httpResponse.statusCode() == 409) {
        throw new IllegalArgumentException("Incorrect password or username");
      } else {
        throw new RuntimeException("Unexpected response: " + httpResponse.statusCode());
      }

    } catch (IOException | InterruptedException e) {
      throw new RuntimeException("Error during user login", e);
    }
  }

  /**
   * Registers a new user by sending a POST request to the server.
   *
   * @param username The username of the new user.
   * @param password The password of the new user.
   * @return The user information if registration is successful.
   * @throws IllegalArgumentException If the username already exists or the format is invalid.
   * @throws RuntimeException         If an unexpected error occurs during registration.
   */
  @Override
  public User registerNewUser(String username, String password) {
    String postMappingPath = "cookbook/register";

    // Create a JSON payload for the request body
    String requestBody = "{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}";

    HttpRequest httpRequest = HttpRequest.newBuilder(resolveUriAccounts(postMappingPath))
        .header("Content-Type", APPLICATION_JSON)
        .header("Accept", APPLICATION_JSON)
        .POST(BodyPublishers.ofString(requestBody))
        .build();

    try {
      final HttpResponse<String> httpResponse = 
          HttpClient.newHttpClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());

      if (httpResponse.statusCode() == 200) {
        return new Gson().fromJson(httpResponse.body(), User.class);
      } else if (httpResponse.statusCode() == 409) {
        throw new IllegalArgumentException("Username already exists");
      } else if (httpResponse.statusCode() == 400) {
        throw new IllegalArgumentException(User.outputSignup);
      } else {
        throw new RuntimeException("Unexpected response: " + httpResponse.statusCode());
      }
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException("Failed to register user", e);
    }
  }

  /**
   * Sends a POST request to the server to update user attributes.
   *
   * @param user The user information to update.
   * @throws RuntimeException If an unexpected error occurs during the update.
   */
  @Override
  public void updateUserAttributes(User user) {
    String postMappingPath = "cookbook/update";
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String jsonUser = gson.toJson(user);

    HttpRequest request = HttpRequest.newBuilder(resolveUriAccounts(postMappingPath))
        .header(ACCEPT_HEADER, APPLICATION_JSON)
        .header(CONTENT_TYPE_HEADER, APPLICATION_JSON)
        .POST(BodyPublishers.ofString(jsonUser))
        .build();

    try {
      HttpResponse<String> httpResponse = HttpClient.newBuilder()
            .build()
            .send(request, HttpResponse.BodyHandlers.ofString());

      if (httpResponse.statusCode() != 200) {
        throw new RuntimeException("Failed to update user attributes. HTTP Status: "
        + httpResponse.statusCode());
      }
    } catch (IOException | InterruptedException e) {
      throw new RuntimeException("Failed to update user attributes", e);
    }
  }
}
