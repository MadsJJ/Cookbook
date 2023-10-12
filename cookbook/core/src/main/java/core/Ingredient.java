package core;

import java.util.List;

public class Ingredient {

  private String name;
  private double amount;
  private String unit;
  public final static List<String> validUnits = List.of("g", "dl", "pieces");
  
  public Ingredient(String name, double amount, String unit) {
    setName(name);
    setAmount(amount);
    setUnit(unit);
  }

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
    if(unit==null||!validUnits.contains(unit)){
    throw new IllegalArgumentException("Not a valid unit of measurement! Legal ones are g, dl and pieces");
  }
    this.unit=unit;
    

  }

  @Override
  public String toString() {
    return name + ", " + amount + ", " + unit;
  }


}
