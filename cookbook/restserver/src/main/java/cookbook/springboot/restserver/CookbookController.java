package cookbook.springboot.restserver;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
  
  public static final String COOKBOOK_SERVICE_PATH = "/cookbook";
  
 
  @Autowired
  private CookbookService cookbookService;


  @GetMapping
  public List<User> displayUsers() {
    return cookbookService.getUsers();
  }

/**
 * Posts the login requests entered from the client.
 *
 * @param requestBody a map containing the "username" and "password" parameters
 * @return the user if login is correct
 */
@PostMapping(path = "login")
public ResponseEntity<User> loginUser(@RequestBody Map<String, String> requestBody) {
    String username = requestBody.get("username");
    String password = requestBody.get("password");

    if (cookbookService.getExistingUser(username, password) == null) {
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    } else {
        User loggedInUser = cookbookService.getExistingUser(username, password);
        return new ResponseEntity<>(loggedInUser, HttpStatus.OK);
    }
}

  
  @PostMapping(path = "register")
  public ResponseEntity<User> registerUser(@RequestBody Map<String, String> requestBody) {
      String username = requestBody.get("username");
      String password = requestBody.get("password");
      System.out.println("username is"+username);
      if (cookbookService.checkIfUsernameTaken(username)) {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
      try {
  
          User registeredUser = cookbookService.signup(username, password);
          return new ResponseEntity<>(registeredUser, HttpStatus.OK);
  
      } catch (IllegalArgumentException e) {
          // Handle the exception and return a ResponseEntity with an appropriate status code
          return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
      }
  }


  // localhost:8080/cookbook/update
@PostMapping(path = "update")
public ResponseEntity<String> updateUser(@RequestBody User user) {
    try {
        cookbookService.autoSaveUser(user);
        return ResponseEntity.status(HttpStatus.OK).body("User updated successfully");
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update user");
    }
}


}
