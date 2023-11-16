package ui.access;

import cookbook.core.User;
import cookbook.core.UserDataFilehandling;

/**
 * Class that utilizes json file directly for data handling.
 */
public class LocalCookbookAccess implements CookbookAccess {

  private final UserDataFilehandling fileHandler;

  /**
   * Loads useraccounts from json-file.
   */
  public LocalCookbookAccess(UserDataFilehandling fileHandler) {
    this.fileHandler = fileHandler;
  }
  
  @Override
  public User readUser(String username, String password) {
    return fileHandler.getUser(username, password);
  }

  @Override
  public User registerNewUser(String username, String password) {
    return fileHandler.signup(username, password);
  }

  @Override
  public void updateUserAttributes(User user) {
    fileHandler.updateFile(user);
  }
}