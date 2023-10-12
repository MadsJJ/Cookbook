package core;

/**
 * Represents a user of the cookbook application.
 */
public class User {
  private String username;
  private String password;
  public static final String outputSignup = "Username and password must be: \n "
      + "- letters and numbers \n "
      + "- between 3 and 16 characters";
  private CookBook cookBook;

  /**
   * Constructor for User class.
   *
   * @param username The username of the user.
   * @param password The password of the user.
   * @param cookBook The cookbook of the user.
   */
  public User(String username, String password, CookBook cookBook) {
    setUsername(username);
    setPassword(password);
    setCookBook(cookBook);
  }

  /**
   * Sets the password of the user.
   *
   * @param password The password to set.
   */
  public void setPassword(String password) {
    signupValidation(password);
    this.password = password;
  }

  /**
   * Sets the username of the user.
   *
   * @param username The username to set.
   */
  public void setUsername(String username) {
    signupValidation(username);
    this.username = username;
  }

  /**
   * Gets the username of the user.
   *
   * @return The username of the user.
   */
  public String getUsername() {
    return username;
  }

  /**
   * Gets the password of the user.
   *
   * @return The password of the user.
   */
  public String getPassword() {
    return password;
  }

  /**
   * Validates the input string for signup.
   *
   * @param string The string to validate.
   * @throws IllegalArgumentException If the string is null or doesn't match the required pattern.
   */
  public static void signupValidation(String string) {
    if (string == null) {
      throw new IllegalArgumentException(outputSignup);
    }
    if (!(string.matches("^[a-zA-Z0-9]+$") && string.length() > 2 && string.length() < 16)) {
      throw new IllegalArgumentException(outputSignup);
    }
  }

  /**
   * Gets the cookbook of the user.
   *
   * @return The cookbook of the user.
   */
  public CookBook getCookBook() {
    return cookBook;
  }

  /**
   * Sets the cookbook of the user.
   *
   * @param cookBook The cookbook to set.
   * @throws IllegalArgumentException If the cookbook is null.
   */
  public void setCookBook(CookBook cookBook) {
    if (cookBook == null) {
      throw new IllegalArgumentException("Cookbook cant be null");
    }
    this.cookBook = cookBook;
  }

  /**
   * Returns a string representation of the User object.
   *
   * @return A string representation of the User object.
   */
  @Override
  public String toString() {
    return "User [username=" + username + ", password=" + password + ", cookBook=" + cookBook + "]";
  }
}