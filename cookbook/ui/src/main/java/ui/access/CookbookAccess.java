package ui.access;

import java.io.IOException;
import java.util.List;
import cookbook.core.User;


/**
 * Interface that centralizes access to a Cookbook through UserAccounts.
 * Makes it easier to support transparent use of a REST API.
 */
public interface CookbookAccess {

  /**
   * Gets all the users that use the Cookbook-application.
   *
   * @return all the users
   * @throws IOException if not found
   */
  public List<User> readUserAccounts() throws IOException;

  /**
   * Get a specific user by username.
   *
   * @param username of the user to get
   * @return the user
   */
  public User readUser(String username, String password);
  
  /**
   * Register new user for the cookbook.
   */
  public User registerNewUser(String username, String password);

  /**
   * Updates thes atrributes of a specific user.
   *
   * @param user the user to update
   * @param indexOfUser in Accounts
   */
  public void updateUserAttributes(User user); 


}
