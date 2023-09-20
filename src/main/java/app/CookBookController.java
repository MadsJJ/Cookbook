package app;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private ComboBox<String> categoryCombobox;

    @FXML
    private TextField deleteIngredientTextField;

    @FXML
    private MenuItem dessertDropdownButton;

    @FXML
    private MenuItem dinnerDropdownButton;

    @FXML
    private Text headerText;

    @FXML
    private ListView<Ingredient> ingredientListView;

    @FXML
    private ListView<Recipe> recipeListView;

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
    void addRecipe(ActionEvent event) {
      if(event.getSource() == addRecepieButton){
        addNewRecipePane.setVisible(true);
        addRecepieButton.setVisible(false);
        randomRecipeButton.setVisible(false);
        ObservableList<String> categories = FXCollections.observableList(Recipe.getCategories());
        categoryCombobox.setItems(categories); 
          
        };
        
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
      if(event.getSource() == addIngredientButton){
      Ingredient ing = new Ingredient(addIngredientTextField.getText(), Double.parseDouble(amountTextField.getText()), unitTextField.getText());
      ingredientListView.getItems().add(ing);
      }
      else if(event.getSource() == removeIngredientButton){
        Ingredient ingredientToRemove = ingredientListView.getItems().stream().filter(a->a.getName().equals(deleteIngredientTextField.getText())).findFirst().orElseThrow();
        ingredientListView.getItems().remove(ingredientToRemove);
      }

    }

    @FXML
    void handleNewRecipe(ActionEvent event) {
      Recipe recipe = new Recipe(titleTextField.getText(), ingredientListView.getItems(), categoryCombobox.getSelectionModel().getSelectedItem());
      recipeListView.getItems().add(recipe);

    }

    @FXML
    void handleSetCategory(ActionEvent event) {
      

    }

    @FXML
    void randomRecipe(ActionEvent event) {

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
