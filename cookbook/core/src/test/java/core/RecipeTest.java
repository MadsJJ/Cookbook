package core;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

public class RecipeTest {

    private Recipe recipe;
    private Ingredient ingredient1;
    private Ingredient ingredient2;
    private Ingredient ingredient3;

    @BeforeEach
    public void setUp() {
        ingredient1 = new Ingredient("Ingredient 1", 100.0, "g");
        ingredient2 = new Ingredient("Ingredient 2", 200.0, "g");
        ingredient3 = new Ingredient("Ingredient 3", 50.0, "dl");
        
        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(ingredient1);
        ingredients.add(ingredient2);
        ingredients.add(ingredient3);

        recipe = new Recipe("Test Recipe", ingredients, "Dinner");
    }

    @Test
    public void testSetTitleSuccess() {
        recipe.setTitle("New Title");
        assertEquals("New Title", recipe.getTitle());
    }

    @Test
    public void testSetTitleLengthEmptyString() {
        {
        assertThrows(IllegalArgumentException.class, () -> {
            recipe.setTitle("");
        });
    }
    }
    @Test
    public void testSetTitleLengthNull() {
        {
        assertThrows(IllegalArgumentException.class, () -> {
            recipe.setTitle(null);
        });
    }
    }

    @Test
    public void testSetValidCategory() {
        recipe.setCategory("Appetizer");
        assertEquals("Appetizer", recipe.getCategory());
    }

    @Test
    public void testInvalidCategoryError() {
        {
        assertThrows(IllegalArgumentException.class, () -> {
            recipe.setCategory("Breakfast");
        });
    }
    }

    @Test
    public void testInvalidCategoryNull() {
        {
        assertThrows(IllegalArgumentException.class, () -> {
            recipe.setCategory(null);
        });
    }
    }



    @Test
    public void testSetIngredients() {
        List<Ingredient> newIngredients = new ArrayList<>();
        newIngredients.add(ingredient2);
        newIngredients.add(ingredient1);

        recipe.setIngredients(newIngredients);
        assertEquals(newIngredients, recipe.getIngredients());
    }

    @Test
    public void testSetIngredientsSorted() {
        List<Ingredient> newIngredients = new ArrayList<>();
        newIngredients.add(ingredient1);
        newIngredients.add(ingredient2);

        recipe.setIngredients(newIngredients);
        assertNotEquals(newIngredients, recipe.getIngredients());
    }

      @Test
    public void testSetEmptyIngredients() {
        assertThrows(IllegalArgumentException.class, () -> {
            recipe.setIngredients(new ArrayList<>());
        });
    }

    @Test
    public void testSetNullIngredients() {
        assertThrows(IllegalArgumentException.class, () -> {
            recipe.setIngredients(null);
        });
    }

    @Test
    public void testAddIngredient() {
        Ingredient newIngredient = new Ingredient("New Ingredient", 75.0, "g");
        recipe.addIngredient(newIngredient);

        assertTrue(recipe.getIngredients().contains(newIngredient));
    }

    @Test
    public void testAddIngredientSorted() {
        Ingredient newIngredient = new Ingredient("New Ingredient", 75.0, "g");
        recipe.addIngredient(newIngredient);

        assertEquals(2, recipe.getIngredients().indexOf(newIngredient));
    }

    @Test
    public void testAddNullIngredient() {
        assertThrows(IllegalArgumentException.class, () -> {
            recipe.addIngredient(null);
        });
    }


    @Test
    public void testAddDuplicateIngredient() {
        assertThrows(IllegalArgumentException.class, () -> {
            recipe.addIngredient(ingredient1);
        });
    }


    @Test
    public void testRemoveExistingIngredient() {
        recipe.removeIngredient(ingredient2);

        assertFalse(recipe.getIngredients().contains(ingredient2));
    }

    @Test
    public void removeNonExistentIngredient() {
      Ingredient removeIngredient = new Ingredient("removeIngredient", 20, "g");
        assertThrows(IllegalArgumentException.class, () -> {
            recipe.removeIngredient(removeIngredient);
        });
    }

    @Test
    public void testRemoveNullIngredient() {
        assertThrows(IllegalArgumentException.class, () -> {
            recipe.removeIngredient(null);
        });
    }

    @Test
    public void testGetIngredientsReturnsCopy() {
        Integer listSize = recipe.getIngredients().size();
        recipe.getIngredients().remove(0);

        assertEquals(listSize, recipe.getIngredients().size());
    }


  

    @Test
    public void testToString() {
        String expected = "Test Recipe, [Ingredient 2, 200.0, g, Ingredient 1, 100.0, g, Ingredient 3, 50.0, dl], Dinner";
        assertEquals(expected, recipe.toString());
    }

}
