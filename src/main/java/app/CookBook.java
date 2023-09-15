package app;

import java.util.ArrayList;
import java.util.List;

public class CookBook {

  private List<Recipe> recipes;

  public CookBook(List<Recipe> recipes) {
    this.recipes = recipes;
  }

  public List<Recipe> getRecipes() {
  List<Recipe> copy = new ArrayList<>(recipes);
      return copy;
  }

  public void setRecipes(List<Recipe> recipes) {
    this.recipes = recipes;
  }

  
  
  
}
