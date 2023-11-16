package cookbook.springboot.restserver;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import cookbook.core.CookBook;
import cookbook.core.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class CookbookServiceTest {

  private CookbookService cookbookService;
  private User user1;
  private User user2;
  private User user3;
  
  @BeforeEach
  public void setup() throws IllegalStateException, IOException {

    String userDir = System.getProperty("user.dir");
      if (userDir.endsWith("restserver")) {
      userDir = userDir.substring(0, userDir.length() - 10);
      userDir = userDir + "ui";
      }
    try (FileWriter writer = new FileWriter(
          Paths.get(userDir, "/src/test/java/ui/resources/ui/RestserverTest.json").toString(),
          StandardCharsets.UTF_8)) { 
        writer.write("");
        writer.close();
        
      } catch (Exception a) {
        a.printStackTrace();
      }

    cookbookService = new CookbookService();
    cookbookService.setFileHandler("/src/test/java/ui/resources/ui/RestserverTest.json");
    this.user1 = new User("test1", "test", new CookBook(new ArrayList<>()));
    this.user2 = new User("test2", "test", new CookBook(new ArrayList<>()));
    this.user3 = new User("test3", "test", new CookBook(new ArrayList<>()));
    cookbookService.signup("test1", "test");
    cookbookService.signup("test2", "test");
    cookbookService.signup("test3", "test");
  }

    @Test
    void getUsers() {
        List<User> users = cookbookService.getUsers();
        assertNotNull(users);
        assertEquals(users.size(),3);
    }

    @Test
    void getFileHandler() {
        assertNotNull(cookbookService.getFileHandler());
    }


    @Test
    void getExistingUser() {
        User existingUser = cookbookService.getExistingUser("test1", "test");
        assertEquals(user1.toString(), existingUser.toString()); 
        User existingUser2 = cookbookService.getExistingUser("test2", "test");
        assertEquals(user2.toString(), existingUser2.toString()); 
        User existingUser3 = cookbookService.getExistingUser("test3", "test");
        assertEquals(user3.toString(), existingUser3.toString()); 

        assertNull(cookbookService.getExistingUser("wrongusername", "test"));
        assertNull(cookbookService.getExistingUser("test3", "wrongpassword")); 
        assertNull(cookbookService.getExistingUser("test4", "test"));
        
    }

    @Test
    void checkIfUsernameTaken() {
        boolean isUsernameTakenTrue = cookbookService.checkIfUsernameTaken("test1");
        boolean isUsernameTakenFalse = cookbookService.checkIfUsernameTaken("test4");

        assertTrue(isUsernameTakenTrue);
        assertFalse(isUsernameTakenFalse);
    }

    @Test
    void signup() {
        User newUser = cookbookService.signup("newUsername", "newPassword");
        assertNotNull(newUser);
    }


    @Test
    void autoSaveUser() {
        assertDoesNotThrow(() -> cookbookService.autoSaveUser(user1));
    }
    
    // You can add more tests for other methods as needed




}
