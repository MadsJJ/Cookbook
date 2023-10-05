package core;

import java.util.ArrayList;
import java.util.List;

public class CookBook {

  private List<Recipe> recipes;

  public CookBook(List<Recipe> recipes) {
    this.recipes = recipes;
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
    if(recipes.stream().filter(a->a!=null).anyMatch(a->a.getTitle().equals(recipe.getTitle()))) throw new IllegalArgumentException("Recipe already exists in cookbook");
    recipes.add(recipe);

  }

  public void removeRecipe(Recipe recipe){
    if(!recipes.contains(recipe)) throw new IllegalArgumentException("Recipe not in cookbook");
    recipes.remove(recipe);
  }
  
  public void removeRecipe(String recipeName){
    recipes.remove(recipes.stream().filter(a->a!=null).filter(a->a.getTitle().equals(recipeName)).findFirst().orElseThrow(() -> new IllegalArgumentException("Recipe not in cookbook")));
  }

  public Recipe getRandomRecipe(String category){
      return getRecipesByCategory(category).get((int) ((Math.random() * (getRecipesByCategory(category).size() - 0)) + 0));
  }

  public List<Recipe> getRecipesByCategory(String category){
    if(!Recipe.validCategories.contains(category)) throw new IllegalArgumentException("Not a valid category");
    List<Recipe> recipeList =  recipes.stream().filter(a->a.getCategory().equals(category)).toList();
    if(recipeList.size()==0) throw new IllegalArgumentException("No recipes in this category");
    return recipeList;

  }

  @Override
  public String toString() {
    return "CookBook [recipes=" + recipes + "]";
  }
  
}
