package cookbook.json;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import cookbook.core.CookBook;
import cookbook.json.internal.CookbookModule;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumSet;
import java.util.Set;

public class CookbookPersistence {

/**
 * Wrapper class for JSON serialization,
 * to avoid direct compile dependencies on Jackson for other modules.
 */
  /**
   * Used to indicate what parts of a TodoModel to serialize.
   */
  public enum CookbookParts {
    INGREDIENT, RECIPE, USER, COOKBOOK
  }

  private ObjectMapper mapper;

  public CookbookPersistence() {
    mapper = createObjectMapper();
  }

  public static SimpleModule createJacksonModule(Set<CookbookParts> parts) {
    return new CookbookModule();
  }

  public static ObjectMapper createObjectMapper(Set<CookbookParts> parts) {
    return new ObjectMapper()
      .registerModule(createJacksonModule(parts));
  }

  public static ObjectMapper createObjectMapper() {
    return createObjectMapper(EnumSet.allOf(CookbookParts.class));
  }

  public CookBook readTodoModel(Reader reader) throws IOException {
    return mapper.readValue(reader, CookBook.class);
  }

  public void writeTodoModel(CookBook cookbook, Writer writer) throws IOException {
    mapper.writerWithDefaultPrettyPrinter().writeValue(writer, cookbook);
  }

  private Path saveFilePath = null;

  public void setSaveFile(String saveFile) {
    this.saveFilePath = Paths.get(System.getProperty("user.home"), saveFile);
  }

  public Path getSaveFilePath() {
    return this.saveFilePath;
  }

  /**
   * Loads a TodoModel from the saved file (saveFilePath) in the user.home folder.
   *
   * @return the loaded TodoModel
   */
  public CookBook loadTodoModel() throws IOException, IllegalStateException {
    if (saveFilePath == null) {
      throw new IllegalStateException("Save file path is not set, yet");
    }
    try (Reader reader = new FileReader(saveFilePath.toFile(), StandardCharsets.UTF_8)) {
      return readTodoModel(reader);
    }
  }

  /**
   * Saves a TodoModel to the saveFilePath in the user.home folder.
   *
   * @param todoModel the TodoModel to save
   */
  public void saveTodoModel(CookBook todoModel) throws IOException, IllegalStateException {
    if (saveFilePath == null) {
      throw new IllegalStateException("Save file path is not set, yet");
    }
    try (Writer writer = new FileWriter(saveFilePath.toFile(), StandardCharsets.UTF_8)) {
      writeTodoModel(todoModel, writer);
    }
  }
}