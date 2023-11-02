package cookbook.springboot.restserver;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
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
    private List<User> users;
    
    @Autowired
    public CookbookService(List<User> users) {
        userDataFilehandler = new UserDataFilehandling("\\src\\main\\resources\\ui\\springboot.json");
        this.users = userDataFilehandler.findUsers(); // Inject the User bean through constructor injection
        System.out.println(users);

    }

    // @Autowired
    // public CookbookService() {
    //   this(createDefaultUser());
    // }
    
    public List<User> getUsers() {
        System.out.println("finding users in Service");
        return userDataFilehandler.findUsers();
    }

    public UserDataFilehandling getFileHandler(){
      return userDataFilehandler;
    }


    public void setUser(User user) {
        this.user = user;
    // }

    // private static List<User> createDefaultUser() {
    // UserDataFilehandling defaultUserDataFilehandler = new UserDataFilehandling("\\src\\main\\resources\\ui\\springboot.json");

  
    // System.out.println(defaultUserDataFilehandler.findUsers());
    // return defaultUserDataFilehandler.findUsers();
  } 


  /**
   * Method that creates two test users.
  //  */
  // private static User manuallyCreateUser() {
  //   User user = new User("username", "password", new CookBook(new ArrayList<Recipe>()));        
  //   return user;
  // }

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





