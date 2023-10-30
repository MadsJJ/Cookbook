package cookbook.springboot.restserver;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cookbook.core.CookBook;
import cookbook.core.Recipe;
import cookbook.core.User;
import cookbook.core.UserDataFilehandling;

/**
* Configures the cookbook service,
* including autowired objects.
*/
@Service
public class CookbookService {

    private User user;
    private UserDataFilehandling userDataFilehandler;
    
    public CookbookService(User user) {
        userDataFilehandler = new UserDataFilehandling("");
        this.user = user; // Inject the User bean through constructor injection
    }

    @Autowired
    public CookbookService() {
      this(createDefaultUser());
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private static User createDefaultUser() {
    UserDataFilehandling defaultUserDataFilehandler = new UserDataFilehandling("");

    try {
      return defaultUserDataFilehandler.getUser("default", "password");
    } catch (Exception e) {
      System.out.println("Couldn't read default-cookbook.json, so making user manually ("
            + e + ")");
    }
    User user = manuallyCreateUser();
    return user;
  } 


  /**
   * Method that creates two test users.
   */
  private static User manuallyCreateUser() {
    User user = new User("username", "password", new CookBook(new ArrayList<Recipe>()));        
    return user;
  }

  /**
   * Saves the User to disk.
   * Should be called after each update.
   */
  public void autoSaveUser() {
      if (userDataFilehandler != null) {
          try {
              userDataFilehandler.updateFile(user);
          } catch (Exception e) {
              System.err.println("Couldn't auto-save User: " + e);
          }
      }
  }
}





