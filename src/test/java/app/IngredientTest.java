package app;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class IngredientTest {
  
    private Ingredient test;

    
    @BeforeEach
	  public void setup() {
      test = new Ingredient("sukker", 0.5, true);
	}

    @Test
    @DisplayName("Tester konstruktør")
    public void testConstructor() {
      Assertions.assertEquals("sukker", test.getName());
      Assertions.assertEquals(0.5, test.getAmount());
      Assertions.assertEquals(true, test.getGrams());
  
    }

    @Test
    @DisplayName("Tester mengde")
    public void testSetAmount(){
      Assertions.assertThrows(IllegalArgumentException.class ,() -> {test.setAmount(0.0);});
    }

     @Test
     @DisplayName("Tester navn")
    public void testSetName(){
            Assertions.assertThrows(IllegalArgumentException.class ,() -> {test.setName("");});
    }

     @Test
     @DisplayName("Tester grams")
    public void testSetGrams(){
      test.setGrams(false);
      Assertions.assertEquals(false, test.getGrams());

    }
  }
    