package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;


public class MainAppTest extends ApplicationTest {
  private Stage stage;

  private MainApp mainApp;

  @Override
  public void start(Stage stage) {
    mainApp = new MainApp();
    try {
      mainApp.start(stage);
      this.stage = stage;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  @Test
  public void testTitle() {
    assertEquals("Cookbook", stage.getTitle());
  }


}
