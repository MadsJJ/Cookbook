// package core;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import static org.junit.jupiter.api.Assertions.*;

// import java.util.ArrayList;
// import java.util.List;

// public class CookBookTest {

//     private CookBook cookBook;
//     private Recipe recipe1;
//     private Recipe recipe2;

//     @BeforeEach
//     public void setUp() {
//         List<Recipe> recipes = new ArrayList<>();
//         recipe1 = new Recipe("Spaghetti", new ArrayList<>(), "Dinner");
//         recipe2 = new Recipe("Chocolate Cake", new ArrayList<>(), "Dessert");
//         recipes.add(recipe1);
//         cookBook = new CookBook(recipes);
//     }

//     @Test
//     public void testGetRecipes() {
//         List<Recipe> retrievedRecipes = cookBook.getRecipes();
//         assertEquals(1, retrievedRecipes.size());
//         assertEquals(recipe1, retrievedRecipes.get(0));
//         assertNotSame(recipes, retrievedRecipes); // Ensure a copy is returned
//     }

//     @Test
//     public void testSetRecipes() {
//         List<Recipe> newRecipes = new ArrayList<>();
//         newRecipes.add(recipe2);
//         cookBook.setRecipes(newRecipes);
//         assertEquals(newRecipes, cookBook.getRecipes());
//     }

//     @Test
//     public void testSetRecipesWithEmptyList() {
//         assertThrows(IllegalArgumentException.class, () -> {
//             cookBook.setRecipes(new ArrayList<>());
//         });
//     }

//     @Test
//     public void testAddRecipe() {
//         cookBook.addRecipe(recipe2);
//         assertTrue(cookBook.getRecipes().contains(recipe2));
//     }

//     @Test
//     public void testAddDuplicateRecipe() {
//         assertThrows(IllegalArgumentException.class, () -> {
//             cookBook.addRecipe(recipe1);
//         });
//     }

//     @Test
//     public void testRemoveRecipe() {
//         cookBook.removeRecipe(recipe1);
//         assertFalse(cookBook.getRecipes().contains(recipe1));
//     }

//     @Test
//     public void testRemoveNonexistentRecipe() {
//         Recipe nonexistentRecipe = new Recipe("Pizza", new ArrayList<>(), "Dinner");
//         assertThrows(IllegalArgumentException.class, () -> {
//             cookBook.removeRecipe(nonexistentRecipe);
//         });
//     }

//     @Test
//     public void testRemoveRecipeByTitle() {
//         cookBook.removeRecipe("Spaghetti");
//         assertFalse(cookBook.getRecipes().contains(recipe1));
//     }

//     @Test
//     public void testGetRandomRecipe() {
//         Recipe randomRecipe = cookBook.getRandomRecipe("Dessert");
//         assertNotNull(randomRecipe);
//         assertTrue(randomRecipe.getCategory().equals("Dessert"));
//     }

//     @Test
//     public void testGetRecipesByCategory() {
//         // List<Recipe> dessertRecipes = cookBook.getRecipesByCategory("Dessert");
//         assertEquals(0, dessertRecipes.size());
//     }

//     @Test
//     public void testGetRecipesByInvalidCategory() {
//         assertThrows(IllegalArgumentException.class, () -> {
//             cookBook.getRecipesByCategory("Breakfast");
//         });
//     }

//     @Test
//     public void testToString() {
//         String expectedString = "CookBook [recipes=[Spaghetti, [], Dinner]]";
//         assertEquals(expectedString, cookBook.toString());
//     }
// }
