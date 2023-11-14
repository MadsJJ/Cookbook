package ui.access;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cookbook.core.User;

public class RemoteCookbookAccess implements CookbookAccess {
  
  private URI endpointUri;
  private static final String APPLICATION_JSON = "application/json";
  
  private static final String ACCEPT_HEADER = "Accept";
  
  private static final String CONTENT_TYPE_HEADER = "Content-Type";
  
  public RemoteCookbookAccess(URI endpointUri) {
    this.endpointUri = endpointUri;
  }
  
  private String uriParam(String s) {
    return URLEncoder.encode(s, StandardCharsets.UTF_8);
  }

      /**
   * Creates a URI by resolving the input string against the uri for 
   * fetching from the server.
   *
   * @param uri the path.
   * @return the URI on the server with the given path.
   */
  public URI resolveUriAccounts(String uri) {
    return endpointUri.resolve("/"+uri);
  }
  
   /**
   * Sends a POST-request to activate a new user login.
   */
  @Override
public User readUser(String username, String password) {
    String postMappingPath = "cookbook/login";
  
    try {
        // Create a JSON payload for the request body
        String requestBody = "{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}";

        HttpRequest httpRequest = HttpRequest
            .newBuilder(resolveUriAccounts(postMappingPath))
            .header("Content-Type", APPLICATION_JSON)
            .header("Accept", APPLICATION_JSON)
            .POST(BodyPublishers.ofString(requestBody))
            .build();

        final HttpResponse<String> httpResponse =
            HttpClient.newBuilder()
                .build()
                .send(httpRequest, HttpResponse.BodyHandlers.ofString());

        if (httpResponse.statusCode() == 200) {
            User user = new Gson().fromJson(httpResponse.body(), User.class);
            return user;
        } else if (httpResponse.statusCode() == 409) {
            // Incorrect password or username
            throw new IllegalArgumentException("Incorrect password or username");
        } else {
            // Handle other HTTP status codes
            throw new RuntimeException("Unexpected response: " + httpResponse.statusCode());
        }
    } catch (IOException | InterruptedException e) {
        throw new RuntimeException("Error during user login", e);
    }
}


  @Override
  public User registerNewUser(String username, String password) {
      String postMappingPath = "cookbook/register";
  
      // Create a JSON payload for the request body
      String requestBody = "{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}";
      
      HttpRequest httpRequest = HttpRequest
      .newBuilder(resolveUriAccounts(postMappingPath))
      .header("Content-Type", APPLICATION_JSON)
      .header("Accept", APPLICATION_JSON)
      .POST(BodyPublishers.ofString(requestBody))
      .build();
      System.out.println("endpoinst is "+resolveUriAccounts(postMappingPath));
      try {
      final HttpResponse<String> httpResponse = HttpClient.newHttpClient().send(httpRequest, HttpResponse.BodyHandlers.ofString());
  
          if (httpResponse.statusCode() == 200) {
              // Registration successful
              User user = new Gson().fromJson(httpResponse.body(), User.class);
              return user;
          } else if (httpResponse.statusCode() == 409) {
              // Handle username conflict
              throw new IllegalArgumentException("Username already exists");
          } else if (httpResponse.statusCode() == 400) {
              // Handle username conflict
              throw new IllegalArgumentException(User.outputSignup);
          } else {
              // Handle other HTTP status codes
              throw new RuntimeException("Unexpected response: " + httpResponse.statusCode());
          }
      } catch (IOException | InterruptedException e) {
          throw new RuntimeException("Failed to register user", e);
      }
  }
  


@Override
public void updateUserAttributes(User user) {
    String postMappingPath = "cookbook/update";
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String jsonUser = gson.toJson(user);

    // try {
        HttpRequest request = HttpRequest.newBuilder(resolveUriAccounts(postMappingPath))
                .header(ACCEPT_HEADER, APPLICATION_JSON)
                .header(CONTENT_TYPE_HEADER, APPLICATION_JSON)
                .POST(BodyPublishers.ofString(jsonUser))
                .build();

        HttpResponse<String> httpResponse;
        try {
            httpResponse = HttpClient.newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());
                    
                    
                    if (httpResponse.statusCode() != 200) {
                        // Handle other HTTP status codes
                        throw new RuntimeException("Failed to update user attributes. HTTP Status: " + httpResponse.statusCode());
                    }
                } catch (IOException | InterruptedException e) {
                            throw new RuntimeException("Failed to update user attributes", e);

                }
   
}

   
}