package ui.access;


import java.io.IOException;
import java.util.List;
import cookbook.core.User;
import cookbook.core.UserDataFilehandling;

public class LocalCookbookAccess implements CookbookAccess{

  private final UserDataFilehandling fileHandler;

  /**
   * Loads useraccounts from json-file.
   */
  public LocalCookbookAccess(UserDataFilehandling fileHandler) {
    this.fileHandler=fileHandler;
    fileHandler = new UserDataFilehandling("/src/main/resources/ui/UserData.json");
   
    }

  @Override
  public List<User> readUserAccounts() throws IOException {
    return fileHandler.findUsers();
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
