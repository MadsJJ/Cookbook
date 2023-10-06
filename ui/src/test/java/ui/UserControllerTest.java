package ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.io.IOException;

public class UserControllerTest extends ApplicationTest {


  @Start
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Cookbook");
        
            // AnchorPane root = FXMLLoader.load(getClass().getResource("User.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("User.fxml"));
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root));
            UserController controller = loader.getController();

            controller.setStage(primaryStage);
            primaryStage.show();
    }
 
    @Test
    void testLoginButton() throws IOException {
        clickOn("#usernameField").write("mads");
        clickOn("#passwordField").write("borte");
        
        // verifyThat("#usernameField", hasText("")); // Verify that the username field is cleared after login
        // clickOn("#loginButton");
        // verifyThat("#passwordField", hasText("")); // Verify that the password field is cleared after login
        // Start a new stage to verify that the user is redirected to the next screen
        // assertThrows(IllegalArgumentException.class, () -> {
        //   clickOn("#loginButton"););}
        try {
          assertThrows(FileNotFoundException.class, () -> {
            clickOn("#loginButton");});
          
        } catch (Exception e) {
          System.out.println(e.getMessage());
        }
        // Assertions.assertEquals("some message", thrown.getMessage());
    }

    // @Test
    // void testSignupButton() {
    //     clickOn("#usernameField").write("newUser");
    //     clickOn("#passwordField").write("newPassword123");
    //     clickOn("#signupButton");
    //     // Add assertions to verify that the user is signed up and redirected to the next screen
    // }
}
