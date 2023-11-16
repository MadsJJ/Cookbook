package cookbook.springboot.restserver;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

/**
 * Configuration class for Gson integration with Spring.
 * Provides beans for Gson and GsonHttpMessageConverter.
 */
@Configuration
public class GsonConfiguration {

  /**
   * Configures and provides a Gson instance as a Spring bean.
   *
   * @return Gson instance
   */
  @Bean
  public Gson gson() {
    return new Gson();
  }

  /**
   * Configures and provides a GsonHttpMessageConverter instance as a Spring bean.
   * Registers the Gson instance created by the {@link #gson()} method.
   *
   * @return GsonHttpMessageConverter instance
   */
  @Bean
  public GsonHttpMessageConverter gsonHttpMessageConverter() {
    GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
    converter.setGson(gson());
    return converter;
  }
  
}  