package app;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.testfx.framework.junit5.ApplicationTest;
// import javafx.fxml.FXMLLoader;
// import javafx.scene.Parent;
// import javafx.scene.Scene;
// import javafx.stage.Stage;
// import javafx.scene.control.Button;
// import javafx.scene.control.TextField;
// import javafx.scene.text.Text;

// public class UserControllerTest extends ApplicationTest {

//     private UserController controller;

//     @Override
//     public void start(Stage stage) throws Exception {
//         FXMLLoader loader = new FXMLLoader(getClass().getResource("/app/User.fxml"));
//         Parent root = loader.load();
//         Scene scene = new Scene(root);
//         stage.setScene(scene);
//         stage.show();

//         controller = loader.getController();
//     }

//     @BeforeEach
//     public void setUp() {
//         // Reset text fields and output message before each test
//         controller.username.setText("");
//         controller.password.setText("");
//         controller.outputMessage.setText("");
//     }

//     @Test
//     public void testLoginWithValidCredentials() {
//         // Simulate a login action with valid credentials
//         clickOn(controller.username).write("valid_username");
//         clickOn(controller.password).write("valid_password");
//         clickOn(controller.loginButton);

//         // Assert that the output message is empty (login success)
//         assert(controller.outputMessage.getText().isEmpty());
//     }

//     @Test
//     public void testLoginWithInvalidCredentials() {
//         // Simulate a login action with invalid credentials
//         clickOn(controller.username).write("invalid_username");
//         clickOn(controller.password).write("invalid_password");
//         clickOn(controller.loginButton);

//         // Assert that the output message is set to the expected error message
//         assert(controller.outputMessage.getText().equals(User.outputLogin));
//     }

//     @Test
//     public void testSignupWithValidCredentials() {
//         // Simulate a signup action with valid credentials
//         clickOn(controller.username).write("new_username");
//         clickOn(controller.password).write("new_password");
//         clickOn(controller.signupButton);

//         // Assert that the output message is empty (signup success)
//         assert(controller.outputMessage.getText().isEmpty());
//     }

//     @Test
//     public void testSignupWithExistingUsername() {
//         // Simulate a signup action with an existing username
//         clickOn(controller.username).write("existing_username");
//         clickOn(controller.password).write("new_password");
//         clickOn(controller.signupButton);

//         // Assert that the output message is set to the expected error message
//         assert(controller.outputMessage.getText().equals(User.existingUser));
//     }

//     @Test
//     public void testSignupWithShortCredentials() {
//         // Simulate a signup action with short username and password
//         clickOn(controller.username).write("u");
//         clickOn(controller.password).write("p");
//         clickOn(controller.signupButton);

//         // Assert that the output message is set to the expected error message
//         assert(controller.outputMessage.getText().equals(User.outputSignup));
//     }
// }
