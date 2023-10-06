package ui;

import java.net.URL;
import java.util.ResourceBundle;
import core.Ingredient;
import core.Recipe;
import core.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import core.UserDataFilehandling;



public class CookBookController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addIngredientButton;

    @FXML
    private TextField addIngredientNameTextField;

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
    private Button removeRecipeButton;

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
    private Text deleteRecipeText;

    @FXML
    private TextField deleteRecipeTextfield;

    @FXML
    private ComboBox<String> unitComboBox;

    @FXML
    private Label popupLabel;

    @FXML 
    private Button appetizerButton;

    @FXML
    private Button dinnerButton;

    @FXML
    private Button dessertButton;

    @FXML
    private Pane mainPagePane;

  
    private User user;

    
    
    public void initialize(User user){
      this.user=user;
      randomRecipePane.setVisible(false);
      addNewRecipePane.setVisible(false);
      popupLabel.setVisible(false);
      headerText.setText(user.getUsername() + "´s cookbook.");
      updateRecipeListView();
      // popupLabel.getScene().getWindow().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
      //   @Override
      //   public void handle(MouseEvent mouseEvent){
      //     if (popupLabel.isVisible()) popupLabel.setVisible(false);
      //   }
        
      // });
    }
    @FXML
    void randomRecipePage() {
        randomRecipePane.setVisible(true);
        mainPagePane.setVisible(false);
        
    }

    @FXML
    void addRecipePage() {
        addNewRecipePane.setVisible(true);
        mainPagePane.setVisible(false);
        ingredientListView.getItems().clear();
        titleTextField.setText("");
        amountTextField.setText("");
        addIngredientNameTextField.setText("");
        deleteIngredientTextField.setText("");
        unitComboBox.setItems(FXCollections.observableArrayList(Ingredient.validUnits));
        categoryCombobox.setItems(FXCollections.observableArrayList(Recipe.validCategories)); 
        
        };
      
      
    
    @FXML
    void handleCancel(ActionEvent event) {
      addNewRecipePane.setVisible(false);
      randomRecipePane.setVisible(false); 
      mainPagePane.setVisible(true);
    }

      @FXML
      void updateRecipeListView(){
        try {
          recipeListView.getItems().clear();
          recipeListView.getItems().addAll(user.getCookBook().getRecipes());
          
        } catch (Exception e) {
          displayErrorMessage(e);
        }
      }

        @FXML
    void removeRecipe(ActionEvent event) {
    try {
      user.getCookBook().removeRecipe(deleteRecipeTextfield.getText());
      UserDataFilehandling.updateFile(user);
      updateRecipeListView();
      
    } catch (Exception e) {
      displayErrorMessage(e);
    }
    }

    
    @FXML
    void getRecipesByCategory(ActionEvent event){
      try {
        recipeListView.getItems().clear();
        recipeListView.getItems().addAll(user.getCookBook().getRecipesByCategory(((Button) event.getSource()).getText()));
        
      } catch (Exception e) {
        displayErrorMessage(e);
      }
    }

    @FXML
    void getRandomRecipeByCategory(ActionEvent event) {
      try {
        randomRecipeTextArea.setText(user.getCookBook().getRandomRecipe(((Button) event.getSource()).getText()).toString());
        
      } catch (Exception e) {
        displayErrorMessage(e);
        randomRecipeTextArea.setText("");
      }
      
    

    }

  
    @FXML
    void addIngredient() {
    
      try{
      Ingredient ing = new Ingredient(addIngredientNameTextField.getText(), Double.parseDouble(amountTextField.getText()), unitComboBox.getSelectionModel().getSelectedItem());
      if (ingredientListView.getItems().stream().filter(a->a!=null).anyMatch(a->a.getName().equals(ing.getName()))) throw new IllegalArgumentException("Ingredient with name already exists");
      ingredientListView.getItems().add(ing);
      addIngredientNameTextField.setText("");
      amountTextField.setText("");
      }

      catch (Exception e){
        displayErrorMessage(e);
      }
  
      }

    @FXML
    void removeIngredient(){
      try {
      
          Ingredient ingredientToRemove = ingredientListView.getItems().stream().filter(a->a.getName().equals(deleteIngredientTextField.getText())).findFirst().orElseThrow(() -> new IllegalArgumentException("Ingredient not in list"));
          ingredientListView.getItems().remove(ingredientToRemove);
          deleteIngredientTextField.setText("");
      
        
      } catch (Exception e) {
        displayErrorMessage(e);
      }
    }




    @FXML
    void addRecipe(ActionEvent event) {
      
      try{

      Recipe recipe = new Recipe(titleTextField.getText(), ingredientListView.getItems(), categoryCombobox.getSelectionModel().getSelectedItem());
      user.getCookBook().addRecipe(recipe);
      updateRecipeListView();
      addRecipePage();
      UserDataFilehandling.updateFile(user); 
    }
    catch (Exception e){
      displayErrorMessage(e);
    }
  }



    @FXML
    void displayErrorMessage(Exception e){
      // if(popupLabel.getScene().getWindow().getEventHandler()==null)    "implement later"
      popupLabel.getScene().getWindow().addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent mouseEvent){
          if (popupLabel.isVisible()) popupLabel.setVisible(false);
        }
        
      });
      popupLabel.setText(e.getMessage());
      popupLabel.setVisible(true);
    }

    @FXML
    void logout(){
       try {
              FXMLLoader loader = new FXMLLoader(getClass().getResource("User.fxml"));
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
