package ui;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The MainApp class starts Cookbook application.
 */
public class MainApp extends Application {

  public static void main(String[] args) {
    Application.launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws IOException {
    primaryStage.setTitle("Cookbook");
    try {
      Parent parent = FXMLLoader.load(getClass().getResource("UserRemoteApp.fxml"));
    primaryStage.setScene(new Scene(parent));
    primaryStage.show();
      // FXMLLoader loader = new FXMLLoader(getClass().getResource("UserRemoteApp.fxml"));
      // Parent root = loader.load();
      // primaryStage.setScene(new Scene(root));
      // UserController controller = loader.getController();
      // controller.setStage(primaryStage);
      // primaryStage.show();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
