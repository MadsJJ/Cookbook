package cookbook.json.internal;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import cookbook.core.User;

public class CookbookModule extends SimpleModule{

  private static final String NAME = "CookbookModule";

  /**
   * Initializes this CookbookModule with appropriate serializers and deserializers.
   */
  public CookbookModule() {
    super(NAME, Version.unknownVersion());
    addSerializer(User.class, new UserSerializer());
    addDeserializer(User.class, new UserDeserializer());    
  }  
}
