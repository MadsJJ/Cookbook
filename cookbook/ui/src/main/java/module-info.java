module user.ui {
    requires user.core;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.net.http;
    requires com.google.gson;

    opens ui to javafx.graphics, javafx.fxml;
}
