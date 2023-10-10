package core;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Recipe {

  private String title;
  private List<Ingredient> ingredients;
  private String category;
  public static final List<String> validCategories = List.of("Appetizer","Dinner","Dessert");
  
  
  

  public Recipe(String title, List<Ingredient> ingredients, String category) {
      setTitle(title);
      setIngredients(ingredients);
      setCategory(category);
  }



  public static List<Ingredient> sortIngredients(List<Ingredient> ingredients) {
    List<Ingredient> sortedIngredients = ingredients.stream()
        .sorted(Comparator.comparing(Ingredient::getAmount).reversed()
                          .thenComparing(Ingredient::getName))
        .collect(Collectors.toList());
        
    return sortedIngredients;
}


  public String getTitle() {
    return title;
  }


  public void setTitle(String title) {
    if(title==null || title.length()<1) throw new IllegalArgumentException("Not a valid title");
    this.title = title;
  }


  public List<Ingredient> getIngredients() {
      return new ArrayList<Ingredient>(ingredients);
  }


  public void setIngredients(List<Ingredient> ingredients) {
        if(ingredients==null || ingredients.size() == 0) throw new IllegalArgumentException("No ingredients");
      this.ingredients = Recipe.sortIngredients(ingredients);
    }
    
  public void addIngredient(Ingredient ingredient){
    if(ingredient==null) throw new IllegalArgumentException("Cant remove null");
    if(ingredients.stream().anyMatch(a->a.getName().equals(ingredient.getName()))) throw new IllegalArgumentException("ingredient already in recipe");
    ingredients.add(ingredient);
    this.ingredients=sortIngredients(ingredients);

  } 

  public void removeIngredient(Ingredient ingredient){
    if(ingredient==null) throw new IllegalArgumentException("Cant remove null");
    if(!ingredients.stream().anyMatch(a->a.getName().equals(ingredient.getName()))) throw new IllegalArgumentException("ingredient not in recipe");
    ingredients.remove(ingredient);
    this.ingredients=sortIngredients(ingredients);    
  }


  public String getCategory() {
    return category;
  }


  public void setCategory(String category) {
     if(category==null||!validCategories.contains(category)) throw new IllegalArgumentException("Invalid category! Legal categories are Appetizer, Dinner and Dessert:");
     this.category=category;
  }

  
  @Override
  public String toString() {
    return title +", "+ ingredients + ", " + category;
  }



  
  
}
