package cookbook.springboot.restserver;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import cookbook.core.CookBook;
import cookbook.core.Recipe;
import cookbook.core.User;
import cookbook.json.CookbookPersistence;

/**
* Configures the cookbook service,
* including autowired objects.
*/
@Service
public class CookbookService {

    private User user;
    private static final CookbookPersistence COOKBOOKPERSISTENCE = new CookbookPersistence();

    public CookbookService(User user) {
        this.user = user; // Inject the User bean through constructor injection
        COOKBOOKPERSISTENCE.setSaveFile("springbootserver-cookbook.json");
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
    CookbookPersistence cookbookPersistence = new CookbookPersistence();
    URL url = CookbookService.class.getResource("default-user.json");
    if (url != null) {
      try (Reader reader = new InputStreamReader(url.openStream(), StandardCharsets.UTF_8)) {
        return cookbookPersistence.readUser(reader);
      } catch (IOException e) {
        System.out.println("Couldn't read default-cookbook.json, so making user manually ("
            + e + ")");
      }
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
      if (COOKBOOKPERSISTENCE != null) {
          try {
              COOKBOOKPERSISTENCE.saveUser(this.user);
          } catch (IllegalStateException | IOException e) {
              System.err.println("Couldn't auto-save User: " + e);
          }
      }
  }
}





