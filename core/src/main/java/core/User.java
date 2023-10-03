package core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class User {
  private String username;
  private String password;
  public static final String outputLogin = "Incorrect username or password";
  public static final String outputSignup = "Username and password needs to contain 2 or more characters";
  public static final String existingUser = "User with username already exists";
  public static final String UserFile = "ui/src/main/resources/ui/Users.json";
  private CookBook cookBook;

  public User(String username, String password, CookBook cookBook) {
    setUsername(username);
    setPassword(password);
    this.cookBook = cookBook;
  }

  public User() {
  }

  public void setPassword(String password) {
    if (!validatePassword(password))
      throw new IllegalArgumentException(outputSignup);
    this.password = password;
  }

  public void setUsername(String username) {
    if (!validateUsername(username))
      throw new IllegalArgumentException(outputSignup);
    this.username = username;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public static boolean validateUsername(String username) {
    return (((username != null)) && (username.length() > 1));
  }

  public static boolean validatePassword(String password) {
    return (((password != null)) && (password.length() > 1));
  }

  public static boolean validateUser(String username, String password) {
    return validatePassword(password) && validateUsername(username);
  }

  public static CookBook createCookBook(String username) {
    CookBook bok = new CookBook();
    return bok;
  }

  public CookBook getCookBook() {
    return cookBook;
  }

  public void setCookBook(CookBook cookBook) {
    this.cookBook = cookBook;
  }

  public static Boolean login(String username, String password) {
    if (validateLogin(username, password, User.findUsers()))
      return true;
    return false;

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

  public static Boolean validateLogin(String username, String password, List<User> users) {
    return users.stream().filter(a -> a != null)
        .anyMatch(a -> a.getUsername().equals(username) && a.getPassword().equals(password));

  }

  public static Boolean validateNoExistingUser(String username) {
    return (!User.findUsers().stream().filter(a -> a != null).anyMatch(a -> a.getUsername().equals(username)));
  }

  public static void Signup(String Username, String password) {
    List<User> users = findUsers();
    // Ingredient ingredient = new Ingredient("pasta", 200, "g");
    // List<Ingredient> ings = new ArrayList<>();
    // ings.add(ingredient);
    // Recipe recipe = new Recipe("PastaBolognese", ings, "Dinner");
    // List<Recipe> recips = new ArrayList<>();
    // recips.add(recipe);
    // CookBook book = new CookBook(recips);
    // User user = new User(Username, password, book);
    User user = new User(Username, password, null);
    users.add(user);
    try (FileWriter writer = new FileWriter(UserFile)) {
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      gson.toJson(users, writer); // Serialize and write the updated user list
    } catch (IOException e) {
      e.printStackTrace();
    }
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

  public static void main(String[] args) {
    User.login("jorgen", "fjermedal");
  }


}
