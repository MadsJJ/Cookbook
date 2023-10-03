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
    return new ArrayList<>(recipes);
  }

  public void setRecipes(List<Recipe> recipes) {
    if(recipes==null||recipes.size()==0) throw new IllegalArgumentException("Cookbook must contain recipes");
    this.recipes = recipes;
  }

  public void addRecipe(Recipe recipe){
    if(recipe==null) throw new IllegalArgumentException("Recipe to add can't be null");
    recipes.add(recipe);

  }

  public void removeRecipe(Recipe recipe){
    if(!recipes.contains(recipe)) throw new IllegalArgumentException("Recipe not in cookbook");
    recipes.remove(recipe);
  
  }
  

  @Override
  public String toString() {
    return "CookBook [recipes=" + recipes + "]";
  }
  
}
