package core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CookBookTest {

    private CookBook cookBook;
    private List<Recipe> recipeList;
    
    @BeforeEach
    public void setUp() {
        // Create a test cookbook with some initial recipes
        recipeList = new ArrayList<>();

        recipeList.add(new Recipe("Recipe1", Arrays.asList(new Ingredient("Ingredient1", 10, "g")), "Appetizer"));
        recipeList.add(new Recipe("Recipe2", Arrays.asList(new Ingredient("Ingredient2", 20, "g")), "Dinner"));
      
        
        cookBook = new CookBook(recipeList);
    }

    @Test
    public void testContructorRecipeListSize(){
        assertEquals(2,cookBook.getRecipes().size());
    }

    @Test
    public void testContructorNullInput(){
        assertThrows(IllegalArgumentException.class,()->new CookBook(null));
    }

    @Test
    public void testGetRecipesReturnsCopy() {
        Integer listSize = cookBook.getRecipes().size();
        cookBook.getRecipes().remove(0);

        assertEquals(listSize, cookBook.getRecipes().size());
    }

    @Test
    public void testSetRecipiesNull(){
        assertThrows(IllegalArgumentException.class,()->cookBook.setRecipes(null));
    }

    @Test
    public void testSetRecipiesEmptyList(){
        assertThrows(IllegalArgumentException.class,()->cookBook.setRecipes(new ArrayList<Recipe>()));
    }

    @Test
    public void testSetRecipiesSizeCheck(){
        cookBook.setRecipes(Arrays.asList(new Recipe("Recipe2", Arrays.asList(new Ingredient("Ingredient2", 20, "g")), "Dinner")));
        assertEquals(1, cookBook.getRecipes().size());
    }

    @Test
    public void testAddRecipieNull(){
        assertThrows(IllegalArgumentException.class,()->cookBook.addRecipe(null));
    }

    @Test
    public void testAddExistingRecipe(){
        assertThrows(IllegalArgumentException.class,()->cookBook.addRecipe(new Recipe("Recipe1", Arrays.asList(new Ingredient("Ingredient1", 10, "g")), "Appetizer")));
    }

    @Test
    public void testAddRecipieSizeCheck(){

        cookBook.addRecipe(new Recipe("Recipe3", Arrays.asList(new Ingredient("Ingredient2", 20, "g")), "Dinner"));
        assertEquals(3, cookBook.getRecipes().size());
    }

    @Test
    public void testRemoveRecipieNull(){
        assertThrows(IllegalArgumentException.class,()->cookBook.removeRecipe(null));
    }

    @Test
    public void testRemoveRecipieEmptyString(){
        assertThrows(IllegalArgumentException.class,()->cookBook.removeRecipe(""));
    }





//     @Test
//     public void testAddRecipeWithIngredients() {
//         // Create a new recipe with ingredients
//         List<Ingredient> ingredients = new ArrayList<>();
//         ingredients.add(new Ingredient("Ingredient1", 100.0, "g"));
//         Recipe newRecipe = new Recipe("NewRecipe", ingredients, "Appetizer");
        
//         cookBook.addRecipe(newRecipe);
//         List<Recipe> recipes = cookBook.getRecipes();
        
//         assertEquals(4, recipes.size());
//         assertTrue(recipes.contains(newRecipe));
//     }

//     @Test(expected = IllegalArgumentException.class)
//     public void testAddRecipeWithNoIngredients() {
//         // Attempt to add a recipe with no ingredients
//         Recipe newRecipe = new Recipe("NewRecipe", new ArrayList<>(), "Appetizer");
//         cookBook.addRecipe(newRecipe);
//     }

//     @Test
//     public void testSetRecipesWithIngredients() {
//         List<Recipe> newRecipes = new ArrayList<>();
//         // Create new recipes with ingredients
//         List<Ingredient> ingredients1 = new ArrayList<>();
//         ingredients1.add(new Ingredient("Ingredient1", 100.0, "g"));
//         newRecipes.add(new Recipe("NewRecipe1", ingredients1, "Appetizer"));

//         List<Ingredient> ingredients2 = new ArrayList<>();
//         ingredients2.add(new Ingredient("Ingredient2", 200.0, "g"));
//         newRecipes.add(new Recipe("NewRecipe2", ingredients2, "Dinner"));

//         cookBook.setRecipes(newRecipes);
//         List<Recipe> recipes = cookBook.getRecipes();
        
//         assertEquals(2, recipes.size());
//         assertEquals("NewRecipe1", recipes.get(0).getTitle());
//         assertEquals("NewRecipe2", recipes.get(1).getTitle());
//     }

//     @Test(expected = IllegalArgumentException.class)
//     public void testSetRecipesWithNoIngredients() {
//         List<Recipe> newRecipes = new ArrayList<>();
//         // Attempt to set recipes with no ingredients
//         newRecipes.add(new Recipe("NewRecipe1", new ArrayList<>(), "Appetizer"));
//         newRecipes.add(new Recipe("NewRecipe2", new ArrayList<>(), "Dinner"));
        
//         cookBook.setRecipes(newRecipes);
//     }

//     // Other test methods remain the same...
}

