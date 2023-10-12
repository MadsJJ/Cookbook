package core;

import java.util.List;

/**
 * Represents an ingredient with a name, amount, and unit of measurement.
 */
public class Ingredient {
  private String name;
  private double amount;
  private String unit;
  public static final List<String> validUnits = List.of("g", "dl", "pieces");

  /**
   * Creates a new Ingredient object with the given name, amount, and unit of measurement.
   *
   * @param name the name of the ingredient
   * @param amount the amount of the ingredient
   * @param unit the unit of measurement for the ingredient
   */
  public Ingredient(String name, double amount, String unit) {
    setName(name);
    setAmount(amount);
    setUnit(unit);
  }

  /**
   * Creates a new Ingredient object with the given name, amount, and unit of measurement.
   *
   * @param name the name of the ingredient
   * @param amount the amount of the ingredient as a string
   * @param unit the unit of measurement for the ingredient
   * @throws IllegalArgumentException if the amount cannot be parsed as a double
   */
  public Ingredient(String name, String amount, String unit) {
    setName(name);
    try {
      setAmount(Double.parseDouble(amount));
    } catch (Exception e) {
      if(e.getMessage().equals("Value must be larger than 0")) throw new IllegalArgumentException("Value must be larger than 0"); 
      else{throw new IllegalArgumentException("Amount for ingredient has to be in double format  i.e. 0.0");}
    }
    setUnit(unit);
  }

  /**
   * Returns the name of the ingredient.
   *
   * @return the name of the ingredient
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the ingredient.
   *
   * @param name the name of the ingredient
   * @throws IllegalArgumentException if the name is null or empty
   */
  public void setName(String name) {
    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("Ingredient needs a name");
    }
    this.name = name;
  }

  /**
   * Returns the amount of the ingredient.
   *
   * @return the amount of the ingredient
   */
  public double getAmount() {
    return amount;
  }

  /**
   * Sets the amount of the ingredient.
   *
   * @param amount the amount of the ingredient
   * @throws IllegalArgumentException if the amount is less than or equal to 0
   */
  public void setAmount(double amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException("Value must be larger than 0");
    }
    this.amount = amount;
  }

  /**
   * Returns the unit of measurement for the ingredient.
   *
   * @return the unit of measurement for the ingredient
   */
  public String getUnit() {
    return unit;
  }

  /**
   * Sets the unit of measurement for the ingredient.
   *
   * @param unit the unit of measurement for the ingredient
   * @throws IllegalArgumentException if the unit is null or not a valid unit of measurement
   */
  public void setUnit(String unit) {
    if (unit == null || !validUnits.contains(unit)) {
      throw new IllegalArgumentException(
          "Not a valid unit of measurement! Legal ones are g, dl and pieces");
    }
    this.unit = unit;
  }

  /**
   * Returns a string representation of the ingredient.
   *
   * @return a string representation of the ingredient
   */
  @Override
  public String toString() {
    return name + ", " + amount + ", " + unit;
  }
}
