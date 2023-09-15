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
      User user = new User(); 
      
      if(e.getSource() == loginButton){
        if(!user.validateUsername(username.getText())||!user.validatePassword(password.getText())){
          outputMessage.setText(User.output);
        }
        else{
          
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/CookBook.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();

            CookBookController CookBookController = loader.getController();
            CookBookController.setheadertext(username.getText()); 
            
          } catch (Exception a) {
            a.printStackTrace(); 
            // TODO: handle exception
          }

      }

      
      

    }

    

}}
