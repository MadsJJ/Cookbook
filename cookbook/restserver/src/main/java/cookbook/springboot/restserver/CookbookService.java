package cookbook.springboot.restserver;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.springframework.stereotype.Service;
import cookbook.core.CookBook;



/**
 * Configures the todo service,
 * including autowired objects.
 */

@Service
public class CookbookService {

  private CookBook cookbook;
  // private CookbookPersistence cookbookPersistence;

  /**
   * Initializes the service with a specific TodoModel.
   *
   * @param todoModel the TodoModel
   */
  //public CookbookService(CookBook cookbook) {
  //  this.cookbook = cookbook;
  //  this.cookbookPersistence = new CookbookPersistence();
  //  cookbookPersistence.setSaveFile("springbootserver-cookbook.json");
  //}

  //public CookbookService() {
  //  this(createDefaultCookbook());
  //}

  public CookBook getCookbook() {
    return cookbook;
  }

  public void setCookbook(CookBook cookbook) {
    this.cookbook = cookbook;
  }

  //private static CookBook createDefaultCookbook() {
  //  CookbookPersistence cookbookPersistence = new CookbookPersistence();
  //  URL url = CookbookService.class.getResource("default-cookbook.json");
  //  if (url != null) {
  //    try (Reader reader = new InputStreamReader(url.openStream(), StandardCharsets.UTF_8)) {
  //      return cookbookPersistence.readCookbook(reader);
  //    } catch (IOException e) {
  //      System.out.println("Couldn't read default-cookbook.json, so rigging cookbook manually ("
  //          + e + ")");
  //    }
  //  }

    //CookBook cookbook = new CookBook(null);
    //return null;
  //}
  
    //Todo todo = new Todo();
    //TodoList todoList1 = new TodoList("todo1");
    //todoList1.addTodoItem(new TodoItem());
    //todo.addTodoList(todoList1);
    //todo.addTodoList(new TodoList("todo2"));
    //return todo;
  //}

  /**
   * Saves the Todo to disk.
   * Should be called after each update.
   */
  //public void autoSaveCookbook() {
  //  if (cookbookPersistence != null) {
  //    try {
  //      cookbookPersistence.saveCookbook(this.cookbook);
  //    } catch (IllegalStateException | IOException e) {
  //      System.err.println("Couldn't auto-save Todo: " + e);
  //    }
  //  }
  //}
}