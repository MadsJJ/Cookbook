package app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserTest {

  private User user; 

  @BeforeEach
  public void setUp(){
    user = new User(); 
  }

  @Test 
  public void testSetAndGetUsername(){
    user.setUsername("UsernameValid");
    assertEquals("UsernameValid", user.getUsername()); 
  }


  @Test
  public void testSetAndGetPassword(){
    user.setPassword("PasswordValid");
    assertEquals("PasswordValid", user.getPassword()); 
  }

  @Test
  public void testValidatePassword(){
    assertTrue(user.validatePassword("PasswordValid"));
    assertFalse(user.validatePassword("L"));
    assertFalse(user.validatePassword(null)); 
  }

  @Test
  public void testValidateUsername(){
    assertTrue(user.validateUsername("UsernameValid"));
    assertFalse(user.validateUsername("L"));
    assertFalse(user.validateUsername(null)); 

  }

  @Test
  public void testInvalidUsername(){
    assertThrows(IllegalArgumentException.class, () -> user.setUsername("L"));
    assertThrows(IllegalArgumentException.class, () -> user.setUsername(null));
  }

  @Test
  public void testInvalidPassword(){
    assertThrows(IllegalArgumentException.class, () -> user.setPassword("L"));
    assertThrows(IllegalArgumentException.class, () -> user.setPassword(null));
  }


}
