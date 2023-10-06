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

  public static final String UserFile = "ui/src/main/resources/ui/UserData.json";
  

  // public static final File file = new File(url.getPath());



  

  public static List<User> findUsers() {
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

  public static User getUser(String username, String password) {
    return UserDataFilehandling.findUsers().stream().filter(a -> a != null)
    .filter(a -> a.getUsername().equals(username) && a.getPassword().equals(password))
    .findFirst().orElseThrow(() -> new IllegalArgumentException("Incorrect password or username"));
}

public static void validateNoExistingUser(String username) {
  if(UserDataFilehandling.findUsers().stream().filter(a -> a != null).anyMatch(a -> a.getUsername().equals(username))){
    throw new IllegalArgumentException("Username already exists");
  }
}

 public static User signup(String Username, String password) {
    validateNoExistingUser(Username);
    CookBook book = new CookBook(new ArrayList<Recipe>());
    User user = new User(Username, password, book);
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

  public static void updateFile(User user){
    List<User> users = findUsers();
    User userToUpdate = users.stream().filter(a->a!=null).filter(a->a.getUsername().equals(user.getUsername())).findAny().get();
    userToUpdate.setCookBook(user.getCookBook());
       try (FileWriter writer = new FileWriter(UserFile, StandardCharsets.UTF_8)) { // Specify UTF-8 encoding
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      gson.toJson(users, writer); // Serialize and write the updated user list
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

}
