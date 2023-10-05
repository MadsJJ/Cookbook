package ui;

import java.net.URL;
import java.util.ResourceBundle;
import core.User;
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
 
    
    @FXML
    void login(){
      try {
        startApp(User.getUser(usernameField.getText(), passwordField.getText()));
        
      } catch (Exception e) {
        displayErrorMessage(e);
      }
    }
    
    @FXML
    void signup(){
      try {
        startApp(User.Signup(usernameField.getText(), passwordField.getText()));
      } catch (Exception e) {
        displayErrorMessage(e);
      }
    }
    
    public void setStage(Stage stage){
        // Add an event handler to the Label when the application starts
        popupLabel.getScene().getWindow().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent mouseEvent) {
            if (popupLabel.isVisible()) popupLabel.setVisible(false);
          }
        });
    }
  


    public void startApp(User user) {  
      try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CookBook.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();

            CookBookController CookBookController = loader.getController();
            CookBookController.initialize(user);
    
            
          } catch (Exception a) {
            a.printStackTrace(); 
            // TODO: handle exception
          }

          
    }

    @FXML
    void displayErrorMessage(Exception e){
      popupLabel.setText(e.getMessage());
      popupLabel.setVisible(true);
    }

}
