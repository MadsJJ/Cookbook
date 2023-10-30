package cookbook.springboot.restserver;

import com.fasterxml.jackson.databind.Module;

import cookbook.json.CookbookPersistence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


/**
 * The Spring application.
 */
@SpringBootApplication
public class CookbookApplication {

  

  public static void main(String[] args) {
    SpringApplication.run(CookbookApplication.class, args);
  }
}
