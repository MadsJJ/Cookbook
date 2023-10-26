package cookbook.json.internal;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import cookbook.core.User;

public class UserSerializer extends JsonSerializer<User>{

  /*
   * format: {
    "username": "...",
    "password": "...",
    "cookBook": {
      "recipes": [
        {
          "title": "...",
          "ingredients": [
            {
              "name": "...",
              "amount": ...,
              "unit": "..."
            }
          ],
          "category": "..."
        }
      ]
    }
  }
   */

  @Override
  public void serialize(User user, JsonGenerator gen, SerializerProvider serializers)
      throws IOException {
    try (FileWriter writer = new FileWriter(userFile, StandardCharsets.UTF_8)) { // Specify UTF-8
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      gson.toJson(users, writer); // Serialize and write the updated user list
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    if (user.getDeadline() != null) {
      gen.writeStringField("deadline", user.getDeadline().toString());
    }
    gen.writeEndObject();
  }
  
}
