package app;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
    private TextField password;

    @FXML
    private Button signupButton;

    @FXML
    private TextField username;
    
    @FXML
    private Text outputMessage;
    

    public void handleAction(ActionEvent e){
      // User user = new User(); 
      Object button = e.getSource();

      // login or signup
      String username1 = username.getText();
      String password1 = password.getText();
      if (button == loginButton || button == signupButton) {
        // login check
        if (button == loginButton) {
          // "Incorrect username or password"
          if(!User.login(username1, password1)){
            outputMessage.setText(User.outputLogin);
            return;
          }
        }
        // signup check
        else {
          // "Username and password needs to contain 2 or more characters"
          if(!User.validateNoExistingUser(username1)){ 
            outputMessage.setText(User.existingUser);
            return;
          }
          else if(!User.validateUser(username1, password1)){ 
          outputMessage.setText(User.outputSignup);
          return;
          }
          User.Signup(username1, password1);
            
          }
        }
        User user = new User(username1, password1, User.createCookBook(username1));
        startApp(user);
      }    



    public void startApp(User user) {
      try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/CookBook.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();

            CookBookController CookBookController = loader.getController();
            CookBookController.setUser(user);
            CookBookController.setheadertext(user.getUsername());
            
          } catch (Exception a) {
            a.printStackTrace(); 
            // TODO: handle exception
          }
    }
}
