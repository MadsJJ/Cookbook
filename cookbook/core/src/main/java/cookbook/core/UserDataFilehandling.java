package cookbook.core;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * This class handles the file operations for user data.
 */
public class UserDataFilehandling {

  private final String userFile;

  /**
   * Constructs a new UserDataFilehandling object with the specified user file.
   *
   * @param userFile the name of the user file
   */
  public UserDataFilehandling(String userFile) {
    String userDir = System.getProperty("user.dir");
    File dirFolder = new File(userDir);

    // Adjust userDir based on project structure
    if (userDir.endsWith("gr2308")) {
        dirFolder = new File(dirFolder, "cookbook/ui");
    } else if (userDir.endsWith("core")) {
        dirFolder = new File(dirFolder.getParent(), "ui");
    } else if (userDir.endsWith("restserver")) {
        dirFolder = new File(dirFolder.getParent(), "ui");
    }

    this.userFile = new File(dirFolder, userFile).getPath();
}

  /**
   * Finds all users in the user file.
   *
   * @return a list of all users in the user file
   */
  public List<User> findUsers() {
    List<User> users = new ArrayList<>();
    try (FileReader reader = new FileReader(userFile, StandardCharsets.UTF_8)) { // Specify UTF-8
      Gson gson = new Gson();
      User[] userArray = gson.fromJson(reader, User[].class);
      if (userArray != null) {
        for (User user : userArray) {
          users.add(user);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return users;
  }

  /**
   * Gets a user with the specified username and password.
   *
   * @param username the username of the user to get
   * @param password the password of the user to get
   * @return the user with the specified username and password
   * @throws IllegalArgumentException if the username or password is incorrect
   */
  public User getUser(String username, String password) {
    return findUsers().stream()
        .filter(a -> a.getUsername().equals(username) && a.getPassword().equals(password))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Incorrect password or username"));
  }

  /**
   * Validates that a user with the specified username does not already exist.
   *
   * @param username the username to validate
   * @throws IllegalArgumentException if a user with the specified username already exists
   */
  public void validateNoExistingUser(String username) {
    if (findUsers().stream().anyMatch(a -> a.getUsername().equals(username))) {
      throw new IllegalArgumentException("Username already exists");
    }
  }

  /**
   * Signs up a new user with the specified username and password.
   *
   * @param username the username of the new user
   * @param password the password of the new user
   * @return the newly signed up user
   * @throws IllegalArgumentException if a user with the specified username already exists
   */
  public User signup(String username, String password) {
    validateNoExistingUser(username);
    User user = new User(username, password, new CookBook(new ArrayList<Recipe>()));
    List<User> users = findUsers();
    users.add(user);
    try (FileWriter writer = new FileWriter(userFile, StandardCharsets.UTF_8)) { // Specify UTF-8
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      gson.toJson(users, writer); // Serialize and write the updated user list
    } catch (IOException e) {
      e.printStackTrace();
    }
    return user;
  }

  /**
   * Updates the user file with the specified user.
   *
   * @param user the user to update
   */
  public void updateFile(User user) {
    List<User> users = findUsers();
    User userToUpdate =
        users.stream().filter(a -> a.getUsername().equals(user.getUsername())).findAny().get();
    userToUpdate.setCookBook(user.getCookBook());
    try (FileWriter writer = new FileWriter(userFile, StandardCharsets.UTF_8)) { // Specify UTF-8
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      gson.toJson(users, writer); // Serialize and write the updated user list
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}