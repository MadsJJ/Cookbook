//package ui.access;
//
//import java.io.File;
//import java.io.IOException;
//import java.net.URI;
//import java.net.URISyntaxException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import cookbook.core.User;
//import cookbook.core.UserAccounts;
//
//public class RemoteCookbookAccess implements CookbookAccess {
//
//  private final URI baseUri;
//  private ObjectMapper objectMapper;
//  private UserAccounts accounts;
//  private User user = new User();
//
//  /**
//   * This constructor initialize the objectmapper used for serializing, 
//   * and sets the baseUri.
//   *
//   * @param baseUri URL used for HTTP requests.
//   */
//  public RemoteCookbookAccess(final URI baseUri) {
//    this.baseUri = baseUri;
//    this.objectMapper = UserPersistence.createObjectMapper();
//    this.accounts = readUserAccounts();
//  }
//
//  /**
//   * Creates a URI by resolving the input string against the uri for 
//   * fetching from the server.
//   *
//   * @param uri the path.
//   * @return the URI on the server with the given path.
//   */
//  public URI resolveUriAccounts(String uri) {
//    return baseUri.resolve(uri);
//  }
//
//  /**
//   * Sends a GET-request to fetch a Accounts object from the
//   * server.
//   */
//  @Override
//  public UserAccounts readUserAccounts() throws IOException {
//    // TODO Auto-generated method stub
//    throw new UnsupportedOperationException("Unimplemented method 'readUserAccounts'");
//  }
//
//  @Override
//  public User readUser(String username) {
//    // TODO Auto-generated method stub
//    throw new UnsupportedOperationException("Unimplemented method 'readUser'");
//  }
//
//  @Override
//  public User userLogin(String username, String password) {
//    // TODO Auto-generated method stub
//    throw new UnsupportedOperationException("Unimplemented method 'userLogin'");
//  }
//
//  @Override
//  public void registerNewUserAccounts(UserAccounts useraccounts) {
//    // TODO Auto-generated method stub
//    throw new UnsupportedOperationException("Unimplemented method 'registerNewUserAccounts'");
//  }
//
//  @Override
//  public void createUser(User user) {
//    // TODO Auto-generated method stub
//    throw new UnsupportedOperationException("Unimplemented method 'createUser'");
//  }
//
//  @Override
//  public void updateUserAttributes(User user, int indexOfUser) {
//    // TODO Auto-generated method stub
//    throw new UnsupportedOperationException("Unimplemented method 'updateUserAttributes'");
//  }
//
//  @Override
//  public void deleteAccounts() {
//    // TODO Auto-generated method stub
//    throw new UnsupportedOperationException("Unimplemented method 'deleteAccounts'");
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
//    // TODO Auto-generated method stub
//    throw new UnsupportedOperationException("Unimplemented method 'getLoggedInUser'");
//  }
//  
//}
//