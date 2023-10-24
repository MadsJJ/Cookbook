package core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import cookbook.core.CookBook;
import cookbook.core.Recipe;
import cookbook.core.User;

public class UserTest {
  private User user;
    


  @BeforeEach
    public void setUp() {
        user = new User("username", "password", new CookBook(new ArrayList<Recipe>()));
    }

  @Test
  public void testContructor(){
     User user2 = new User("username2", "password", new CookBook(new ArrayList<Recipe>()));
      assertEquals("username2",user2.getUsername());

      assertThrows(IllegalArgumentException.class,()->new User("username2", "password",null));
  }

  @Test
  public void testSignUpValidation(){
    assertThrows(IllegalArgumentException.class,()->User.signupValidation(null));
    assertThrows(IllegalArgumentException.class,()->User.signupValidation("#2e2!@"));
  }

  @Test
  public void setInvalidPassword(){
    assertThrows(IllegalArgumentException.class,()->user.setPassword(null));
    assertThrows(IllegalArgumentException.class,()->user.setPassword(""));
    assertThrows(IllegalArgumentException.class,()->user.setPassword("12345678910111213141516"));
    assertThrows(IllegalArgumentException.class,()->user.setPassword("dsld@@s2Z!"));
  }

  @Test
  public void setInvalidUsername(){
    assertThrows(IllegalArgumentException.class,()->user.setUsername(null));
    assertThrows(IllegalArgumentException.class,()->user.setUsername(""));
    assertThrows(IllegalArgumentException.class,()->user.setUsername("12345678910111213141516"));
    assertThrows(IllegalArgumentException.class,()->user.setUsername("dsld@@s2Z"));
  }


  @Test
  public void testGetters(){
      assertEquals("username",user.getUsername());
      assertEquals("password",user.getPassword());
      assertEquals("CookBook [recipes=[]]",user.getCookBook().toString());
  }

  @Test
  public void testToString(){
    assertEquals("User [username=username, password=password, cookBook=CookBook [recipes=[]]]", user.toString());
  }




}



