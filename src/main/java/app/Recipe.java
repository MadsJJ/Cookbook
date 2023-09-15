package app;

import java.util.ArrayList;
import java.util.List;

public class Recipe {

  private String title;
  private List<Ingredient> ingredients;
  private String category;


  public Recipe(String title, List<Ingredient> ingredients,String category) {
    this.title = title;
    this.ingredients = ingredients;
    this.category=category;
  }


  public String getTitle() {
    return title;
  }


  public void setTitle(String title) {
    this.title = title;
  }


  public List<Ingredient> getIngredients() {
      List<Ingredient> copy = new ArrayList<>(ingredients);
      return copy;
  }


  public void setIngredients(List<Ingredient> ingredients) {
    this.ingredients = ingredients;
  }

  public void addIngredient(Ingredient Ingredient){

  } 

  public void removeIngredient(Ingredient Ingredient){

  }


  public String getCategory() {
    return category;
  }


  public void setCategory(String category) {
    this.category = category;
  }

  
  

  

  
  
}
