package ui;


import core.CookBook;
import core.Ingredient;
import core.Recipe;
import core.User;
import core.UserDataFilehandling;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
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


/**
 * Controller class for the cookbook application's main UI.
 */
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
  private Button cancelRandomRecipeButton;

  @FXML
  private Button randomRecipeButton;

  @FXML
  private Button removeRecipeButton;

  @FXML
  private Button addRecipeButton;

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

  @FXML
  private Pane ingredientPane;

  @FXML
  private Pane searchByIngredientsPane;


  private User user;
  private UserDataFilehandling fileHandler;
  private Recipe tmpRecipe;

  /**
   * Initialize the controller with a user and file handler. also sets the header text to the
   * username and hides the random recipe and add recipe panes.
   *
   * @param user The user object.
   * @param fileHandler The file handler for user data.
   */
  public void initialize(User user, UserDataFilehandling fileHandler) {
    this.user = user;
    randomRecipePane.setVisible(false);
    addNewRecipePane.setVisible(false);
    ingredientPane.setVisible(false);
    searchByIngredientsPane.setVisible(false);
    popupLabel.setVisible(false);
    headerText.setText(user.getUsername() + "'s cookbook.");
    this.fileHandler = fileHandler;
    popupLabel.getScene().getWindow().addEventHandler(MouseEvent.MOUSE_CLICKED,
        new EventHandler<MouseEvent>() {
          @Override
          public void handle(MouseEvent mouseEvent) {
            if (popupLabel.isVisible()) {
              popupLabel.setVisible(false);
            }
          }
        });
    updateRecipeListView();
    tmpRecipe = new Recipe("tmpRecipe", Arrays.asList(new Ingredient("tmpIng", 20, "g")), "Dinner");
    tmpRecipe.removeAllIngredients();
  }

  /**
   * Switches to the random recipe page, hiding the main page.
   */
  @FXML
  void randomRecipePage() {
    randomRecipePane.setVisible(true);
    mainPagePane.setVisible(false);
  }

  /**
   * Initializes the ingredientPane.
   */
  private void initIngredientView() {
    ingredientPane.setVisible(true);
    mainPagePane.setVisible(false);
    ingredientListView.getItems().clear();
    titleTextField.setText("");
    amountTextField.setText("");
    addIngredientNameTextField.setText("");
    deleteIngredientTextField.setText("");
    unitComboBox.setItems(FXCollections.observableArrayList(Ingredient.validUnits));
  }

  /**
   * Switches to the add recipe page, hiding the main page and clearing input fields.
   */
  @FXML
  void addRecipePage() {
    addNewRecipePane.setVisible(true);
    initIngredientView();
    categoryCombobox.setItems(FXCollections.observableArrayList(Recipe.validCategories));
  }

  /**
   * Switches to the searchByIngredients page, hiding the main page.
   */
  @FXML
  void searchByIngredientsPage() {
    searchByIngredientsPane.setVisible(true);
    initIngredientView();
  }

  /**
   * Displays a list of recipes that contains the ingredients we search for.
   */
  @FXML
  void searchByIngredients() {
    try {
      user.setCookBook(new CookBook(fileHandler.getUser(user.getUsername(), user.getPassword())
          .getCookBook().getCookBookByIngredientSearch(tmpRecipe.getIngredients())));
    } catch (Exception e) {
      displayErrorMessage(e);
      user.setCookBook(fileHandler.getUser(user.getUsername(), user.getPassword()).getCookBook());

    }
    updateRecipeListView();

  }

  /**
   * Handles the cancellation of the add recipe or random recipe page and returns to the main page.
   *
   * @param event The ActionEvent that triggered this method.
   */
  @FXML
  void handleCancel(ActionEvent event) {
    addNewRecipePane.setVisible(false);
    ingredientPane.setVisible(false);
    searchByIngredientsPane.setVisible(false);
    randomRecipePane.setVisible(false);
    mainPagePane.setVisible(true);
    user.setCookBook(fileHandler.getUser(user.getUsername(), user.getPassword()).getCookBook());
    updateRecipeListView();
    tmpRecipe.removeAllIngredients();

  }

  /**
   * Updates the recipe list view with the user's recipes.
   */
  @FXML
  void updateRecipeListView() {
    try {
      recipeListView.getItems().setAll(user.getCookBook().getRecipes());

    } catch (Exception e) {
      displayErrorMessage(e);
    }
  }

  /**
   * Removes a recipe from the user's cookbook and updates the recipe list view.
   *
   * @param event The ActionEvent that triggered this method.
   */
  @FXML
  void removeRecipe(ActionEvent event) {
    try {
      user.getCookBook().removeRecipe(deleteRecipeTextfield.getText());
      fileHandler.updateFile(user);
      updateRecipeListView();
    } catch (Exception e) {
      displayErrorMessage(e);
    }
    deleteRecipeTextfield.setText("");
  }

  /**
   * Retrieves and displays recipes from the user's cookbook based on a selected category.
   *
   * @param event The ActionEvent that triggered this method.
   */
  @FXML
  void getRecipesByCategory(ActionEvent event) {
    try {
      recipeListView.getItems()
          .setAll(user.getCookBook().getRecipesByCategory(((Button) event.getSource()).getText()));

    } catch (Exception e) {
      displayErrorMessage(e);
    }
  }

  /**
   * Retrieves and displays a random recipe from the user's cookbook based on a selected category.
   *
   * @param event The ActionEvent that triggered this method.
   */
  @FXML
  void getRandomRecipeByCategory(ActionEvent event) {
    try {
      randomRecipeTextArea.setText(
          user.getCookBook().getRandomRecipe(((Button) event.getSource()).getText()).toString());

    } catch (Exception e) {
      displayErrorMessage(e);
      randomRecipeTextArea.setText("");
    }
  }

  /**
   * Adds an ingredient to the list of ingredients for a new recipe.
   */
  @FXML
  void addIngredient() {

    try {
      tmpRecipe.addIngredient(new Ingredient(addIngredientNameTextField.getText(),
          amountTextField.getText(), unitComboBox.getSelectionModel().getSelectedItem()));
      ingredientListView.getItems().setAll(tmpRecipe.getIngredients());
      addIngredientNameTextField.setText("");
      amountTextField.setText("");
    } catch (Exception e) {
      displayErrorMessage(e);
    }

  }

  /**
   * Removes an ingredient from the list of ingredients for a new recipe.
   */
  @FXML
  void removeIngredient() {
    try {
      tmpRecipe.removeIngredient(deleteIngredientTextField.getText());
      ingredientListView.getItems().setAll(tmpRecipe.getIngredients());
      deleteIngredientTextField.setText("");


    } catch (Exception e) {
      displayErrorMessage(e);
    }
  }

  /**
   * Adds a new recipe to the user's cookbook and updates the recipe list view.
   *
   * @param event The ActionEvent that triggered this method.
   */
  @FXML
  void addRecipe(ActionEvent event) {
    try {

      Recipe recipe = new Recipe(titleTextField.getText(), tmpRecipe.getIngredients(),
          categoryCombobox.getSelectionModel().getSelectedItem());
      user.getCookBook().addRecipe(recipe);
      updateRecipeListView();
      addRecipePage();
      fileHandler.updateFile(user);
      tmpRecipe.removeAllIngredients();
    } catch (Exception e) {
      displayErrorMessage(e);
    }
  }

  /**
   * Displays an error message as a popup label.
   *
   * @param e The exception that contains the error message.
   */
  void displayErrorMessage(Exception e) {
    popupLabel.setText(e.getMessage());
    popupLabel.setVisible(true);
  }

  public List<Recipe> getRecipeListView() {
    return new ArrayList<Recipe>(recipeListView.getItems());
  }

  public List<Ingredient> getIngredientListView() {
    return new ArrayList<Ingredient>(ingredientListView.getItems());
  }

  public String getErrorMessage() {
    return popupLabel.getText();
  }

  public String getRandomRecipeText() {
    return randomRecipeTextArea.getText();
  }

  /**
   * Logs the user out and returns to the user login page.
   */
  @FXML
  void logout() {
    try {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("User.fxml"));
      Parent root = loader.load();
      Scene scene = new Scene(root);
      Stage stage = (Stage) logOutButton.getScene().getWindow();
      UserController controller = loader.getController();
      controller.setFileHandler(fileHandler);
      stage.setScene(scene);
      stage.show();

    } catch (Exception a) {
      a.printStackTrace();

    }
  }

}
