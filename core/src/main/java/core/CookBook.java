package core;

import java.util.ArrayList;
import java.util.List;

public class CookBook {

  private List<Recipe> recipes;

  public CookBook(List<Recipe> recipes) {
    this.recipes = recipes;
  }

  public CookBook(){
  }

  public List<Recipe> getRecipes() {
  List<Recipe> copy = new ArrayList<>(recipes);
      return copy;
  }

  public void setRecipes(List<Recipe> recipes) {
    this.recipes = recipes;
  }

  public void addRecipe(Recipe recipe){
    recipes.add(recipe);
  }

  public void removeRecipe(Recipe recipe){
    if(recipes.contains(recipe)) recipes.remove(recipe);
    else{
      throw new IllegalArgumentException("Recipe not in cookbook");
    }
  }

  

  @Override
  public String toString() {
    return "CookBook [recipes=" + recipes + "]";
  }
  
}
