package core;



public class User {
  private String username;
  private String password;
  public static final String outputSignup = "Username and password must be: \n - letters and numbers \n - between 3 and 16 characters";
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



  @Override
  public String toString() {
    return "User [username=" + username + ", password=" + password + ", cookBook=" + cookBook + "]";
  }
}

