package cookbook.springboot.restserver;

import cookbook.core.User;
import cookbook.core.UserDataFilehandling;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 * Service class for the Cookbook application, handling user-related operations.
 */
@Service
public class CookbookService {

  private UserDataFilehandling userDataFilehandler;

  /**
   * Default constructor initializes the UserDataFilehandling with a default file path.
   */
  public CookbookService() {
    userDataFilehandler = new UserDataFilehandling("/src/main/resources/ui/UserData.json");
  }

  /**
   * Retrieves the list of users from the data file.
   *
   * @return List of User objects
   */
  public List<User> getUsers() {
    return userDataFilehandler.findUsers();
  }

  /**
   * Retrieves the UserDataFilehandling object.
   *
   * @return UserDataFilehandling object
   */
  public UserDataFilehandling getFileHandler() {
    return userDataFilehandler;
  }

  /**
   * Sets the UserDataFilehandling object with a new file path.
   *
   * @param path New file path
   */
  public void setFileHandler(String path) {
    this.userDataFilehandler = new UserDataFilehandling(path);
  }

  /**
   * Retrieves an existing user based on the provided username and password.
   *
   * @param username User's username
   * @param password User's password
   * @return User object if found, null otherwise
   */
  public User getExistingUser(String username, String password) {
    return getUsers().stream()
        .filter(a -> a.getUsername().equals(username) && a.getPassword().equals(password))
        .findFirst()
        .orElse(null); // Return null if the user is not found
  }

  /**
   * Checks if a username is already taken by an existing user.
   *
   * @param username Username to check
   * @return True if the username is taken, false otherwise
   */
  public boolean checkIfUsernameTaken(String username) {
    return getUsers().stream().anyMatch(a -> a.getUsername().equals(username));
  }

  /**
   * Registers a new user with the provided username and password.
   *
   * @param username User's username
   * @param password User's password
   * @return Registered User object
   */
  public User signup(String username, String password) {
    return userDataFilehandler.signup(username, password);
  }

  /**
   * Automatically saves user information to the data file.
   *
   * @param user User object to be saved
   */
  public void autoSaveUser(User user) {
    userDataFilehandler.updateFile(user);
  }
  
}
