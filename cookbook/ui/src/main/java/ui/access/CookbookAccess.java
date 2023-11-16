package ui.access;

import cookbook.core.User;

/**
 * Interface that centralizes access to a Cookbook through UserAccounts.
 * Makes it easier to support transparent use of a REST API.
 */
public interface CookbookAccess {

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
   */
  public void updateUserAttributes(User user); 
}
