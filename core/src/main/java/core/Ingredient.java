package core;

public class Ingredient {

  private String name;
  private double amount;
  private String unit;
  public final static String GRAMS ="g", DL="dl", PIECES="pieces";



public Ingredient(){

}

  public Ingredient(String name, double amount, String unit) {
    setName(name);
    setAmount(amount);
    if(isValidUnit(unit)){
      this.unit=unit;
    }
    else{
      throw new IllegalArgumentException("Not a valid unit of measurement! Legal ones are g, dl and pieces");
    }
    
  }



  public String getName() {
    return name;
  }

  private boolean isValidUnit(String unit) {
    try{
      return unit.equals(GRAMS) || unit.equals(DL) || unit.equals(PIECES);
    } catch(Exception e){
        throw new IllegalArgumentException("Must fill out a unit");
    }
}



  public void setName(String name) {
    if(name==null || name.equals("")){
      throw new IllegalArgumentException("Ingredient needs a name");
    }
    this.name = name;
  }



  public double getAmount() {
    return amount;
  }



  public void setAmount(double amount) {
    if(amount <= 0){
      throw new IllegalArgumentException("Must have more than 0 of the ingredient");
    }
    this.amount = amount;
  }



  public String getUnit() {
    return unit;
  }

  public void setUnit(String unit){
    if(isValidUnit(unit)){
      this.unit=unit;
    }
    else{
      throw new IllegalArgumentException("Not a valid unit of measurement! Legal ones are g, dl and pieces");
    }

  }



  @Override
  public String toString() {
    return name + ","+ amount +","+ unit;
  }
  
  


  
}
