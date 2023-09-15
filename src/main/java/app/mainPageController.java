package app;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text headerText;

    @FXML
    private Button myRecepiesButton;

    @FXML
    private Button randomeRecepieButton;

    @FXML
    private Button logOutButton;

    @FXML
    void initialize() {
        assert headerText != null : "fx:id=\"headerText\" was not injected: check your FXML file 'MainPage.fxml'.";
        assert myRecepiesButton != null : "fx:id=\"myRecepiesButton\" was not injected: check your FXML file 'MainPage.fxml'.";
        assert randomeRecepieButton != null : "fx:id=\"randomeRecepieButton\" was not injected: check your FXML file 'MainPage.fxml'.";

    }

    

    public void handelAction(ActionEvent e){
        
        if(e.getSource() == logOutButton){
            
            try {
              FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/Login.fxml"));
              Parent root = loader.load();
              Scene scene = new Scene(root);
              Stage stage = (Stage) logOutButton.getScene().getWindow();
              stage.setScene(scene);
              stage.show();
              
            } catch (Exception a) {
              a.printStackTrace(); 
              // TODO: handle exception
            }
  
        }
  
      }
  

    public void setheadertext(String username){
        headerText.setText(username + "´s cookbook.");
    }

}
