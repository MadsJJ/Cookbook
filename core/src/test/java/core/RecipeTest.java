// package core;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import static org.junit.jupiter.api.Assertions.*;

// import java.util.ArrayList;
// import java.util.List;

// public class RecipeTest {

//     private Recipe recipe;
//     private List<Ingredient> ingredients;

//     // @BeforeEach
//     public void setUp() {
//         ingredients = new ArrayList<>();
//         ingredients.add(new Ingredient("Sugar", 100, Ingredient.GRAMS));
//         ingredients.add(new Ingredient("Flour", 200, Ingredient.GRAMS));
//         recipe = new Recipe("Chocolate Cake", ingredients, "Dessert");
//     }

//     @Test
//     public void testGetTitle() {
//         assertEquals("Chocolate Cake", recipe.getTitle());
//     }

//     @Test
//     public void testSetTitle() {
//         recipe.setTitle("Brownie");
//         assertEquals("Brownie", recipe.getTitle());
//     }

//     @Test
//     public void testSetTitleWithInvalidTitle() {
//         assertThrows(IllegalArgumentException.class, () -> {
//             recipe.setTitle("");
//         });
//     }

//     @Test
//     public void testGetIngredients() {
//         List<Ingredient> retrievedIngredients = recipe.getIngredients();
//         assertEquals(ingredients, retrievedIngredients);
//         assertNotSame(ingredients, retrievedIngredients); // Ensure a copy is returned
//     }

//     @Test
//     public void testSetIngredients() {
//         List<Ingredient> newIngredients = new ArrayList<>();
//         newIngredients.add(new Ingredient("Cocoa Powder", 50, Ingredient.GRAMS));
//         recipe.setIngredients(newIngredients);
//         assertEquals(newIngredients, recipe.getIngredients());
//     }

//     @Test
//     public void testSetIngredientsWithEmptyList() {
//         assertThrows(IllegalArgumentException.class, () -> {
//             recipe.setIngredients(new ArrayList<>());
//         });
//     }

//     @Test
//     public void testAddIngredient() {
//         Ingredient newIngredient = new Ingredient("Eggs", 2, Ingredient.PIECES);
//         recipe.addIngredient(newIngredient);
//         assertTrue(recipe.getIngredients().contains(newIngredient));
//     }

//     @Test
//     public void testAddDuplicateIngredient() {
//         Ingredient duplicateIngredient = new Ingredient("Sugar", 50, Ingredient.GRAMS);
//         assertThrows(IllegalArgumentException.class, () -> {
//             recipe.addIngredient(duplicateIngredient);
//         });
//     }

//     @Test
//     public void testRemoveIngredient() {
//         Ingredient ingredientToRemove = new Ingredient("Sugar", 100, Ingredient.GRAMS);
//         recipe.removeIngredient(ingredientToRemove);
//         assertFalse(recipe.getIngredients().contains(ingredientToRemove));
//     }

//     @Test
//     public void testRemoveNonexistentIngredient() {
//         Ingredient nonexistentIngredient = new Ingredient("Butter", 50, Ingredient.GRAMS);
//         assertThrows(IllegalArgumentException.class, () -> {
//             recipe.removeIngredient(nonexistentIngredient);
//         });
//     }

//     @Test
//     public void testGetCategory() {
//         assertEquals("Dessert", recipe.getCategory());
//     }

//     @Test
//     public void testSetCategory() {
//         recipe.setCategory("Appetizer");
//         assertEquals("Appetizer", recipe.getCategory());
//     }

//     @Test
//     public void testSetCategoryWithInvalidCategory() {
//         assertThrows(IllegalArgumentException.class, () -> {
//             recipe.setCategory("Main Course");
//         });
//     }

//     @Test
//     public void testToString() {
//         String expectedString = "Chocolate Cake, [Sugar,100.0,g, Flour,200.0,g], Dessert";
//         assertEquals(expectedString, recipe.toString());
//     }
// }
