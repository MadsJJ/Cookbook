package cookbook.springboot.restserver;

import com.fasterxml.jackson.databind.Module;

import cookbook.json.CookbookPersistence;
import cookbook.json.CookbookPersistence.CookbookModelParts;

import java.util.EnumSet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


/**
 * The Spring application.
 */
@SpringBootApplication
public class CookbookApplication {

  @Bean
  public Module objectMapperModule() {
    return CookbookPersistence.createJacksonModule(EnumSet.of(CookbookModelParts.RECIPE));
  }

  public static void main(String[] args) {
    SpringApplication.run(CookbookApplication.class, args);
  }
}
