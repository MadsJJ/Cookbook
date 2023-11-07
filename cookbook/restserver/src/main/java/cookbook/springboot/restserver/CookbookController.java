package cookbook.springboot.restserver;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import cookbook.core.User;

/**
 * The service implementation.
 */

@RestController
@RequestMapping(CookbookController.COOKBOOK_SERVICE_PATH)
public class CookbookController {
  
  public static final String COOKBOOK_SERVICE_PATH = "/cookbook/";
  
 
  @Autowired
  private CookbookService cookbookService;


  @GetMapping
  public List<User> displayUsers() {
    System.out.println("getting users in controller");
    return cookbookService.getUsers();
  }

  /**
   * Posts the login requests entered from client.
   *
   * @param email the email
   * @param password the password
   * @return the user if login is correct
   */

  //localhost:8080/cookbook/login?username={username}&password={password}
  @PostMapping(path = "login")
  public User loginUser(@RequestParam("username") String username, 
      @RequestParam("password") String password) {
        return cookbookService.getExistingUser(username, password);
    }

  //localhost:8080/cookbook/register?username={username}&password={password}
  @PostMapping(path = "register")
  public User registerUser(@RequestParam("username") String username, 
      @RequestParam("password") String password) {
          cookbookService.checkIfUsernameTaken(username);
          return cookbookService.signup(username, password);
    }

  //localhost:8080/cookbook/update?username={username}
  @PostMapping(path = "update")
  public void updateUser(@RequestBody User user) {
      cookbookService.autoSaveUser(user);
    }

}
