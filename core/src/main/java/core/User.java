package core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class User {
  private String username;
  private String password; 
  public static final String outputLogin="Incorrect username or password";
  public static final String outputSignup="Username and password needs to contain 2 or more characters";
  public static final String existingUser="User with username already exists";
  private CookBook cookBook;

  

  

  public User(String username, String password,CookBook cookBook) {
    setUsername(username);
    setPassword(password);
    this.cookBook=cookBook;
  }

  public User(){}

  public void setPassword(String password){
    if(!validatePassword(password)) throw new IllegalArgumentException(outputSignup);
    this.password=password;
  }

  public void setUsername(String username){
    if(!validateUsername(username)) throw new IllegalArgumentException(outputSignup);
    this.username = username; 
  }

  public String getUsername(){
    return username; 
  }
  
  public String getPassword() {
    return password;
  }

  public static boolean validateUsername(String username){
    return (((username != null))&&(username.length() > 1));
  }

  public static boolean validatePassword(String password){
    return (((password != null))&&(password.length() > 1));
  }

  public static boolean validateUser(String username,String password){
      return validatePassword(password)&&validateUsername(username);
  }



  public static CookBook createCookBook(String username) {
    CookBook bok = new CookBook();
    return bok;
  }

  public CookBook getCookBook(){
    return cookBook;
  }

  public void setCookBook(CookBook cookBook) {
    this.cookBook = cookBook;
  }
  


  public static Boolean login(String username, String password){
    if(validateLogin(username,password,User.findUsers())) return true;
    return false;    
    
}

 public static List<String> findUsers(){
  String file = "ui/src/main/resources/ui/Users.txt";
    FileReader fileReader;
    List<String> lines = new ArrayList<>();
    try {
      fileReader = new FileReader(file);
      try (// Convert fileReader to bufferedReader
    BufferedReader buffReader = new BufferedReader(fileReader)) {
      String line = buffReader.readLine();
      lines.add(line);

      while (line != null) {
        System.out.println(line);
        line = buffReader.readLine();
        lines.add(line);
      }

      buffReader.close();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return lines;
 }




  public static Boolean validateLogin(String username, String password,List<String> lines){
    return lines.stream().filter(a->a!=null).
    anyMatch(a->a.substring(0, a.indexOf(",")).equals(username)&&a.substring(a.indexOf(",")+1,a.length()).equals(password));
    
  }

  public static Boolean validateNoExistingUser(String username){
    return (!User.findUsers().stream().filter(a->a!=null).anyMatch(a->a.substring(0, a.indexOf(",")).equals(username)));
  }



 public static void Signup(String Username, String password) {
    String file = "ui/src/main/resources/ui/Users.txt";
    try {
   
        FileWriter fileWriter = new FileWriter(file, true);
        PrintWriter writer = new PrintWriter(fileWriter);


        writer.println(Username + "," + password);

        writer.close();
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
