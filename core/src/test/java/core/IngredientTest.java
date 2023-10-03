package core;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class IngredientTest {

    @Test
    void testConstructor() {
        Ingredient ingredient = new Ingredient("Flour", 250.0, Ingredient.GRAMS);
        assertEquals("Flour", ingredient.getName());
        assertEquals(250.0, ingredient.getAmount());
        assertEquals(Ingredient.GRAMS, ingredient.getUnit());

        assertThrows(IllegalArgumentException.class, () -> new Ingredient("", 100.0, Ingredient.GRAMS));
        assertThrows(IllegalArgumentException.class, () -> new Ingredient("Sugar", -50.0, Ingredient.GRAMS));
        assertThrows(IllegalArgumentException.class, () -> new Ingredient("Salt", 50.0, "InvalidUnit"));
    }

    @Test
    void testSetName() {
        Ingredient ingredient = new Ingredient("Eggs", 6.0, Ingredient.PIECES);

        ingredient.setName("Milk");
        assertEquals("Milk", ingredient.getName());

        assertThrows(IllegalArgumentException.class, () -> ingredient.setName(""));
        assertThrows(IllegalArgumentException.class, () -> ingredient.setName(null));
    }

    @Test
    void testSetAmount() {
        Ingredient ingredient = new Ingredient("Sugar", 250.0, Ingredient.GRAMS);

        ingredient.setAmount(300.0);
        assertEquals(300.0, ingredient.getAmount());

        assertThrows(IllegalArgumentException.class, () -> ingredient.setAmount(-50.0));
    }

    @Test
    void testSetUnit() {
        Ingredient ingredient = new Ingredient("Tomatoes", 3.0, Ingredient.PIECES);

        ingredient.setUnit(Ingredient.GRAMS);
        assertEquals(Ingredient.GRAMS, ingredient.getUnit());

        assertDoesNotThrow(() -> ingredient.setUnit(Ingredient.DL));
        assertThrows(IllegalArgumentException.class, () -> ingredient.setUnit("InvalidUnit"));
    }
}
