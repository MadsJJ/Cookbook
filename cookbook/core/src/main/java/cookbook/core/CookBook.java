package cookbook.core;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a cookbook containing a list of recipes.
 */
public class CookBook {
  private List<Recipe> recipes;

  /**
   * Constructs a new cookbook with the given list of recipes.
   *
   * @param recipes the list of recipes to add to the cookbook
   * @throws IllegalArgumentException if the list of recipes is null
   */
  public CookBook(List<Recipe> recipes) {
    if (recipes == null) {
      throw new IllegalArgumentException("recipes cant be null");
    }
    this.recipes = recipes;
  }

  /**
   * Returns a copy of the list of recipes in the cookbook.
   *
   * @return a copy of the list of recipes in the cookbook
   */
  public List<Recipe> getRecipes() {
    return new ArrayList<>(recipes);
  }

  /**
   * Sets the list of recipes in the cookbook to the given list of recipes.
   * only used in tests
   *
   * @param recipes the list of recipes to set in the cookbook
   * @throws IllegalArgumentException if the list of recipes is null or empty
   */
  public void setRecipes(List<Recipe> recipes) {
    if (recipes == null || recipes.size() == 0) {
      throw new IllegalArgumentException("Cookbook must contain recipes");
    }
    this.recipes = recipes;
  }

  /**
   * Adds the given recipe to the cookbook.
   *
   * @param recipe the recipe to add to the cookbook
   * @throws IllegalArgumentException if the recipe is null or already exists in the cookbook
   */
  public void addRecipe(Recipe recipe) {
    if (recipe == null) {
      throw new IllegalArgumentException("Recipe to add can't be null");
    }
    if (recipes.stream().anyMatch(a -> a.getTitle().equals(recipe.getTitle()))) {
      throw new IllegalArgumentException("Recipe already exists in cookbook");
    }
    recipes.add(recipe);
  }

  /**
   * Removes the recipe with the given name from the cookbook.
   *
   * @param recipeName the name of the recipe to remove from the cookbook
   * @throws IllegalArgumentException if the recipe name is null or empty, or if the recipe is not
   *         in the cookbook
   */
  public void removeRecipe(String recipeName) {
    if (recipeName == null || recipeName.equals("")) {
      throw new IllegalArgumentException("Enter recipe name to remove from Cookbook");
    }
    recipes.remove(recipes.stream().filter(a -> a.getTitle().equals(recipeName)).findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Recipe not in cookbook")));
  }

  /**
   * Returns a random recipe from the cookbook with the given category.
   *
   * @param category the category of the recipe to return
   * @return a random recipe from the cookbook with the given category
   * @throws IllegalArgumentException if the category is null or not a valid category
   */
  public Recipe getRandomRecipe(String category) {
    return getRecipesByCategory(category)
        .get((int) ((Math.random() * (getRecipesByCategory(category).size() - 0)) + 0));
  }

  /**
   * Returns a list of all recipes in the cookbook with the given category.
   *
   * @param category the category of the recipes to return
   * @return a list of all recipes in the cookbook with the given category
   * @throws IllegalArgumentException if the category is null or not a valid category, or if there
   *         are no recipes in the category
   */
  public List<Recipe> getRecipesByCategory(String category) {
    if (category == null || !Recipe.validCategories.contains(category)) {
      throw new IllegalArgumentException("Not a valid category");
    }
    List<Recipe> recipeList =
        recipes.stream().filter(a -> a.getCategory().equals(category)).toList();
    if (recipeList.size() == 0) {
      throw new IllegalArgumentException("No recipes in this category");
    }
    return recipeList;
  }

  /**
   * iterates through recipies and checks if they contain the input.
   *
   * @return a list of recipes that contains the input ingredients in the correct amount
   */
  public List<Recipe> getCookBookByIngredientSearch(List<Ingredient> ingredients) {
    List<Recipe> newRecipes = recipes.stream().filter(a -> a.recipeContainsIngredients(ingredients))
        .collect(Collectors.toList());
    if (newRecipes.size() == 0) {
      throw new IllegalArgumentException("No recipe can be made with current ingredients"); 
    }
    return newRecipes;

  }

  /**
   * Returns a string representation of the cookbook.
   *
   * @return a string representation of the cookbook
   */
  @Override
  public String toString() {
    return "CookBook [recipes=" + recipes + "]";
  }

}
