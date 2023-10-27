package ui.access;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import cookbook.core.User;
import cookbook.core.UserAccounts;

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
  public UserAccounts readUserAccounts() throws IOException;

  /**
   * Get a specific user by username.
   *
   * @param username of the user to get
   * @return the user
   */
  public User readUser(String username);
  
  /**
   * Method to log in.
   *
   * @param username of the user
   * @param password of the user
   * @return the user
   */
  public User userLogin(String username, String password);

  /**
   * Register multiple new users for the cookbook.
   */
  public void registerNewUserAccounts(UserAccounts useraccounts);

  /**
   * Creates a new user and adds it to Accounts.
   *
   * @param user the user to create
   */
  public void createUser(User user);

  /**
   * Updates thes atrributes of a specific user.
   *
   * @param user the user to update
   * @param indexOfUser in Accounts
   */
  public void updateUserAttributes(User user, int indexOfUser); 
  
  /**
   * Removes the UserAccounts object currently used by
   * Cookbook.
   */
   public void deleteAccounts();

   /**
   * 
   */ 
   public void uploadFile(File file) throws IOException, InterruptedException, URISyntaxException;  

  /**
   * Returns the logged in user
   */ 
  public User getLoggedInUser();




}
