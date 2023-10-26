package cookbook.core;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Class which contains a list of all registred Users in the Cookbook-application
 * This class implements UserObserver, this is needed to
 * observe changes in User object
 */
public class UserAccounts implements UserObserver {

  protected final List<User> accounts = new ArrayList<>();

  /**
   * Access method for accounts.
   *
   * @return the accounts
   */
  public List<User> getAccounts() {
    return new ArrayList<>(accounts);
  }

  /**
   * Adds new User to this list of Users.
   *
   * @param user the user to add
   * @throws IllegalStateException if the user already exists.
   */
  public void addUser(User user) {
    if (contains(user)) {
      throw new IllegalStateException("User already exists!");
    }
    this.accounts.add(user);
  }

  /**
   * Removes already existing User.
   *
   * @param user the user to remove
   * @throws IllegalStateException if the user dosen't already exists.
   */
  public void removeUser(User user) {
    if (!contains(user)) {
      throw new IllegalArgumentException("User does not exists!");
    }
    this.accounts.remove(user);
  }

  /**
   * Iterator to easilly move between objects in list.
   *
   * @return iterator of accounts
   */
  public Iterator<User> iterator() {
    return accounts.iterator();
  }

  /**
   * Finds the index of the user in list.
   *
   * @param user to find index for
   */
  public int indexOf(User user) {
    return accounts.indexOf(user);
  }

  /**
   * Checks if the given user exists in the list of users.
   *
   * @param user to check
   * @return true or false based on if the user exists
   */
  public boolean contains(User user) {
    return accounts.stream().anyMatch(u -> u.getUsername().equals(user.getUsername()));
  }

  /**
   * Checks if the user login is valid.
   *
   * @param username username to check
   * @param password password to check
   * @return boolean
   */
  public boolean checkValidUserLogin(String username, String password) {
    User user = null;

    for (User ab : accounts) {
      if (ab.getUsername().equals(username)) {
        user = ab;
      }
    }
    return user != null && user.getPassword().equals(password);
  }

  /**
   * Gets the user by username and password.
   *
   * @param username the username
   * @param password the password
   * @return the user
   */
  public User getUser(String username, String password) {
    User user = null;

    for (User ab : accounts) {
      if (ab.getUsername().equals(username)) {
        user = ab; //sjekk ut
      }
    }
    if (user == null) {
      return null;
    }
    if (user.getPassword().equals(password)) {
      return user;
    }
    return null;
  }
  /**
   * get accounts by username.
   *
   * @param username the username
   * @return accounts if they exist, null else
   */

  public User getUser(String username) {
    return getAccounts().stream().filter(u -> u.getUsername().equals(username))
                                 .findAny()
                                 .orElse(null);
  }

  /**
   * This method is used when something is changed.
   * The accounts object will be updated accordingly.
   *
   * @param user        to change
   * @param indexOfUser in list
   */
  public void updateUserObject(User user, int indexOfUser) {
    accounts.set(indexOfUser, user);
  }

  /**
   * This method is used to inform the observers.
   * This method is called when anything is changed.
   *
   * @param user the user
   */
  public void userInfoChanged(User user) {
    int index = accounts.indexOf(user);
    this.updateUserObject(user, index);
  }
  
}
