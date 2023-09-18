package app;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class RecipeTest {

    private Recipe recipe;

    @BeforeEach
    void setUp() {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("Pasta", 200.0, Ingredient.GRAMS));
        ingredients.add(new Ingredient("Bacon", 100.0, Ingredient.GRAMS));
        ingredients.add(new Ingredient("Eggs", 6.0, Ingredient.PIECES));

        recipe = new Recipe("Spaghetti Carbonara", ingredients, Recipe.DINNER);
    }

    @Test
    void testConstructor() {
        assertEquals("Spaghetti Carbonara", recipe.getTitle());
        assertEquals(Recipe.DINNER, recipe.getCategory());

        List<Ingredient> sortedIngredients = recipe.getIngredients();
        assertEquals(3, sortedIngredients.size());
        assertEquals("Pasta", sortedIngredients.get(0).getName());
        assertEquals("Bacon", sortedIngredients.get(1).getName());
        assertEquals("Eggs", sortedIngredients.get(2).getName());
    }

    @Test
    void testAddIngredient() {
        Ingredient newIngredient = new Ingredient("Parmesan", 50.0, Ingredient.GRAMS);
        recipe.addIngredient(newIngredient);

        List<Ingredient> sortedIngredients = recipe.getIngredients();
        assertEquals(4, sortedIngredients.size());
        assertEquals("Pasta", sortedIngredients.get(0).getName());
        assertEquals("Bacon", sortedIngredients.get(1).getName());
        assertEquals("Parmesan", sortedIngredients.get(2).getName());
        assertEquals("Eggs", sortedIngredients.get(3).getName());
    }

    @Test
    void testRemoveIngredient() {
        Ingredient bacon = new Ingredient("Bacon", 100.0, Ingredient.GRAMS);
        recipe.removeIngredient(bacon);

        List<Ingredient> sortedIngredients = recipe.getIngredients();
        assertEquals(2, sortedIngredients.size());
        assertEquals("Pasta", sortedIngredients.get(0).getName());
        assertEquals("Eggs", sortedIngredients.get(1).getName());
    }
}
