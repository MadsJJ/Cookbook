package core;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class IngredientTest {

    private Ingredient ingredient;

    @BeforeEach
    public void setUp() {
        ingredient = new Ingredient("Sugar", 100, Ingredient.GRAMS);
    }

    @Test
    public void testGetName() {
        assertEquals("Sugar", ingredient.getName());
    }

    @Test
    public void testSetName() {
        ingredient.setName("Flour");
        assertEquals("Flour", ingredient.getName());
    }

    @Test
    public void testSetNameWithInvalidName() {
        assertThrows(IllegalArgumentException.class, () -> {
            ingredient.setName("");
        });
    }

    @Test
    public void testGetAmount() {
        assertEquals(100, ingredient.getAmount(), 0.001);
    }

    @Test
    public void testSetAmount() {
        ingredient.setAmount(200);
        assertEquals(200, ingredient.getAmount(), 0.001);
    }

    @Test
    public void testSetAmountWithNegativeValue() {
        assertThrows(IllegalArgumentException.class, () -> {
            ingredient.setAmount(-50);
        });
    }

    @Test
    public void testGetUnit() {
        assertEquals(Ingredient.GRAMS, ingredient.getUnit());
    }

    @Test
    public void testSetUnit() {
        ingredient.setUnit(Ingredient.DL);
        assertEquals(Ingredient.DL, ingredient.getUnit());
    }

    @Test
    public void testSetUnitWithInvalidUnit() {
        assertThrows(IllegalArgumentException.class, () -> {
            ingredient.setUnit("oz");
        });
    }

    @Test
    public void testToString() {
        assertEquals("Sugar,100.0,g", ingredient.toString());
    }
}
