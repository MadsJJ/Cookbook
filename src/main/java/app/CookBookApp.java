package app;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class CookBookApp extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Cookbook");
        try {
            AnchorPane root = FXMLLoader.load(getClass().getResource("/app/Login.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace(); 
        }
    }

}
