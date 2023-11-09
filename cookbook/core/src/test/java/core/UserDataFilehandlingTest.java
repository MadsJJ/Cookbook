package core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cookbook.core.CookBook;
import cookbook.core.Ingredient;
import cookbook.core.Recipe;
import cookbook.core.User;
import cookbook.core.UserDataFilehandling;


public class UserDataFilehandlingTest {

  private UserDataFilehandling filehandler;
  private User user;

  @BeforeEach
  public void setup(){
    this.filehandler=new UserDataFilehandling("/src/test/java/ui/resources/ui/UserDataTest.json");
      String userDir = System.getProperty("user.dir").toString();
                  // userDir=userDir.replace("/cookbook/core","/cookbook/ui");
                  userDir=userDir.substring(0, userDir.length()-4);
                  userDir=userDir+"ui/";

             

              
            try (FileWriter writer = new FileWriter(userDir+"src/test/java/ui/resources/ui/UserDataTest.json", StandardCharsets.UTF_8)) { // Specify UTF-8 encoding
            writer.write("");
            this.user= new User("username", "password", new CookBook(new ArrayList<Recipe>()));
            filehandler.signup("username", "password");
            writer.close();


   } catch (IOException e) {
      e.printStackTrace();
    }

    

  }
  @Test
    public void testGetUserSucess() {
        assertEquals(this.user.toString(),filehandler.getUser("username", "password").toString());
    }

    @Test
    public void testGetUserFail() {
        assertThrows(IllegalArgumentException.class,()->filehandler.getUser("username1","password"));
    }

  @Test
  public void testFindUsers(){
    assertEquals(1,filehandler.findUsers().size());
    filehandler.signup("findUsersTest", "password");
    assertEquals(2,filehandler.findUsers().size());
  }

  @Test
  public void testSignupSuccess(){
    User signupUser = new User("signupTest", "password", new CookBook(new ArrayList<Recipe>()));
    filehandler.signup(signupUser.getUsername(), signupUser.getPassword());
    assertEquals(signupUser.toString(),filehandler.getUser("signupTest", "password").toString());
  }


  @Test
  public void testSignupWithExistingUsername(){
    assertThrows(IllegalArgumentException.class,()->filehandler.signup("username","password"));
  }

  @Test
  public void testUpdateFile(){
  
    assertEquals(0,user.getCookBook().getRecipes().size());
    user.getCookBook().addRecipe(new Recipe("recipeTest", Arrays.asList(new Ingredient("ingTest", 20, "g")), "Dinner"));
    filehandler.updateFile(user);
    assertEquals(1,filehandler.getUser(user.getUsername(), user.getPassword()).getCookBook().getRecipes().size());


    
  }


}
