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
    if(!(recipe.getIngredients().size() == 0)  || !recipe.getTitle().equals("") || !recipe.getTitle().equals(null)){
          recipes.add(recipe);
    }else{
      throw new IllegalArgumentException("Must fill out title and ingredients");
    }

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
