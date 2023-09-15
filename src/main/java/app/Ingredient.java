package app;

public class Ingredient {

  private String name;
  private double amount;
  private Boolean grams;



  public Ingredient(String name, double amount, Boolean grams) {
    this.name = name;
    this.amount = amount;
    this.grams=grams;
  }



  public String getName() {
    return name;
  }



  public void setName(String name) {
    if(name.equals("")){
      throw new IllegalArgumentException("Must fill out an ingredient");
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



  public Boolean getGrams() {
    return grams;
  }



  public void setGrams(Boolean grams) {
    
    this.grams = grams;
  }



  

  
  
  
}
