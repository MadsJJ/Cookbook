package ui.access;

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
    return endpointUri.resolve(uri);
  }
  
   /**
   * Sends a POST-request to activate a new user login.
   */
  @Override
  public User readUser(String username, String password) {
    String postMappingPath = "login?";
    String key1 = "username=";
    String value1 = username + "&";
    String key2 = "password=";
    String value2 = password;
    
    try {
      HttpRequest httpRequest = HttpRequest
            .newBuilder(resolveUriAccounts(postMappingPath + key1 + value1 + key2 + value2))
            .header("Accept", "application/json")
            .POST(BodyPublishers.ofString(username + "|" + password))
            .build();
 
      final HttpResponse<String> httpResponse =
          HttpClient.newBuilder()
                    .build()
                    .send(httpRequest, HttpResponse.BodyHandlers.ofString());

         return new Gson().fromJson(httpResponse.body(), User.class);

    } catch (Exception e) {
      throw new IllegalArgumentException("Incorrect password or username");
    }

  }

  @Override
  public User registerNewUser(String username, String password) {
    String postMappingPath = "register?";
    String key1 = "username=";
    String value1 = username + "&";
    String key2 = "password=";
    String value2 = password;
    
    try {
      HttpRequest httpRequest = HttpRequest
            .newBuilder(resolveUriAccounts(postMappingPath + key1 + value1 + key2 + value2))
            .header("Accept", "application/json")
            .POST(BodyPublishers.ofString(username + "|" + password))
            .build();
 
      final HttpResponse<String> httpResponse =
          HttpClient.newBuilder()
                    .build()
                    .send(httpRequest, HttpResponse.BodyHandlers.ofString());

         User user = new Gson().fromJson(httpResponse.body(), User.class);
         System.out.println(user);
         System.out.println(httpResponse.body());
         return user;
    } catch (Exception e) {
      throw new IllegalArgumentException("Username already exists");
    }
  }

  @Override
  public void updateUserAttributes(User user) {
    String postMappingPath = "update?";
    String key = "username=";
    String username = user.getUsername();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String jsonUser = gson.toJson(user);

      try {
       HttpRequest request = HttpRequest.newBuilder(resolveUriAccounts(postMappingPath+key+username))
            .header(ACCEPT_HEADER, APPLICATION_JSON)
            .header(CONTENT_TYPE_HEADER, APPLICATION_JSON)
            .POST(BodyPublishers.ofString(jsonUser))
            .build();

            System.out.println(BodyPublishers.ofString(username));
            System.out.println(request.toString());
 
      final HttpResponse<String> httpResponse =
          HttpClient.newBuilder()
                    .build()
                    .send(request, HttpResponse.BodyHandlers.ofString());

                    System.out.println(httpResponse.body());
    } catch (Exception e) {
      throw new IllegalArgumentException("Failed to update file");
    }
  }
   
}