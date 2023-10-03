package ui;

import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.TextInputControlMatchers.hasText;

public class UserControllerTest extends ApplicationTest {

    @Override
    public void start(Stage stage) {
        // Initialize your JavaFX application
    }

    @Test
    void testLoginButton() {
        clickOn("#usernameField").write("testUser");
        clickOn("#passwordField").write("password123");
        clickOn("#loginButton");
        verifyThat("#usernameField", hasText("")); // Verify that the username field is cleared after login
        verifyThat("#passwordField", hasText("")); // Verify that the password field is cleared after login
    }

    @Test
    void testSignupButton() {
        clickOn("#usernameField").write("newUser");
        clickOn("#passwordField").write("newPassword123");
        clickOn("#signupButton");
        // Add assertions to verify that the user is signed up and redirected to the next screen
    }
}
