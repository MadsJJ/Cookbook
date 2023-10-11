package core;



public class User {
  private String username;
  private String password;
  public static final String outputSignup = "Username and password must be: \n - letters and numbers \n - between 3 and 16 characters";
  private CookBook cookBook;

  public User(String username, String password, CookBook cookBook) {
    setUsername(username);
    setPassword(password);
    setCookBook(cookBook);
  }

  public void setPassword(String password) {
    signupValidation(password);
    this.password = password;
  }

  public void setUsername(String username) {
    signupValidation(username);
    this.username = username;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }


  public static void signupValidation(String string) {
    if(string==null) throw new IllegalArgumentException(outputSignup);
    if(!(string.matches("^[a-zA-Z0-9]+$")&&string.length()>2&&string.length()<16)) throw new IllegalArgumentException(outputSignup);
  }

  public CookBook getCookBook() {
    return cookBook;
  }

  public void setCookBook(CookBook cookBook) {
    if(cookBook==null) throw new IllegalArgumentException("Cookbook cant be null");
    this.cookBook = cookBook;
  }



  @Override
  public String toString() {
    return "User [username=" + username + ", password=" + password + ", cookBook=" + cookBook + "]";
  }
}

