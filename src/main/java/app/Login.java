package app;

public class Login {
  
  private String username;
  private String password; 
  static final String output="Invalid Username or password, both needs at least 9 characters";

  

  public Login(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public Login(){}

  public void setPassword(String password){
    this.password=password;
  }

  public void setUsername(String username){
    if(!validateUsername(username)) throw new IllegalArgumentException(output);
    this.username = username; 
  }

  public String getUsername(){
    return username; 
  }
  
  public boolean validateUsername(String username){
    return (((username != null))&&(username.length() > 8));
  }

  public boolean validatePassword(String password){
    return (((password != null))&&(password.length() > 8));
  }
  
  
}
