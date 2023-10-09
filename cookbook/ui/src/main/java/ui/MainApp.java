package ui;

import java.io.IOException;
import core.UserDataFilehandling;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Cookbook");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("User.fxml"));
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root));
            UserController controller = loader.getController();
            controller.setFileHandler(new UserDataFilehandling("src/main/resources/ui/UserData.json"));
            controller.setStage(primaryStage);
            System.out.println(controller.getFileHandler().findUsers());
            primaryStage.show();
            
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }

}
