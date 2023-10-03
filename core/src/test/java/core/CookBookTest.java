package core;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class CookBookTest {

    private CookBook cookBook;

    @BeforeEach
    void setUp() {
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(new Recipe("Recipe 1", new ArrayList<>(), Recipe.APPETIZER));
        recipes.add(new Recipe("Recipe 2", new ArrayList<>(), Recipe.DINNER));

        cookBook = new CookBook(recipes);
    }

    @Test
    void testGetRecipes() {
        List<Recipe> retrievedRecipes = cookBook.getRecipes();
        assertEquals(2, retrievedRecipes.size());
    }

    @Test
    void testSetRecipes() {
        List<Recipe> newRecipes = new ArrayList<>();
        newRecipes.add(new Recipe("New Recipe 1", new ArrayList<>(), Recipe.DESSERT));
        newRecipes.add(new Recipe("New Recipe 2", new ArrayList<>(), Recipe.APPETIZER));

        cookBook.setRecipes(newRecipes);

        List<Recipe> retrievedRecipes = cookBook.getRecipes();
        assertEquals(2, retrievedRecipes.size());
        assertEquals("New Recipe 1", retrievedRecipes.get(0).getTitle());
        assertEquals("New Recipe 2", retrievedRecipes.get(1).getTitle());
    }

    @Test
    void testAddRecipe() {
        Recipe newRecipe = new Recipe("New Recipe", new ArrayList<>(), Recipe.DINNER);

        cookBook.addRecipe(newRecipe);

        List<Recipe> retrievedRecipes = cookBook.getRecipes();
        assertEquals(3, retrievedRecipes.size());
        assertTrue(retrievedRecipes.contains(newRecipe));
    }

    @Test
    void testRemoveRecipe() {
        Recipe recipeToRemove = cookBook.getRecipes().get(0);

        cookBook.removeRecipe(recipeToRemove);

        List<Recipe> retrievedRecipes = cookBook.getRecipes();
        assertEquals(1, retrievedRecipes.size());
        assertFalse(retrievedRecipes.contains(recipeToRemove));
    }

    @Test
    void testRemoveRecipeNotInCookbook() {
        Recipe recipeNotInCookbook = new Recipe("Not in Cookbook", new ArrayList<>(), Recipe.APPETIZER);

        assertThrows(IllegalArgumentException.class, () -> {
            cookBook.removeRecipe(recipeNotInCookbook);
        });
    }
}
