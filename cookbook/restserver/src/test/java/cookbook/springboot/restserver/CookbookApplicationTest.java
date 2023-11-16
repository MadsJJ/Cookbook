package cookbook.springboot.restserver;

import static org.mockito.Mockito.times;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cookbook.core.CookBook;
import cookbook.core.User;
import java.util.ArrayList;
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
@ContextConfiguration(classes = {CookbookController.class, CookbookService.class,
    CookbookApplication.class, GsonConfiguration.class})
@TestInstance(Lifecycle.PER_CLASS)
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



  private void assertNotNull(CookbookService cookbookService2) {}



  @Test
  public void testGetAccounts() throws Exception {
    Mockito.when(cookbookService.getUsers()).thenReturn(new ArrayList<>());

    mockMvc.perform(MockMvcRequestBuilders.get("/cookbook").accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

    Mockito.verify(cookbookService, times(1)).getUsers();
  }

  @Test
  public void testLoginUser() throws Exception {
    User user = new User("test", "test", new CookBook(new ArrayList<>()));

    // Mock the behavior of cookbookService.getExistingUser()
    Mockito.when(cookbookService.getExistingUser("test", "test")).thenReturn(user);


    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/cookbook/login").contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"test\", \"password\": \"test\"}")
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

    Mockito.verify(cookbookService, times(1)).getExistingUser("test", "test");
  }

  @Test
  public void testLoginUserError() throws Exception {

    // Mock the behavior of cookbookService.getExistingUser()
    Mockito.when(cookbookService.getExistingUser("test", "test")).thenReturn(null);


    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/cookbook/login").contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\": \"test\", \"password\": \"test\"}")
                .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isConflict()).andReturn();

    Mockito.verify(cookbookService, times(1)).getExistingUser("test", "test");
  }

  @Test
  public void testSignup() throws Exception {
    User user = new User("test", "test", new CookBook(new ArrayList<>()));

    // Mock the behavior of cookbookService.getExistingUser()
    Mockito.when(cookbookService.checkIfUsernameTaken("test")).thenReturn(false);
    Mockito.when(cookbookService.signup("test", "test")).thenReturn(user);

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String jsonUser = gson.toJson(user);


    mockMvc
        .perform(MockMvcRequestBuilders.post("/cookbook/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"username\": \"test\", \"password\": \"test\"}")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().json(jsonUser)).andReturn();

    Mockito.verify(cookbookService, times(1)).checkIfUsernameTaken("test");
    Mockito.verify(cookbookService, times(1)).signup("test", "test");
  }

  @Test
  public void testSignupExistingUsername() throws Exception {
    // Mock the behavior of cookbookService.getExistingUser()
    Mockito.when(cookbookService.checkIfUsernameTaken("test")).thenReturn(true);



    mockMvc
        .perform(MockMvcRequestBuilders.post("/cookbook/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"username\": \"test\", \"password\": \"test\"}")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isConflict()).andReturn();

    Mockito.verify(cookbookService, times(1)).checkIfUsernameTaken("test");
    Mockito.verify(cookbookService, times(0)).signup("test", "test");
  }

  @Test
  public void testSignupInvalidFormat() throws Exception {

    // Mock the behavior of cookbookService.getExistingUser()
    Mockito.when(cookbookService.checkIfUsernameTaken("1")).thenReturn(false);
    Mockito.when(cookbookService.signup("1", "test")).thenThrow(IllegalArgumentException.class);



    mockMvc
        .perform(MockMvcRequestBuilders.post("/cookbook/register")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"username\": \"1\", \"password\": \"test\"}")
            .accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isBadRequest()).andReturn();

    Mockito.verify(cookbookService, times(1)).checkIfUsernameTaken("1");
    Mockito.verify(cookbookService, times(1)).signup("1", "test");
  }

  @Test
  public void testUpdateUser() throws Exception {
    User user = new User("test", "test", new CookBook(new ArrayList<>()));

    // Mock the behavior of cookbookService.getExistingUser()
    Mockito.doNothing().when(cookbookService).autoSaveUser(Mockito.any(User.class));

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String jsonUser = gson.toJson(user);



    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/cookbook/update").contentType(MediaType.APPLICATION_JSON)
                .content(jsonUser).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

  }

  @Test
  public void testUpdateUserError() throws Exception {
    User user = new User("test", "test", new CookBook(new ArrayList<>()));

    // Mock the behavior of cookbookService.getExistingUser()
    Mockito.doThrow(new IllegalArgumentException()).when(cookbookService)
        .autoSaveUser(Mockito.any(User.class));

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String jsonUser = gson.toJson(user);



    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/cookbook/update").contentType(MediaType.APPLICATION_JSON)
                .content(jsonUser).accept(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isInternalServerError()).andReturn();

  }

}


