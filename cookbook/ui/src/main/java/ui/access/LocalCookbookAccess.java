//package ui.access;
//
//import java.io.File;
//import java.io.IOException;
//import java.net.URISyntaxException;
//import cookbook.core.User;
//import cookbook.core.UserAccounts;
//import cookbook.json.CookbookPersistence;
//
//public class LocalCookbookAccess implements CookbookAccess{
//
//  private UserAccounts accounts;
//  private User user;
//  private final CookbookPersistence persistence = new CookbookPersistence();
//
//  /**
//   * Loads useraccounts from json-file.
//   */
//  public LocalCookbookAccess() {
//    persistence.setFilePath("User.json");
//    try {
//      this.accounts = persistence.loadUserAccounts();
//    } catch (IllegalStateException | IOException e) {
//      this.accounts = new UserAccounts();
//      try {
//        persistence.saveUserAccounts(accounts);
//      } catch (IllegalStateException | IOException e1) {
//        System.out.println(e1.getMessage());
//      }
//    }
//  }
//  @Override
//  public UserAccounts readUserAccounts() throws IOException {
//    return persistence.loadUserAccounts();
//  }
//
//  @Override
//  public User readUser(String username) {
//    if(accounts.getUser(username)instanceof User) {
//      return (User) accounts.getUser(username);
//    }
//    return  null;
//  }
//
//  @Override
//  public User userLogin(String username, String password) {
//    this.user = accounts.getUser(username, password);
//    return user;
//  }
//
//  @Override
//  public void registerNewUserAccounts(UserAccounts useraccounts) {
//    this.accounts = useraccounts;
//  }
//
//  @Override
//  public void createUser(User user) {
//    if (user != null) {
//      accounts.addUser(user);
//    }
//    try {
//      persistence.saveUserAccounts(accounts);
//    } catch (IllegalStateException | IOException e) {
//      e.printStackTrace();
//    }
//  }
//
//  @Override
//  public void updateUserAttributes(User user, int indexOfUser) {
//    accounts.updateUserObject(user, indexOfUser);
//    try {
//      persistence.saveUserAccounts(accounts);
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//  }
//
//  @Override
//  public void deleteAccounts() {
//    accounts = null;  
//  }
//
//  @Override
//  public void uploadFile(File file) throws IOException, InterruptedException, URISyntaxException {
//    // TODO Auto-generated method stub
//    throw new UnsupportedOperationException("Unimplemented method 'uploadFile'");
//  }
//
//  @Override
//  public User getLoggedInUser() {
//    return this.user;
//  }
//  
//}
//