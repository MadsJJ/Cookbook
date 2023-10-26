package cookbook.json.internal;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import cookbook.core.User;

public class UserSerializer extends JsonSerializer<User>{

  @Override
  public void serialize(User user, JsonGenerator gen, SerializerProvider serializers)
      throws IOException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'serialize'");
  }
  
}
