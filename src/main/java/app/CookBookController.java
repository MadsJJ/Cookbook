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

public class CookBookController {

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

    private User user;
  

    public void removeRecipe(ActionEvent e){

    }

    public void addRecipe(ActionEvent e){

    }

    public void handleNewRecipe(ActionEvent e){
      
    }


    public void handleCategory(ActionEvent e){

      //Randome and choose category from all recipes

    }

    public void handleCancel(ActionEvent e){


    }

    public void handleIngredient(ActionEvent e){

    }

    public void handleSetCategory(ActionEvent e){

    }


    public void handleAction(ActionEvent e){
        
        if(e.getSource() == logOutButton){
            
            try {
              FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/User.fxml"));
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

      

      public void setUser(User user) {
        this.user = user;
      }
  

    public void setheadertext(String username){
        headerText.setText(username + "´s cookbook.");
    }

}
