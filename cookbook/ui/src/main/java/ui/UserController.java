package ui;

import cookbook.core.User;
import cookbook.core.UserDataFilehandling;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ui.access.CookbookAccess;
import ui.access.LocalCookbookAccess;
import ui.access.RemoteCookbookAccess;
import ui.access.ServerStatusChecker;


/**
 * This class is responsible for handling user interactions with the login and signup screens.
 */
public class UserController {

  @FXML
  private ResourceBundle resources;

  @FXML
  private URL location;

  @FXML
  private Button loginButton;

  @FXML
  private PasswordField passwordField;

  @FXML
  private Button signupButton;

  @FXML
  private TextField usernameField;

  @FXML
  private Text outputMessage;

  @FXML
  private Label popupLabel;


  String endpointUri = "http://localhost:8080/cookbook/";

  String localFilePath = "/src/main/resources/ui/UserData.json";

  private CookbookAccess accessType;

  private UserDataFilehandling fileHandler;



  public void setAccessType(CookbookAccess accessType) {
    this.accessType = accessType;
  }

  /**
   * Attempts to log in the user with the provided username and password. If successful, starts the
   * main application with the logged in user. If unsuccessful, displays an error message.
   */
  @FXML
  void login() {
    try {
      startApp(accessType.readUser(usernameField.getText(), passwordField.getText()));
    } catch (Exception e) {
      displayErrorMessage(e);
    }
  }

  /**
   * Attempts to sign up the user with the provided username and password. If successful, starts the
   * main application with the newly signed up user. If unsuccessful, displays an error message.
   */
  @FXML
  void signup() {
    try {
      startApp(accessType.registerNewUser(usernameField.getText(), passwordField.getText()));
    } catch (Exception e) {
      displayErrorMessage(e);
    }
  }

  @FXML
  void initialize() {
    try {
      if (ServerStatusChecker.serverStatus()) {
        RemoteCookbookAccess remoteAccess;
        System.out.println("Using remote endpoint @ " + endpointUri);
        remoteAccess = new RemoteCookbookAccess(new URI(endpointUri));
        this.accessType = remoteAccess;
        System.out.println(remoteAccess);

      } else {

        System.out.println("Failed to establish contact with server. \n"
            + "Using data directly from file \n" + "@" + localFilePath);
        this.fileHandler = new UserDataFilehandling(localFilePath);
        LocalCookbookAccess localAccess = new LocalCookbookAccess(fileHandler);
        this.accessType = localAccess;
      }

    } catch (Exception e) {
      System.out.println("Error occured when attempting contact with server. \n"
          + "Using data directly from file \n" + "@" + localFilePath);
      this.fileHandler = new UserDataFilehandling(localFilePath);
      LocalCookbookAccess localAccess = new LocalCookbookAccess(fileHandler);
      accessType = localAccess;

      this.accessType = localAccess;

    }
  }

  /**
   * Sets the stage for the login and signup screens. Adds an event handler to the popup label to
   * hide it when clicked.
   *
   * @param stage the stage for the login and signup screens
   */
  public void setStage(Stage stage) {
    // Add an event handler to the Label when the application starts
    popupLabel.getScene().getWindow().addEventHandler(MouseEvent.MOUSE_CLICKED,
        new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent mouseEvent) {
            hideErrorMessage();
          }
        });
  }

  @FXML
  void hideErrorMessage() {
    if (popupLabel.isVisible()) {
      popupLabel.setVisible(false);
    }
  }

  /**
   * Starts the main application with the provided user.
   *
   * @param user the user to start the main application with
   */
  public void startApp(User user) {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("CookBook.fxml"));
      Parent root = loader.load();
      Scene scene = new Scene(root);
      Stage stage = (Stage) loginButton.getScene().getWindow();
      stage.setScene(scene);
      stage.show();

      CookBookController cookBookController = loader.getController();
      cookBookController.initialize(user, accessType);



    } catch (Exception a) {
      a.printStackTrace();
    }
  }

  /**
   * Returns the error message displayed in the popup label.
   *
   */
  public String getErrorMessage() {
    return popupLabel.getText();
  }

  /**
   * Displays an error message as a popup label.
   *
   */
  @FXML
  void displayErrorMessage(Exception e) {
    popupLabel.setText(e.getMessage());
    popupLabel.setVisible(true);
  }
}
