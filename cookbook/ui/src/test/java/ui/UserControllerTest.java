package ui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;

import cookbook.core.UserDataFilehandling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

public class UserControllerTest extends ApplicationTest {
  private UserController controller;
  private UserDataFilehandling fileHandler;


  @Start
  public void start(Stage primaryStage) throws IOException {
    primaryStage.setTitle("Cookbook");

    // AnchorPane root = FXMLLoader.load(getClass().getResource("User.fxml"));
    FXMLLoader loader = new FXMLLoader(getClass().getResource("User.fxml"));
    Parent root = loader.load();
    primaryStage.setScene(new Scene(root));
    UserController controller = loader.getController();
    this.controller = controller;
    UserDataFilehandling filehandler =
        new UserDataFilehandling("/src/test/java/ui/resources/ui/UserDataTest.json");
    this.fileHandler = filehandler;
    this.controller = controller;
    // System.out.println(controller.getFileHandler().findUsers());
    controller.addHideErrorMessageEventHandler(primaryStage);
    controller.setFilePath("/src/test/java/ui/resources/ui/UserDataTest.json");
    primaryStage.show();

    String userDir = System.getProperty("user.dir");
    if (userDir.endsWith("gr2308")) {
      userDir = userDir + "/cookbook/ui";
    }

    try (FileWriter writer = new FileWriter(
        Paths.get(userDir, "/src/test/java/ui/resources/ui/UserDataTest.json").toString(),
        StandardCharsets.UTF_8)) { // Specify UTF-8 encoding
      writer.write("");
      fileHandler.signup("username", "password");
      writer.close();
      // Serialize and write the updated user list
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // test for login
  @Test
  void LoginWithWrongUsernameAndPassword() throws IOException {
    clickOn("#usernameField").write("username1");
    clickOn("#passwordField").write("password1");
    clickOn("#loginButton");
    assertEquals("Incorrect password or username", controller.getErrorMessage());
  }

  @Test
  void LoginWithWrongUsername() throws IOException {
    clickOn("#usernameField").write("username1");
    clickOn("#passwordField").write("password");
    clickOn("#loginButton");
    assertEquals("Incorrect password or username", controller.getErrorMessage());
  }

  @Test
  void LoginWithWrongPassword() throws IOException {
    clickOn("#usernameField").write("username");
    clickOn("#passwordField").write("password1");
    clickOn("#loginButton");
    assertEquals("Incorrect password or username", controller.getErrorMessage());
  }

  @Test
  void LoginSuccess() throws IOException {
    clickOn("#usernameField").write("username");
    clickOn("#passwordField").write("password");
    clickOn("#loginButton");
    assertEquals("", controller.getErrorMessage());
  }
  // tests for signup

  @Test
  void SignupSuccess() throws IOException {
    clickOn("#usernameField").write("username1");
    clickOn("#passwordField").write("password");
    clickOn("#signupButton");
    assertEquals("", controller.getErrorMessage());
  }


  @Test
  void signupWithExistingUsername() {
    clickOn("#usernameField").write("username");
    clickOn("#passwordField").write("password");
    clickOn("#signupButton");
    assertEquals("Username already exists", controller.getErrorMessage());
  }

  @Test
  void testSignupInvalidUsername() {
    clickOn("#usernameField").write("username@");
    clickOn("#passwordField").write("password");
    clickOn("#signupButton");
    assertEquals(
        "Username and password must be: \n - letters and numbers \n - between 3 and 16 characters",
        controller.getErrorMessage());
  }

  @Test
  void testSignupInvalidPassword() {
    clickOn("#usernameField").write("username1");
    clickOn("#passwordField").write("password@");
    clickOn("#signupButton");
    assertEquals(
        "Username and password must be: \n - letters and numbers \n - between 3 and 16 characters",
        controller.getErrorMessage());
  }

  @Test
  void testSignupInvalidLengthShort() {
    clickOn("#usernameField").write("us");
    clickOn("#passwordField").write("password");
    clickOn("#signupButton");
    assertEquals(
        "Username and password must be: \n - letters and numbers \n - between 3 and 16 characters",
        controller.getErrorMessage());
  }

  @Test
  void testSignupInvalidLengthLong() {
    clickOn("#usernameField").write("usernameusernameusernameusername");
    clickOn("#passwordField").write("password");
    clickOn("#signupButton");
    assertEquals(
        "Username and password must be: \n - letters and numbers \n - between 3 and 16 characters",
        controller.getErrorMessage());
  }

}
