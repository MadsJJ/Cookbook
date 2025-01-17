package cookbook.core;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a recipe with a title, a list of ingredients and a category.
 */
public class Recipe {
  private String title;
  private List<Ingredient> ingredients;
  private String category;
  public static final List<String> validCategories = List.of("Appetizer", "Dinner", "Dessert");

  /**
   * Constructs a recipe with a title, a list of ingredients and a category.
   *
   * @param title the title of the recipe
   * @param ingredients the list of ingredients in the recipe
   * @param category the category of the recipe
   * @throws IllegalArgumentException if the title is null or empty, or if the list 
   *     of ingredients is null or empty, or if the category is null or not a valid category
   */
  public Recipe(String title, List<Ingredient> ingredients, String category) {
    setTitle(title);
    setIngredients(ingredients);
    setCategory(category);
  }

  /**
   * Sorts a list of ingredients by amount and name.
   *
   * @param ingredients the list of ingredients to sort
   * @return the sorted list of ingredients
   */
  public static List<Ingredient> sortIngredients(List<Ingredient> ingredients) {
    List<Ingredient> sortedIngredients = ingredients.stream().sorted(
        Comparator.comparing(Ingredient::getAmount).reversed().thenComparing(Ingredient::getName))
        .collect(Collectors.toList());

    return sortedIngredients;
  }

  /**
   * Gets the title of the recipe.
   *
   * @return the title of the recipe
   */
  public String getTitle() {
    return title;
  }

  /**
   * Sets the title of the recipe.
   *
   * @param title the title of the recipe
   * @throws IllegalArgumentException if the title is null or empty
   */
  public void setTitle(String title) {
    if (title == null || title.length() < 1) {
      throw new IllegalArgumentException("Not a valid title");
    }
    this.title = title;
  }

  /**
   * Gets a copy of the list of ingredients in the recipe.
   *
   * @return a copy of the list of ingredients in the recipe
   */
  public List<Ingredient> getIngredients() {
    return new ArrayList<Ingredient>(ingredients);
  }

  /**
   * Sets the list of ingredients in the recipe.
   *
   * @param ingredients the list of ingredients in the recipe
   * @throws IllegalArgumentException if the list of ingredients is null or empty
   */
  public void setIngredients(List<Ingredient> ingredients) {
    if (ingredients == null || ingredients.size() == 0) {
      throw new IllegalArgumentException("No ingredients");
    }
    this.ingredients = Recipe.sortIngredients(ingredients);
  }

  /**
   * Adds an ingredient to the recipe.
   *
   * @param ingredient the ingredient to add
   * @throws IllegalArgumentException if the ingredient is null or already in the recipe
   */
  public void addIngredient(Ingredient ingredient) {
    if (ingredient == null) {
      throw new IllegalArgumentException("Cant add null");
    }
    if (ingredients.stream().anyMatch(a -> a.getName().equals(ingredient.getName()))) {
      throw new IllegalArgumentException("Ingredient already in recipe");
    }
    ingredients.add(ingredient);
    this.ingredients = sortIngredients(ingredients);
  }

  /**
   * Removes an ingredient from the recipe.
   *
   * @param ingredient the ingredient to remove
   * @throws IllegalArgumentException if the ingredient is null or not in the recipe
   */
  public void removeIngredient(String ingredient) {
    if (ingredient == null || ingredient.equals("")) {
      throw new IllegalArgumentException("Cant remove null");
    }
    if (!ingredients.stream().anyMatch(a -> a.getName().equals(ingredient))) {
      throw new IllegalArgumentException("Ingredient not in recipe");
    }
    Ingredient ingredientToRemove = ingredients.stream()
        .filter(a -> a.getName().equals(ingredient)).findAny().get();
    ingredients.remove(ingredientToRemove);

  }

  public void removeAllIngredients() {
    ingredients = new ArrayList<Ingredient>();
  }

  /**
   * Gets the category of the recipe.
   *
   * @return the category of the recipe
   */
  public String getCategory() {
    return category;
  }

  /**
   * Sets the category of the recipe.
   *
   * @param category the category of the recipe
   * @throws IllegalArgumentException if the category is null or not a valid category
   */
  public void setCategory(String category) {
    if (category == null || !validCategories.contains(category)) {
      throw new IllegalArgumentException(
          "Invalid category! Legal categories are Appetizer, Dinner and Dessert:");
    }
    this.category = category;
  }

  /**
   * Sets the category of the recipe.
   *
   * @param ingredientsToSearch the ingredients from which to filter the search
   * @throws IllegalArgumentException if the category is null or not a valid category
   */
  public boolean recipeContainsIngredients(List<Ingredient> ingredientsToSearch) {
    if (ingredientsToSearch == null || ingredientsToSearch.size() == 0) { 
      return true; 
    }
    if (ingredientsToSearch.stream().anyMatch(a -> !ingredients.stream()
        .map(b -> b.getName().toLowerCase()).collect(Collectors.toList())
        .contains(a.getName().toLowerCase()))) { 
      return false;
    }
    // returns false if this recipe doesn't contain any of the input ingredients
    List<Ingredient> filteredIngredients = ingredients.stream()
        .filter(a -> ingredientsToSearch.stream().map(b -> b.getName().toLowerCase())
        .collect(Collectors.toList()).contains(a.getName().toLowerCase()))
        .collect(Collectors.toList());
    // return a new list of ingredients from this recipe that are also in the input ingredient list

    return ingredientsToSearch.stream().allMatch(a -> filteredIngredients.stream()
    .filter(b -> b.getName().toLowerCase().equals(a.getName().toLowerCase()))
    .findAny().get().getAmount() <= a.getAmount());
    //  returns true if for every ingredient in filteredRecipes 
    //  the amount is greater than the corresponding amount of the input ingredient list
  }


  /**
   * Returns a string representation of the recipe.
   *
   * @return a string representation of the recipe
   */
  @Override
  public String toString() {
    return title + ", " + ingredients + ", " + category;
  }
  
 

  
}