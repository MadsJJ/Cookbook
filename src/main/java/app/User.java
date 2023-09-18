package app;

public class User {
  private String username;
  private String password; 
  static final String output = "Invalid Username or password, both needs at least 9 characters";

  

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public User(){}

  public void setPassword(String password){
    if(!validatePassword(username)) throw new IllegalArgumentException(output);
    this.password=password;
  }

  public void setUsername(String username){
    if(!validateUsername(username)) throw new IllegalArgumentException(output);
    this.username = username; 
  }

  public String getUsername(){
    return username; 
  }
  
  
  public String getPassword() {
    return password;
  }

  public boolean validateUsername(String username){
    return (((username != null))&&(username.length() > 8));
  }

  public boolean validatePassword(String password){
    return (((password != null))&&(password.length() > 8));
  }
  
  
}
