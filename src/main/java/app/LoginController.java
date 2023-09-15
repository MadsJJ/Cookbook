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

public class LoginController {

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
    

    @FXML
    void initialize() {
        assert loginButton != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'Login.fxml'.";
        assert password != null : "fx:id=\"password\" was not injected: check your FXML file 'Login.fxml'.";
        assert signupButton != null : "fx:id=\"signupButton\" was not injected: check your FXML file 'Login.fxml'.";
        assert username != null : "fx:id=\"username\" was not injected: check your FXML file 'Login.fxml'.";

    }

    public void handleAction(ActionEvent e){
      Login login = new Login(); 
      
      if(e.getSource() == loginButton){
        if(!login.validateUsername(username.getText())||!login.validatePassword(password.getText())){
          outputMessage.setText(Login.output);
          // login.setOutput();
          // outputMessage.setText(login.getOutput());
        }
        else{
          
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/MainPage.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.setScene(scene);
            stage.show();

            mainPageController mainPageController = loader.getController();
            mainPageController.setheadertext(username.getText()); 
            

            
          } catch (Exception a) {
            a.printStackTrace(); 
            // TODO: handle exception
          }

      }

      
      

    }

    

}}
