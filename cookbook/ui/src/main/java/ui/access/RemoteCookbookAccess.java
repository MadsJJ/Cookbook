package ui.access;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cookbook.core.CookBook;
import cookbook.core.Recipe;
import cookbook.core.User;
import cookbook.core.UserDataFilehandling;

public class RemoteCookbookAccess implements CookbookAccess {

  private UserDataFilehandling fileHandler;
  private User user;
  private URI endpointUri;
  private static final String APPLICATION_JSON = "application/json";

  private static final String APPLICATION_FORM_URLENCODED = "application/x-www-form-urlencoded";

  private static final String ACCEPT_HEADER = "Accept";

  private static final String CONTENT_TYPE_HEADER = "Content-Type";

  /**
   * Loads useraccounts from json-file.
   */
  public RemoteCookbookAccess(URI endpointUri) {
    this.endpointUri = endpointUri;

  }


  // private String uriParam(String s) {
  // return URLEncoder.encode(s, StandardCharsets.UTF_8);
  // }

  // private URI todoListUri(String name) {
  // return endpointBaseUri.resolve("list/").resolve(uriParam(name));
  // }



  @Override
  public List<User> readUserAccounts() throws IOException {
    List<User> users = new ArrayList<>();
    HttpRequest request =
        HttpRequest.newBuilder(endpointUri).header(ACCEPT_HEADER, APPLICATION_JSON).GET().build();
    try {
      final HttpResponse<String> response =
          HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
      Gson gson = new Gson();
      User[] userArray = gson.fromJson(response.body(), User[].class);
      if (userArray != null) {
        for (User user : userArray) {
          users.add(user);
        }
      }
      return users;
    } catch (Exception e) {
      throw new IllegalArgumentException("Can't find user");
    }
  }



  @Override
  public User readUser(String username, String password) {
    try {
      return readUserAccounts().stream()
          .filter(a -> a.getUsername().equals(username) && a.getPassword().equals(password))
          .findFirst()
          .orElseThrow(() -> new IllegalArgumentException("Incorrect password or username"));

    } catch (Exception e) {
      throw new IllegalArgumentException("Can't find user");
    }
  }

  @Override
  public User registerNewUser(String username, String password) {
    try {
      validateNoExistingUser(username);
      User user = new User(username, password, new CookBook(new ArrayList<Recipe>()));
      List<User> users = readUserAccounts();
      users.add(user);
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      String jsonUsers = gson.toJson(users);
      HttpRequest request = HttpRequest.newBuilder(endpointUri)
          .header(ACCEPT_HEADER, APPLICATION_JSON).header(CONTENT_TYPE_HEADER, APPLICATION_JSON)
          .PUT(BodyPublishers.ofString(jsonUsers)).build();
      HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
      return user;

    } catch (IOException | InterruptedException e) {
      throw new RuntimeException(e);
    }



  }



  /**
   * Validates that a user with the specified username does not already exist.
   *
   * @param username the username to validate
   * @throws IllegalArgumentException if a user with the specified username already exists
   */
  public void validateNoExistingUser(String username) {
    try {
      if (readUserAccounts().stream().anyMatch(a -> a.getUsername().equals(username))) {
        throw new IllegalArgumentException("Username already exists");
      }
    } catch (IOException e) {
      throw new IllegalArgumentException("Failed to search through users");
    }
  }

  @Override
  public void updateUserAttributes(User user) {

    try {
      List<User> users = readUserAccounts();
      User userToUpdate =
          users.stream().filter(a -> a.getUsername().equals(user.getUsername())).findAny().get();
      userToUpdate.setCookBook(user.getCookBook());
  
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonUsers = gson.toJson(users);
        HttpRequest request = HttpRequest.newBuilder(endpointUri)
            .header(ACCEPT_HEADER, APPLICATION_JSON).header(CONTENT_TYPE_HEADER, APPLICATION_JSON)
            .PUT(BodyPublishers.ofString(jsonUsers)).build();
        HttpClient.newBuilder().build().send(request, HttpResponse.BodyHandlers.ofString());
      
    } catch (Exception e) {
      throw new IllegalArgumentException("Couldn't update file");
    }
   
    


  }



}
