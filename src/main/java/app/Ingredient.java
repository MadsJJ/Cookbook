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
    this.name = name;
  }



  public double getAmount() {
    return amount;
  }



  public void setAmount(double amount) {
    this.amount = amount;
  }



  public Boolean getGrams() {
    return grams;
  }



  public void setGrams(Boolean grams) {
    this.grams = grams;
  }



  

  
  
  
}
