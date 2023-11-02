package cookbook.springboot.restserver;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

  public static final String COOKBOOK_SERVICE_PATH = "cookbook";

  @Autowired
  private CookbookService cookbookService;

  
  @GetMapping
  public List<User> getUsers() {
    System.out.println("getting users in controsller");
    return cookbookService.getUsers();
  }
 

  @PutMapping(path = "/{name}")
  public void setUser(@PathVariable("name") String name, @RequestBody User user) {
      // You can now use the 'user' object obtained from the request body
      // cookbookService.getFileHandler().getUser(name, name);
      System.out.println("putmapping");
      cookbookService.setUser(user);
      cookbookService.autoSaveUser();
  }

  @GetMapping(path ="/{name}")
  public User getUser(@PathVariable("name") String name, @RequestBody User user) {
    System.out.println("getting users in controsller");
    cookbookService.setUser(user);
    return user;
  }



  private void autoSaveUser() {
   cookbookService.autoSaveUser();
  }

  private void checkUser(User user, String username) {
   if (user == null) {
     throw new IllegalArgumentException("No user named \"" + username + "\"");
   }
  }

  /**
  // * Gets the corresponding User.
  // *
  // * @param username the name of the User
  // * @return the corresponding User
  // */
  // @GetMapping(path = "/list/{username}")
  // public User getUser(@PathVariable("username") String username) {
  //  User user = getUser().getUser(username);
  //  checkUser(user, username);
  //  return user;
  // }

  /**
   * Replaces or adds a User.
   *
   * @param username the name of the User
   * @param User the User to add
   * @return true if it was added, false if it replaced
   */
  //@PutMapping(path = "/list/{username}")
  //public boolean putUser(@PathVariable("username") String username,
  //    @RequestBody AbstractUser user) {
  //  boolean added = getUser().putUser(username) == null;
  //  autoSaveUser();
  //  return added;
  //}

  /**
   * Renames the User.
   *
   * @param username the name of the User
   * @param newUsername the new name
   */
  //@PostMapping(path = "/list/{username}/rename")
  //public boolean renameCookbook(@PathVariable("username") String username,
  //    @RequestParam("newUsername") String newUsername) {
  //  AbstractUser cookbook = getUser().getUser(username);
  //  checkUser(user, username);
  //  if (getUser().getUser(newUsername) != null) {
  //    throw new IllegalArgumentException("A User named \"" + newUsername + "\" already exists");
  //  }
  //  user.setName(newUsername);
  //  autoSaveUser();
  //  return true;
  //}

  /**
   * Removes the Cookbook.
   *
  //  * @param name the name of the Cookbook
  //  */
  // @DeleteMapping(path = "/list/{username}")
  // public boolean removeUser(@PathVariable("username") String username) {
  //  AbstractUser User = getUser().getUser(username);
  //  checkUser(User, username);
  //  getUser().removeUser(User);
  //  autoSaveUser();
  //  return true;
  // }
}
