package cookbook.springboot.restserver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cookbook.core.CookBook;
import cookbook.core.User;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@TestInstance(Lifecycle.PER_CLASS)
@ContextConfiguration(classes = 
      {CookbookController.class, CookbookService.class, CookbookApplication.class})  
@WebMvcTest
class RestServerApplicationTests {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CookbookService cookbookService;
  
  @Test
  public void contextLoads() throws Exception {
    assertNotNull(cookbookService);
  }
  
  @BeforeAll
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
    final User user1 = new User("test1", "test", new CookBook(new ArrayList<>()));
    final User user2 = new User("test2", "test", new CookBook(new ArrayList<>()));
    final User user3 = new User("test3", "test", new CookBook(new ArrayList<>()));
    cookbookService.signup("test1", "test");
    cookbookService.signup("test2", "test");
    cookbookService.signup("test3", "test");
  
  }
  
  @Test
  public void testGetAccounts() throws Exception {
      mockMvc.perform(MockMvcRequestBuilders.get("/cookbook")
             .accept(MediaType.APPLICATION_JSON))
             .andExpect(MockMvcResultMatchers.status().isOk())
             .andReturn();
    }
    
// @Test
//   public void testLoginUser() throws Exception {
//     User user = new User("testUser", "testPassword", new CookBook(new ArrayList<>()));
    
//     // Mock the behavior of cookbookService.getExistingUser()
//     // This assumes you have a method similar to getExistingUser in your CookbookService
//     // Make sure to adapt this based on your actual implementation
//     Mockito.when(cookbookService.getExistingUser("test1", "test")).thenReturn(user);

//     Gson gson = new GsonBuilder().setPrettyPrinting().create();
//     String jsonUser = gson.toJson(user);
    
//     mockMvc.perform(MockMvcRequestBuilders.post("/cookbook/login")
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(jsonUser)
//            .accept(MediaType.APPLICATION_JSON))
//            .andExpect(MockMvcResultMatchers.status().isOk())
//            .andReturn();
//   }

//  @Test
// public void testUserSignUp() throws Exception {
//     // Sign up a new user
//     cookbookService.signup("test4", "test");

//     // Assert that the user was created successfully
//     User createdUser = cookbookService.getExistingUser("test4", "test");
//     assertNotNull(createdUser);
//     assertEquals("test4", createdUser.getUsername());

//     // Attempting to sign up with the same username and password should throw an IllegalArgumentException
//     assertThrows(IllegalArgumentException.class,
//             () -> cookbookService.signup("test4", "test"));

//     // Use mockMvc to simulate a registration request
//     mockMvc.perform(MockMvcRequestBuilders.post("/cookbook/register")
//             .param("username", "test4")
//             .param("password", "test")
//             .contentType(MediaType.APPLICATION_JSON)
//             .characterEncoding("UTF-8")
//             .accept(MediaType.APPLICATION_JSON))
//             .andExpect(MockMvcResultMatchers.status().isOk());

//     // Additional assertions can be added based on the expected behavior of your system
// }


// Attempting to register with the same username again should not be allowed
// mockMvc.perform(MockMvcRequestBuilders.post("/cookbook/register")
//         .contentType(MediaType.APPLICATION_JSON)
//         .characterEncoding("UTF-8")
//         .content("test4|test")
//         .accept(MediaType.APPLICATION_JSON))
//         .andExpect(MockMvcResultMatchers.status().isConflict());  // or another appropriate status code


    // mockMvc.perform(MockMvcRequestBuilders.post("/cookbook/register")
    //         .param("username", "test5")
    //         .param("password", "test")
    //         .contentType(MediaType.APPLICATION_JSON)
    //         .characterEncoding("UTF-8")
    //         .content(jsonUser)
    //         .accept(MediaType.APPLICATION_JSON))
    //         .andExpect(MockMvcResultMatchers.status().isOk());

    // mockMvc.perform(MockMvcRequestBuilders.post("/cookbook/register")
    //         .param("username", "test5")
    //         .param("password", "test")
    //         .contentType(MediaType.APPLICATION_JSON)
    //         .characterEncoding("UTF-8")
    //         .content(jsonUser)
    //         .accept(MediaType.APPLICATION_JSON))
    //         .andExpect(MockMvcResultMatchers.status().isOk());


    // mockMvc.perform(MockMvcRequestBuilders.post("/cookbook/register?username={test4}&password={test}")
    //                             .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
    //                             .content(userAsJson).accept(MediaType.APPLICATION_JSON))
    //                             .andExpect(MockMvcResultMatchers.status().isBadRequest());

    // mockMvc.perform(MockMvcRequestBuilders.post(getUrl("create-user", "admin"))
    //        .contentType(MediaType.APPLICATION_JSON).characterEncoding("UTF-8")
    //        .content(adminUserAsJson).accept(MediaType.APPLICATION_JSON))
    //        .andExpect(MockMvcResultMatchers.status().isOk());

   //localhost:8080/cookbook/register?username={username}&password={password}
  // @PostMapping(path = "register")
  // public User registerUser(@RequestParam("username") String username, 
  //     @RequestParam("password") String password) {
  //         cookbookService.checkIfUsernameTaken(username);
  //         return cookbookService.signup(username, password);
  //   }
//   @Test
//   public void testRegisterUser() throws Exception {
//     User user = new User("testUser", "testPassword", new CookBook(new ArrayList<>()));
    
//     // Mock the behavior of cookbookService.signup()
//     // This assumes you have a method similar to signup in your CookbookService
//     // Make sure to adapt this based on your actual implementation
//     Mockito.when(cookbookService.signup("testUser", "testPassword")).thenReturn(user);

//     Gson gson = new GsonBuilder().setPrettyPrinting().create();
//     String jsonUser = gson.toJson(user);

//     mockMvc.perform(MockMvcRequestBuilders.post("/cookbook/register")
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(jsonUser)
//            .accept(MediaType.APPLICATION_JSON))
//            .andExpect(MockMvcResultMatchers.status().isOk())
//            .andReturn();
//   }

//  @Test
// public void testUpdateUser() throws Exception {
//     User user = new User("testUser", "testPassword", new CookBook(new ArrayList<>()));
//     Gson gson = new GsonBuilder().setPrettyPrinting().create();

//     // Mock the behavior of cookbookService.autoSaveUser()
//     // This assumes you have a method similar to autoSaveUser in your CookbookService
//     // Make sure to adapt this based on your actual implementation
//     Mockito.doNothing().when(cookbookService).autoSaveUser(user);

//     mockMvc.perform(MockMvcRequestBuilders.post("/cookbook/update")
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(gson.toJson(user))
//            .accept(MediaType.APPLICATION_JSON))
//            .andExpect(MockMvcResultMatchers.status().isOk())
//            .andReturn();

//     // Verify that autoSaveUser was called with the correct user
//     Mockito.verify(cookbookService, Mockito.times(1)).autoSaveUser(user);
// }
  

  



}
