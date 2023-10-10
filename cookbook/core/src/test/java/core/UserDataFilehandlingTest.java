package core;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


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
            CookBook book = new CookBook(new ArrayList<Recipe>());
            this.user= new User("username", "password", book);
            filehandler.signup("username", "password");
            writer.close();


   } catch (IOException e) {
      e.printStackTrace();
    }

    

  }
  @Test
    public void testGetName() {
        assertEquals(filehandler.getUser("username", "password").toString(),this.user.toString());
    }



}
