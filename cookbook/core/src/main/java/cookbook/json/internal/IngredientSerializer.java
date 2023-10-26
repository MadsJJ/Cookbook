package cookbook.json.internal;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import cookbook.core.Ingredient;

public class IngredientSerializer extends JsonSerializer<Ingredient>{

  @Override
  public void serialize(Ingredient ingredient, JsonGenerator gen, SerializerProvider serializers)
      throws IOException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'serialize'");
  }
  
}
