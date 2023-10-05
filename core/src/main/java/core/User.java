package core;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class User {
  private String username;
  private String password;
  public static final String outputSignup = "Username and password must be: \n - letters and numbers \n - between 3 and 16 characters";
  public static final String UserFile = "ui/src/main/resources/ui/Users.json";
  private CookBook cookBook;

  public User(String username, String password, CookBook cookBook) {
    setUsername(username);
    setPassword(password);
    this.cookBook = cookBook;
  }

  public void setPassword(String password) {
    if (!signupValidation(password))
      throw new IllegalArgumentException(outputSignup);
    this.password = password;
  }

  public void setUsername(String username) {
    if (!signupValidation(username))
      throw new IllegalArgumentException(outputSignup);
    this.username = username;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }


  public static boolean signupValidation(String string) {
    return string.matches("^[a-zA-Z0-9]+$")&&string.length()>2&&string.length()<16;

  }

  public CookBook getCookBook() {
    return cookBook;
  }

  public void setCookBook(CookBook cookBook) {
    this.cookBook = cookBook;
  }

  public static User getUser(String username, String password) {
      return User.findUsers().stream().filter(a -> a != null)
      .filter(a -> a.getUsername().equals(username) && a.getPassword().equals(password))
      .findFirst().orElseThrow(() -> new IllegalArgumentException("Incorrect password or username"));
  }

  public static List<User> findUsers() {
    List<User> users = new ArrayList<>();
    try (FileReader reader = new FileReader(UserFile)) {
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


  public static void validateNoExistingUser(String username) {
      if(User.findUsers().stream().filter(a -> a != null).anyMatch(a -> a.getUsername().equals(username))){
        throw new IllegalArgumentException("Username already exists");
      }
  }

  public static User Signup(String Username, String password) {
    validateNoExistingUser(Username);
    CookBook book = new CookBook(new ArrayList<Recipe>());
    User user = new User(Username, password, book);
    List<User> users = findUsers();
    users.add(user);
    try (FileWriter writer = new FileWriter(UserFile)) {
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
    try (FileWriter writer = new FileWriter(UserFile)) {
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      gson.toJson(users, writer); // Serialize and write the updated user list
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  @Override
  public String toString() {
    return "User [username=" + username + ", password=" + password + ", cookBook=" + cookBook + "]";
  }
}

