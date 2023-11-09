package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;

import cookbook.core.CookBook;
import cookbook.core.Ingredient;
import cookbook.core.Recipe;
import cookbook.core.User;
import cookbook.core.UserDataFilehandling;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import ui.access.LocalCookbookAccess;

public class CookBookControllerTest extends ApplicationTest {

  private CookBookController controller;
  private UserDataFilehandling fileHandler;
  private User user;

  @Start
  public void start(Stage primaryStage) throws IOException {
    try {

      FXMLLoader loader = new FXMLLoader(getClass().getResource("CookBook.fxml"));
      Parent root = loader.load();
      primaryStage.setScene(new Scene(root));
      this.controller = loader.getController();
      primaryStage.show();
      this.fileHandler =
          new UserDataFilehandling("/src/test/java/ui/resources/ui/UserDataTest.json");
      String userDir = System.getProperty("user.dir");
      if (userDir.endsWith("gr2308")) {
        userDir = userDir + "/cookbook/ui";
      }

      try (FileWriter writer = new FileWriter(
          Paths.get(userDir, "/src/test/java/ui/resources/ui/UserDataTest.json").toString(),
          StandardCharsets.UTF_8)) { 
        writer.write("");
        writer.close();
        fileHandler.signup("username", "password");
        this.user = fileHandler.getUser("username", "password");
        Ingredient ingredient1 = new Ingredient("Ingredient 1", 100.0, "g");
        Ingredient ingredient2 = new Ingredient("Ingredient 2", 200.0, "g");
        Ingredient ingredient3 = new Ingredient("Ingredient 3", 50.0, "dl");
        Ingredient ingredient4 = new Ingredient("Ingredient 4", 50.0, "dl");

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);
        ingredients.add(ingredient3);

        Recipe recipe1 = new Recipe("Test Dinner", ingredients, "Dinner");
        Recipe recipe2 = new Recipe("Test Dinner2", ingredients, "Dinner");
        Recipe recipe3 = new Recipe("Test Appetizer", ingredients, "Appetizer");

        ingredients.add(ingredient4);
        Recipe recipe4 = new Recipe("Test Dinner3", ingredients, "Dinner");
        CookBook cookBook = new CookBook(new ArrayList<Recipe>());
        cookBook.addRecipe(recipe1);
        cookBook.addRecipe(recipe2);
        cookBook.addRecipe(recipe3);
        cookBook.addRecipe(recipe4);
        this.user.setCookBook(cookBook);
        fileHandler.updateFile(user);
        controller.initialize(user, new LocalCookbookAccess(fileHandler));
      } catch (Exception a) {
        a.printStackTrace();
      }

      // Serialize and write the updated user list
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Test
  void logOut() throws IOException {
    clickOn("#logOutButton");
  }

  @Test
  void TestRecipieListView() {
    assertEquals(user.getCookBook().getRecipes(), controller.getRecipeListView());
    clickOn("#dinnerButton");
    assertEquals(user.getCookBook().getRecipesByCategory("Dinner"), controller.getRecipeListView());
    clickOn("#appetizerButton");
    assertEquals(user.getCookBook().getRecipesByCategory("Appetizer"),
        controller.getRecipeListView());
    clickOn("#dessertButton");
    assertEquals("No recipes in this category", controller.getErrorMessage());
    clickOn("#allRecipesButton");
    assertEquals(user.getCookBook().getRecipes(), controller.getRecipeListView());
  }

  @Test
  void TestGetRandomRecipe() {
    clickOn("#randomRecipeButton");
    assertEquals("", controller.getRandomRecipeText());
    clickOn("#randomAppetizerButton");
    assertEquals(user.getCookBook().getRandomRecipe("Appetizer").toString(),
        controller.getRandomRecipeText());
    clickOn("#randomDessertButton");
    assertEquals("", controller.getRandomRecipeText());
    assertEquals("No recipes in this category", controller.getErrorMessage());
    clickOn("#randomDinnerButton");
    assertTrue(user.getCookBook().getRecipesByCategory("Dinner").toString()
        .contains(controller.getRandomRecipeText()));
  }

  @Test
  void TestRemoveRecipe() {
    clickOn("#removeRecipeButton");
    assertEquals("Enter recipe name to remove from Cookbook", controller.getErrorMessage());
    clickOn("#deleteRecipeTextfield").write("NotInCookBook");
    clickOn("#removeRecipeButton");
    assertEquals("Recipe not in cookbook", controller.getErrorMessage());
    clickOn("#deleteRecipeTextfield").write("");
    clickOn("#removeRecipeButton");
    assertEquals("Enter recipe name to remove from Cookbook", controller.getErrorMessage());
    clickOn("#deleteRecipeTextfield").write("Test Appetizer");
    clickOn("#removeRecipeButton");
    assertEquals(3, user.getCookBook().getRecipes().size());
    assertEquals(3, controller.getRecipeListView().size());
  }

  @Test
  void TestAddRecipeInitCheck() {
    clickOn("#addRecipeButton");
    clickOn("#addIngredientButton");
    assertEquals("Ingredient needs a name", controller.getErrorMessage());
    clickOn("#removeIngredientButton");
    assertEquals("Cant remove null", controller.getErrorMessage());
    clickOn("#addNewRecipeButton");
    assertEquals("Not a valid title", controller.getErrorMessage());
  }

  @Test
  void TestAddIngredient() {
    clickOn("#addRecipeButton");
    clickOn("#addIngredientNameTextField").write("NewIngredient");
    clickOn("#addIngredientButton");
    assertEquals("Amount for ingredient has to be in double format  i.e. 0.0",
        controller.getErrorMessage());
    clickOn("#amountTextField").write("abc");
    clickOn("#amountTextField").

        clickOn("#addIngredientButton");
    assertEquals("Amount for ingredient has to be in double format  i.e. 0.0",
        controller.getErrorMessage());

    clickOn("#amountTextField").write('\b');
    clickOn("#amountTextField").write('\b');
    clickOn("#amountTextField").write('\b');
    clickOn("#amountTextField").write("-10");
    clickOn("#addIngredientButton");
    assertEquals("Value must be larger than 0", controller.getErrorMessage());
    clickOn("#amountTextField").write('\b');
    clickOn("#amountTextField").write('\b');
    clickOn("#amountTextField").write('\b');
    clickOn("#amountTextField").write("10");
    clickOn("#addIngredientButton");
    assertEquals("Not a valid unit of measurement! Legal ones are g, dl and pieces",
        controller.getErrorMessage());
    clickOn("#unitComboBox").write('\u2193').write('\n');
    clickOn("#addIngredientButton");
    assertEquals("[NewIngredient, 10.0, g]", controller.getIngredientListView().toString());
    clickOn("#deleteIngredientTextField").write("NewIngredient");
    clickOn("#removeIngredientButton");
    assertEquals(0, controller.getIngredientListView().size());

  }

  @Test
  void TestAddRecipe() {
    clickOn("#addRecipeButton");
    clickOn("#addIngredientNameTextField").write("NewIngredient");
    clickOn("#amountTextField").write("10");
    clickOn("#unitComboBox").write('\u2193').write('\n');
    clickOn("#addIngredientButton");
    assertEquals("[NewIngredient, 10.0, g]", controller.getIngredientListView().toString());
    clickOn("#addIngredientNameTextField").write("NewIngredient");
    clickOn("#amountTextField").write("20");
    clickOn("#unitComboBox").write('\u2193').write('\n');
    clickOn("#addIngredientButton");
    assertEquals("Ingredient already in recipe", controller.getErrorMessage());
    clickOn("#addIngredientNameTextField").write("2");
    clickOn("#addIngredientButton");
    assertEquals(2, controller.getIngredientListView().size());
    clickOn("#addNewRecipeButton");
    assertEquals("Not a valid title", controller.getErrorMessage());
    clickOn("#titleTextField").write("Test Dinner");
    clickOn("#addNewRecipeButton");
    assertEquals("Invalid category! Legal categories are Appetizer, Dinner and Dessert:",
        controller.getErrorMessage());
    clickOn("#categoryCombobox").write('\u2193').write('\u2193').write('\n');
    clickOn("#addNewRecipeButton");
    assertEquals("Recipe already exists in cookbook", controller.getErrorMessage());
    clickOn("#titleTextField").write("4");
    clickOn("#addNewRecipeButton");
    assertEquals(user.getCookBook().getRecipes().size(),controller.getRecipeListView().size()); 
  

  }

  @Test
  void TestSearchByIngredients(){
    List<Recipe> initialRecipes = controller.getRecipeListView();
    clickOn("#SearchByIngredientsButton");
    clickOn("#SearchButton");
    List<Recipe> filteredRecipes = controller.getRecipeListView();
    assertEquals(initialRecipes.size(),filteredRecipes.size());
    clickOn("#addIngredientNameTextField").write("Ingredient 4");
    clickOn("#amountTextField").write("100");
    clickOn("#unitComboBox").write('\u2193').write('\n');
    clickOn("#addIngredientButton");
    clickOn("#SearchButton");
    filteredRecipes = controller.getRecipeListView();
    assertEquals(1,filteredRecipes.size());
    clickOn("#addIngredientNameTextField").write("Ingredient 5");
    clickOn("#amountTextField").write("100");
    clickOn("#addIngredientButton");
    clickOn("#SearchButton");
    filteredRecipes = controller.getRecipeListView();
    assertEquals(initialRecipes.size(),filteredRecipes.size());
  }

  @Test
  void TestCancelButton(){
    List<Recipe> initialRecipes = controller.getRecipeListView();
    clickOn("#SearchByIngredientsButton");
    clickOn("#addIngredientNameTextField").write("Ingredient 4");
    clickOn("#amountTextField").write("100");
    clickOn("#unitComboBox").write('\u2193').write('\n');
    clickOn("#addIngredientButton");
    clickOn("#SearchButton");
    List<Recipe> filteredRecipes = controller.getRecipeListView();
    assertEquals(1,filteredRecipes.size());
    clickOn("#cancelSearchByIngredients");
    assertEquals(initialRecipes.size(),controller.getRecipeListView().size());


  }



}

