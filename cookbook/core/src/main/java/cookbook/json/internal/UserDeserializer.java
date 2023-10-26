package cookbook.json.internal;

import java.io.IOException;
import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import cookbook.core.User;

public class UserDeserializer extends JsonDeserializer<User>{

  @Override
  public User deserialize(JsonParser p, DeserializationContext ctxt)
      throws IOException, JacksonException {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'deserialize'");
  }
  
}
