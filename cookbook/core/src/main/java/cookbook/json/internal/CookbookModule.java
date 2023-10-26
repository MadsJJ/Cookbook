package cookbook.json.internal;

import java.util.EnumSet;
import java.util.Set;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import cookbook.core.CookBook;
import cookbook.core.Ingredient;
import cookbook.core.Recipe;
import cookbook.core.User;
import cookbook.json.CookbookPersistence.CookbookModelParts;

public class CookbookModule extends SimpleModule{

  private static final String NAME = "UserModule";

  /**
   * Initializes this SalaryCheckerModule with appropriate serializers and deserializers.
   */
  public CookbookModule(Set<CookbookModelParts> parts) {
    super(NAME, Version.unknownVersion());
    addSerializer(User.class, new UserSerializer());
    //Lurer på om vi kanskje ikke trenger disse? Siden alt nås fra user eller cookbook?
    //Og om vi kanskje kan bruke noen av de metodene vi allerede har for å oversette til json i fillagring.

    //addSerializer(Ingredient.class, new IngredientSerializer());
    //addSerializer(Recipe.class, new RecipeSerializer());
    
    addDeserializer(User.class, new UserDeserializer());
    //addDeserializer(Ingredient.class, new IngredientDeserializer());
    //addDeserializer(Recipe.class, new RecipeDeserializer());
    
  }

  public CookbookModule() {
    this(EnumSet.allOf(CookbookModelParts.class));
  }

  
}
