package app;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class CookBookController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addIngredientButton;

    @FXML
    private TextField addIngredientTextField;

    @FXML
    private Button addNewRecipeButton;

    @FXML
    private Pane addNewRecipePane;

    @FXML
    private TextField amountTextField;

    @FXML
    private MenuItem appetizerDropdownButton;

    @FXML
    private Button cancelNewRecipeButton;

    @FXML
    private Button cancelRandomRecepieButton;

    @FXML
    private Button randomRecipeButton;

    @FXML
    private Button addRecepieButton;

   @FXML
    private ComboBox<?> categoryCombobox;

    @FXML
    private TextField deleteIngredientTextField;

    @FXML
    private MenuItem dessertDropdownButton;

    @FXML
    private MenuItem dinnerDropdownButton;

    @FXML
    private Text headerText;

    @FXML
    private ListView<?> ingredientListView;

    @FXML
    private ListView<?> listView;

    @FXML
    private Button logOutButton;

    @FXML
    private Button randomAppetizerButton;

    @FXML
    private Button randomDessertButton;

    @FXML
    private Button randomDinnerButton;

    @FXML
    private Pane randomRecipePane;

    @FXML
    private TextArea randomRecipeTextArea;

    @FXML
    private Button removeIngredientButton;

    @FXML
    private TextField titleTextField;

    @FXML
    private TextField unitTextField;

    private User user;

    @FXML
    void addRecipe() {
        addNewRecipePane.setVisible(true);
        addRecepieButton.setVisible(false);
        randomRecipeButton.setVisible(false);
        randomRecipeButton.setVisible(false);  
    }


    @FXML
    void handleCancel(ActionEvent event) {
      if(event.getSource() == cancelNewRecipeButton){
        addNewRecipePane.setVisible(false);
        addRecepieButton.setVisible(true);
        randomRecipeButton.setVisible(true); 
      }
      else if (event.getSource() == cancelRandomRecepieButton){
        randomRecipePane.setVisible(false); 
        addRecepieButton.setVisible(true);
        randomRecipeButton.setVisible(true); 

      }
    }

        @FXML
    void removeRecipe(ActionEvent event) {

    }

    @FXML
    void handleCategory(ActionEvent event) {

    }

    @FXML
    void handleIngredient(ActionEvent event) {

    }

    @FXML
    void handleNewRecipe(ActionEvent event) {

    }

    @FXML
    void handleSetCategory(ActionEvent event) {

    }

    @FXML
    void randomRecipe() {
        randomRecipePane.setVisible(true);
        addRecepieButton.setVisible(false);
        randomRecipeButton.setVisible(false);
        randomRecipeButton.setVisible(false);
    }

    public void initialize(){
      randomRecipePane.setVisible(false);
      addNewRecipePane.setVisible(false);
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
