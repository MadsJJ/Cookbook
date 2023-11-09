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

  public User getExistingUser(String username,String password){
    return getUsers().stream()
    .filter(a -> a.getUsername().equals(username) && a.getPassword().equals(password))
    .findFirst()
    .orElseThrow(() -> new IllegalArgumentException("Incorrect password or username"));
  }
  
  public void checkIfUsernameTaken(String username){
    if (getUsers().stream().anyMatch(a -> a.getUsername().equals(username))) {
        throw new IllegalArgumentException("Username already exists");
    }
  }

  public User signup(String username, String password) {
    return userDataFilehandler.signup(username, password);
  }
 
  public void autoSaveUser(User user) {
    userDataFilehandler.updateFile(user);
  }
}





