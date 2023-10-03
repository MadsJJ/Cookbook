package core;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Recipe {

  private String title;
  private List<Ingredient> ingredients;
  private String category;
  public static final String APPETIZER = "Appetizer";
  public static final String DINNER = "Dinner";
  public static final String DESSERT = "Dessert";
  
  

  public Recipe(String title, List<Ingredient> ingredients, String category) {
      setTitle(title);
      setIngredients(ingredients);
      this.ingredients=Recipe.sortIngredients(ingredients);
      setCategory(category);
  }



  private static List<Ingredient> sortIngredients(List<Ingredient> ingredients) {
    List<Ingredient> sortedIngredients = ingredients.stream()
        .sorted(Comparator.comparing(Ingredient::getAmount).reversed()
                          .thenComparing(Ingredient::getName))
        .collect(Collectors.toList());
        
    return sortedIngredients;
}

  private boolean isValidCategory(String category) {
    try{
      return category.equals(APPETIZER) || category.equals(DINNER) || category.equals(DESSERT);
    }catch (Exception e){
      throw new IllegalArgumentException("Must fill out a category");
    }
  }

  public String getTitle() {
    return title;
  }


  public void setTitle(String title) {
    if(title==null || title.length()<1) throw new IllegalArgumentException("Not a valid title");
    this.title = title;
  }


  public List<Ingredient> getIngredients() {
      List<Ingredient> copy = new ArrayList<>(ingredients);
      return copy;
  }


  public void setIngredients(List<Ingredient> ingredients) {
    if(validateIngredients(ingredients)){
      this.ingredients = sortIngredients(ingredients);
    }else{
      throw new IllegalArgumentException("Must fill out ingredients");
    }
    
  }

  public boolean validateIngredients(List<Ingredient> ingredients){
    if(ingredients.isEmpty() || ingredients.size() == 0){
      return false; 
    }
      return true;
  }

  public void addIngredient(Ingredient ingredient){
    ingredients.add(ingredient);
    this.ingredients=sortIngredients(ingredients);

  } 

  public void removeIngredient(Ingredient ingredient){
    Ingredient toBeRemoved= ingredients.stream().filter(a->a.getName().equals(ingredient.getName())).findFirst().orElse(null);
    if(toBeRemoved!=null) ingredients.remove(toBeRemoved);
    else{
      throw new IllegalArgumentException("Ingredient not in recipe");
    }
  }


  public String getCategory() {
    return category;
  }


  public void setCategory(String category) {
     if (isValidCategory(category)) {
          this.category = category;
      } else {
          throw new IllegalArgumentException("Invalid category! Legal categories are Appetizer, Dinner and Dessert:");
      }
  }

  public static List<String> getCategories(){
    List<String> categories = new ArrayList<>();
    categories.add(APPETIZER);
    categories.add(DINNER);
    categories.add(DESSERT);
    return categories;
  }

  // public static List<Ingredient> getIngredientsFromString(List<String> ingredients){
  //   List<Ingredient> list = new ArrayList<>();
  //   for (String ingredient : ingredients) {
  //     String[] ingredientDetails =ingredient.split(",");
  //     Ingredient newIngredient= new Ingredient(ingredientDetails[0],Double.parseDouble(ingredientDetails[1]),ingredientDetails[2]);
  //     list.add(newIngredient);
  //   }
  //   return list;
  // }

  

  @Override
  public String toString() {
    return title +","+ ingredients + "," + category;
  }


  public static void main(String[] args) {
    List<Ingredient> ingredients = new ArrayList<>();
    ingredients.add(new Ingredient("Bacon", 100.0, Ingredient.GRAMS));
        ingredients.add(new Ingredient("Pasta", 200.0, Ingredient.GRAMS));

        Recipe recipe = new Recipe("Spaghetti Carbonara", ingredients, Recipe.DINNER);

        System.out.println(recipe);
    
  }
  
  
  

  

  
  
}