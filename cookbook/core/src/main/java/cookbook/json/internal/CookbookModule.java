package cookbook.json.internal;

import java.util.EnumSet;
import java.util.Set;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import cookbook.core.User;
import cookbook.json.CookbookPersistence.CookbookParts;

public class CookbookModule extends SimpleModule{

  private static final String NAME = "CookbookModule";

  /**
   * Initializes this CookbookModule with appropriate serializers and deserializers.
   */
  public CookbookModule(Set<CookbookParts> parts) {
    super(NAME, Version.unknownVersion());
    //addSerializer(User.class, new UserSerializer());
    
    addDeserializer(User.class, new UserDeserializer());    
  }

  public CookbookModule() {
    this(EnumSet.allOf(CookbookParts.class));
  }

  
}
