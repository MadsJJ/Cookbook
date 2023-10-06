// package core;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import static org.junit.jupiter.api.Assertions.*;

// import java.util.List;
// import java.io.File;
// import java.io.IOException;
// import java.nio.file.Files;
// import java.util.ArrayList;

// public class UserTest {

//     private static final String TEST_USER_FILE = "testUsers.json";

//     @BeforeEach
//     public void setUp() {
//         // Delete the test user file before each test
//         File testUserFile = new File(TEST_USER_FILE);
//         if (testUserFile.exists()) {
//             testUserFile.delete();
//         }
//     }

//     @Test
//     public void testValidUser() {
//         CookBook cookBook = new CookBook(new ArrayList<>());
//         User user = new User("testuser", "password123", cookBook);

//         assertEquals("testuser", user.getUsername());
//         assertEquals("password123", user.getPassword());
//         assertEquals(cookBook, user.getCookBook());
//     }

//     @Test
//     public void testInvalidUsername() {
//         assertThrows(IllegalArgumentException.class, () -> {
//             new User("u$ername", "password123", new CookBook(new ArrayList<>()));
//         });
//     }

//     @Test
//     public void testInvalidPassword() {
//         assertThrows(IllegalArgumentException.class, () -> {
//             new User("testuser", "pwd", new CookBook(new ArrayList<>()));
//         });
//     }

//     @Test
//     public void testSignupValidation() {
//         assertTrue(User.signupValidation("validUsername"));
//         assertTrue(User.signupValidation("password123"));
//         assertFalse(User.signupValidation("u$ername"));
//         assertFalse(User.signupValidation("pwd"));
//     }

//     @Test
//     public void testGetUser() {
//         List<User> users = new ArrayList<>();
//         CookBook cookBook = new CookBook(new ArrayList<>());
//         users.add(new User("user1", "password1", cookBook));
//         users.add(new User("user2", "password2", cookBook));

//         User user = User.Signup("user3", "password3");

//         assertEquals(user, User.getUser("user3", "password3"));
//         assertThrows(IllegalArgumentException.class, () -> {
//             User.getUser("nonexistent", "password");
//         });
//     }

//     @Test
//     public void testSignup() throws IOException {
//         User user = User.Signup("newuser", "newpassword");

//         assertTrue(user.getUsername().equals("newuser") && user.getPassword().equals("newpassword"));
//         assertTrue(new File(TEST_USER_FILE).exists());

//         List<User> users = User.findUsers();
//         assertEquals(1, users.size());
//         assertEquals("newuser", users.get(0).getUsername());
//     }

//     @Test
//     public void testUpdateFile() {
//         CookBook cookBook = new CookBook(new ArrayList<>());
//         User user = new User("testuser", "password123", cookBook);

//         user.updateFile(user);

//         List<User> users = User.findUsers();
//         assertEquals(1, users.size());
//         assertEquals(cookBook, users.get(0).getCookBook());
//     }
// }



