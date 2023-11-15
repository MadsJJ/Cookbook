package cookbook.springboot.restserver;

import cookbook.core.User;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class for the Cookbook service.
 */
@RestController
@RequestMapping(CookbookController.COOKBOOK_SERVICE_PATH)
public class CookbookController {
  
  /** The base path for the Cookbook service. */
  public static final String COOKBOOK_SERVICE_PATH = "/cookbook";
  
  @Autowired
  private CookbookService cookbookService;

  /**
   * Retrieves a list of all users.
   *
   * @return List of User objects
   */
  @GetMapping
  public List<User> displayUsers() {
    return cookbookService.getUsers();
  }

  /**
   * Handles login requests from clients.
   *
   * @param requestBody Map containing the "username" and "password" parameters
   * @return ResponseEntity with the user if login is correct or CONFLICT status if not
   */
  @PostMapping(path = "login")
  public ResponseEntity<User> loginUser(@RequestBody Map<String, String> requestBody) {
    String username = requestBody.get("username");
    String password = requestBody.get("password");

    User loggedInUser = cookbookService.getExistingUser(username, password);
    if (loggedInUser == null) {
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    } else {
      return new ResponseEntity<>(loggedInUser, HttpStatus.OK);
    }
  }

  /**
   * Handles user registration requests.
   *
   * @param requestBody Map containing the "username" and "password" parameters
   * @return ResponseEntity with the registered user if successful, CONFLICT if username is taken,
   *         or BAD_REQUEST if there is an issue with the request
   */
  @PostMapping(path = "register")
  public ResponseEntity<User> registerUser(@RequestBody Map<String, String> requestBody) {
    String username = requestBody.get("username");
    String password = requestBody.get("password");
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

  /**
   * Handles user update requests.
   *
   * @param user User object containing updated information
   * @return ResponseEntity with a success message if update is successful,
   *         or INTERNAL_SERVER_ERROR if there is an issue with the update
   */
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
