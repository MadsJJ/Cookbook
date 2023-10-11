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

    @Test
    public void testRemoveRecipieNotInCookBook(){
        assertThrows(IllegalArgumentException.class,()->cookBook.removeRecipe("Recipe3"));
    }

     @Test
    public void testRemoveRecipieSizeCheck(){
        cookBook.removeRecipe("Recipe1");
        assertEquals(1,cookBook.getRecipes().size());
    }

    @Test
    public void testGetRandomRecipeInvalidCategory(){
        assertThrows(IllegalArgumentException.class,()->cookBook.getRandomRecipe("Breakfast"));
    }

    @Test
    public void testGetRandomRecipeNull(){
        assertThrows(IllegalArgumentException.class,()->cookBook.getRandomRecipe(null));
    }

    @Test
    public void testGetRandomNoRecipeInCategory(){
        assertThrows(IllegalArgumentException.class,()->cookBook.getRandomRecipe("Dessert"));
    }

    @Test
    public void testGetRandomRecipeSuccess(){
                assertEquals(recipeList.get(0),cookBook.getRandomRecipe("Appetizer"));
    }

    @Test
    public void testGetRandomRecipesInvalidCategory(){
        assertThrows(IllegalArgumentException.class,()->cookBook.getRecipesByCategory("Breakfast"));
    }

    @Test
    public void testGetRandomRecipesNull(){
        assertThrows(IllegalArgumentException.class,()->cookBook.getRecipesByCategory(null));
    }

    @Test
    public void testGetRandomNoRecipesInCategory(){
        assertThrows(IllegalArgumentException.class,()->cookBook.getRecipesByCategory("Dessert"));
    }

    @Test
    public void testGetRandomRecipesSuccess(){
                assertEquals(recipeList.get(0),cookBook.getRecipesByCategory("Appetizer").get(0));
    }


    @Test
    public void testToString(){
        String expected = "CookBook [recipes=[Recipe1, [Ingredient1, 10.0, g], Appetizer, Recipe2, [Ingredient2, 20.0, g], Dinner]]";
        assertEquals(expected,cookBook.toString());
    }
    

}

