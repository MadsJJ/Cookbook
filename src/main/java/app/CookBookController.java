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
    private TextField unitTextField;

    private User user;

    @FXML
    void addRecipe() {
        addNewRecipePane.setVisible(true);
        addRecepieButton.setVisible(false);
        randomRecipeButton.setVisible(false);
        removeRecipeButton.setVisible(false);
        ObservableList<String> categories = FXCollections.observableList(Recipe.getCategories());
        categoryCombobox.setItems(categories); 
        deleteRecipeText.setVisible(false);
        deleteRecipeTextfield.setVisible(false);
          
        };
      
      
    


    @FXML
    void handleCancel(ActionEvent event) {
      ingredientListView.getItems().removeAll(ingredientListView.getItems());
      titleTextField.setText("");
      amountTextField.setText("");
      unitTextField.setText("");
      addIngredientTextField.setText("");
      deleteIngredientTextField.setText("");

      if(event.getSource() == cancelNewRecipeButton){
        addNewRecipePane.setVisible(false);
        addRecepieButton.setVisible(true);
        randomRecipeButton.setVisible(true);
        removeRecipeButton.setVisible(true);
        deleteRecipeText.setVisible(true);
        deleteRecipeTextfield.setVisible(true);
      }
      else if (event.getSource() == cancelRandomRecepieButton){
        randomRecipePane.setVisible(false); 
        addRecepieButton.setVisible(true);
        randomRecipeButton.setVisible(true); 
        removeRecipeButton.setVisible(true);
        deleteRecipeText.setVisible(true);
        deleteRecipeTextfield.setVisible(true);

      }
    }

        @FXML
    void removeRecipe(ActionEvent event) {
      Recipe recipeToRemove = recipeListView.getItems().stream().filter(a->a.getTitle().equals(deleteRecipeTextfield.getText())).findFirst().orElseThrow();
      user.getCookBook().removeRecipe(recipeToRemove); 
      recipeListView.getItems().remove(recipeToRemove);
    }

    @FXML
    void handleCategory(ActionEvent event) {
      String category;
      if(event.getSource()==randomAppetizerButton) category=Recipe.APPETIZER;
      else if(event.getSource()==randomDinnerButton) category=Recipe.DINNER;
      else{ category=Recipe.DESSERT;
      }
      List<Recipe> recipes=user.getCookBook().getRecipes().stream().filter(a->a.getCategory().equals(category)).toList();
      int random = (int) ((Math.random() * (recipes.size() - 0)) + 0);
      randomRecipeTextArea.setText(recipes.get(random).toString());


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
      user.getCookBook().addRecipe(recipe);
      recipeListView.getItems().add(recipe);
      ingredientListView.getItems().removeAll(ingredientListView.getItems());
      titleTextField.setText("");
      amountTextField.setText("");
      unitTextField.setText("");
      addIngredientTextField.setText("");
      deleteIngredientTextField.setText("");
    }

    @FXML
    void handleSetCategory(ActionEvent event) {
      

    }

    @FXML
    void randomRecipe() {
        randomRecipePane.setVisible(true);
        addRecepieButton.setVisible(false);
        randomRecipeButton.setVisible(false);
        removeRecipeButton.setVisible(false);
        deleteRecipeText.setVisible(false);
        deleteIngredientTextField.setVisible(false);
        
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
