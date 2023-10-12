package ui;

import core.User;
import core.UserDataFilehandling;
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

  private UserDataFilehandling fileHandler;

  /**
   * Initializes the filehandler for user data.
   *
   * @param fileHandler the filehandler for user data
   */
  public void setFileHandler(UserDataFilehandling fileHandler) {
    this.fileHandler = fileHandler;
  }

  /**
   * Attempts to log in the user with the provided username and password. If successful, starts the
   * main application with the logged in user. If unsuccessful, displays an error message.
   */
  @FXML
  void login() {
    try {
      startApp(fileHandler.getUser(usernameField.getText(), passwordField.getText()));

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
      startApp(fileHandler.signup(usernameField.getText(), passwordField.getText()));
    } catch (Exception e) {
      displayErrorMessage(e);
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
            if (popupLabel.isVisible()) {
              popupLabel.setVisible(false);
            }
          }
        });
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
      cookBookController.initialize(user, fileHandler);


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
