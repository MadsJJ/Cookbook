package cookbook.springboot.restserver;

import java.util.List;
import org.springframework.stereotype.Service;
import cookbook.core.User;
import cookbook.core.UserDataFilehandling;

/**
* Configures the cookbook service,
* including autowired objects.
*/
@Service
public class CookbookService {

    private UserDataFilehandling userDataFilehandler;
  
    public CookbookService() {
        userDataFilehandler = new UserDataFilehandling("/src/main/resources/ui/springboot.json");
    }

    public List<User> getUsers() {
        return userDataFilehandler.findUsers();
    }

    public UserDataFilehandling getFileHandler(){
      return userDataFilehandler;
    }

    public void setFileHandler(String path){
      this.userDataFilehandler=new UserDataFilehandling(path);
    }

    public User getExistingUser(String username, String password) {
      return getUsers().stream()
        .filter(a -> a.getUsername().equals(username) && a.getPassword().equals(password))
        .findFirst()
        .orElse(null); // Return null if the user is not found
    }
  
  public boolean checkIfUsernameTaken(String username){
    return getUsers().stream().anyMatch(a -> a.getUsername().equals(username));
  }

  public User signup(String username, String password) {
    return userDataFilehandler.signup(username, password);
  }
 
  public void autoSaveUser(User user) {
    userDataFilehandler.updateFile(user);
  }
}





