package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;
import core.CookBook;
import core.Ingredient;
import core.Recipe;
import core.User;
import core.UserDataFilehandling;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

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
      this.fileHandler = new UserDataFilehandling("/src/test/java/ui/resources/ui/UserDataTest.json");
      String userDir = System.getProperty("user.dir");
      if (userDir.endsWith("gr2308")) {
        userDir = userDir + "/cookbook/ui";
      }

      try (FileWriter writer = new FileWriter(
          Paths.get(userDir, "/src/test/java/ui/resources/ui/UserDataTest.json").toString(), StandardCharsets.UTF_8)) { // Specify
                                                                                                                        // UTF-8
                                                                                                                        // encoding
        writer.write("");
        writer.close();
        fileHandler.signup("CookBookTest", "password");
        this.user = fileHandler.getUser("CookBookTest", "password");
        Ingredient ingredient1 = new Ingredient("Ingredient 1", 100.0, "g");
        Ingredient ingredient2 = new Ingredient("Ingredient 2", 200.0, "g");
        Ingredient ingredient3 = new Ingredient("Ingredient 3", 50.0, "dl");

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);
        ingredients.add(ingredient3);

        Recipe recipe1 = new Recipe("Test Dinner", ingredients, "Dinner");
        Recipe recipe2 = new Recipe("Test Dinner2", ingredients, "Dinner");
        Recipe recipe3 = new Recipe("Test Appetizer", ingredients, "Appetizer");
        CookBook cookBook = new CookBook(new ArrayList<Recipe>());
        cookBook.addRecipe(recipe1);
        cookBook.addRecipe(recipe2);
        cookBook.addRecipe(recipe3);
        this.user.setCookBook(cookBook);

        controller.initialize(user, fileHandler);
      } catch (Exception a) {
        a.printStackTrace();
      }

      // Serialize and write the updated user list
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // test for login
  // @Test
  // void logOut() throws IOException {
  // clickOn("#logOutButton");
  // }

  @Test
  void TestRecipieListView() {
    assertEquals(user.getCookBook().getRecipes(), controller.getRecipeListView());

  }

}