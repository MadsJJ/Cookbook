package core;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class UserDataFilehandling {



  // public final String UserFile = "cookbook/ui/src/main/resources/ui/UserData.json";
  public final String UserFile;
  

  // public static final File file = new File(url.getPath());




  

  public UserDataFilehandling(String UserFile) {
      
    // System.out.println(UserFile);
    // System.out.println(System.getProperty("user.dir"));
    String userDir = System.getProperty("user.dir");
    // System.out.println(userDir);
    if(!userDir.contains("cookbook/ui")){
      userDir=userDir+"/cookbook/ui";
    }
    // System.out.println(Paths.get(userDir, UserFile).toString());
    this.UserFile=Paths.get(userDir, UserFile).toString();

    

    // System.out.println(Paths.get(System.getProperty("user.dir"), UserFile).toString());

    // this.UserFile=Paths.get(System.getProperty("user.dir"), UserFile).toString();

    

    
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
    return findUsers().stream().filter(a -> a != null)
    .filter(a -> a.getUsername().equals(username) && a.getPassword().equals(password))
    .findFirst().orElseThrow(() -> new IllegalArgumentException("Incorrect password or username"));
}

public void validateNoExistingUser(String username) {
  if(findUsers().stream().filter(a -> a != null).anyMatch(a -> a.getUsername().equals(username))){
    throw new IllegalArgumentException("Username already exists");
  }
}

 public User signup(String Username, String password) {
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

  public void updateFile(User user){
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
