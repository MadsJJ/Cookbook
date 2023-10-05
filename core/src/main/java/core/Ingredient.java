package core;

import java.util.List;

public class Ingredient {

  private String name;
  private double amount;
  private String unit;
  public final static String GRAMS = "g", DL = "dl", PIECES = "pieces";
  public final static List<String> validUnits = List.of("g", "dl", "pieces");
  
  public Ingredient(String name, double amount, String unit) {
    setName(name);
    setAmount(amount);
    setUnit(unit);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    if (name == null || name.equals("")) {
      throw new IllegalArgumentException("Ingredient needs a name");
    }
    this.name = name;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException("Value must be larger than 0");
    }
    this.amount = amount;
  }

  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit) {
    if(!validUnits.contains(unit)) 
    throw new IllegalArgumentException("Not a valid unit of measurement! Legal ones are g, dl and pieces");
    this.unit=unit;
    

  }

  @Override
  public String toString() {
    return name + "," + amount + "," + unit;
  }

}
