package core;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class UserDataFilehandling {



  private String UserFile;
  

  public UserDataFilehandling(String UserFile) {
    String userDir = System.getProperty("user.dir");
    if(userDir.endsWith("gr2308")){
      userDir=userDir+"/cookbook/ui";
    }
    else if(userDir.endsWith("core")){
      userDir=userDir.substring(0, userDir.length()-4);
      userDir=userDir+"ui";
    }
  
  
    this.UserFile=userDir+UserFile;

  }

  

  public List<User> findUsers() {
    List<User> users = new ArrayList<>();
    try (FileReader reader = new FileReader(UserFile, StandardCharsets.UTF_8)) { // Specify UTF-8 encoding
      Gson gson = new Gson();
      User[] userArray = gson.fromJson(reader, User[].class);
      if (userArray != null) {
        for (User user : userArray) {
          users.add(user);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return users;
  }

  public User getUser(String username, String password) {
    return findUsers().stream()
    .filter(a -> a.getUsername().equals(username) && a.getPassword().equals(password))
    .findFirst().orElseThrow(() -> new IllegalArgumentException("Incorrect password or username"));
}

public void validateNoExistingUser(String username) {
  if(findUsers().stream().anyMatch(a -> a.getUsername().equals(username))){
    throw new IllegalArgumentException("Username already exists");
  }
}

 public User signup(String Username, String password) {
    validateNoExistingUser(Username);
    User user = new User(Username, password, new CookBook(new ArrayList<Recipe>()));
    List<User> users = findUsers();
    users.add(user);
    try (FileWriter writer = new FileWriter(UserFile, StandardCharsets.UTF_8)) { // Specify UTF-8 encoding
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      gson.toJson(users, writer); // Serialize and write the updated user list
    } catch (IOException e) {
      e.printStackTrace();
    }
    return user;
  }

  public void updateFile(User user){
    List<User> users = findUsers();
    User userToUpdate = users.stream().filter(a->a.getUsername().equals(user.getUsername())).findAny().get();
    userToUpdate.setCookBook(user.getCookBook());
       try (FileWriter writer = new FileWriter(UserFile, StandardCharsets.UTF_8)) { // Specify UTF-8 encoding
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      gson.toJson(users, writer); // Serialize and write the updated user list
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
