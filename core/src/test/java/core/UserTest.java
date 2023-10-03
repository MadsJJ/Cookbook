package core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    void testValidUsername() {
        Assertions.assertTrue(User.signupValidation("validUsername123"));
    }

    @Test
    void testInvalidUsername() {
        Assertions.assertFalse(User.signupValidation("invalid-username"));
    }

    @Test
    void testValidPassword() {
        Assertions.assertTrue(User.signupValidation("validPassword123"));
    }

    @Test
    void testInvalidPassword() {
        Assertions.assertFalse(User.signupValidation("short"));
    }

    @Test
    void testGetUser() {
        User user = new User("testUser", "password123", null);
        User result = User.getUser("testUser", "password123");
        Assertions.assertEquals(user.getUsername(), result.getUsername());
        Assertions.assertEquals(user.getPassword(), result.getPassword());
    }
}


