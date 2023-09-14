package app;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class mainPageController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Text headerText;

    @FXML
    private Button myRecepiesButton;

    @FXML
    private Button randomeRecepieButton;

    @FXML
    void initialize() {
        assert headerText != null : "fx:id=\"headerText\" was not injected: check your FXML file 'MainPage.fxml'.";
        assert myRecepiesButton != null : "fx:id=\"myRecepiesButton\" was not injected: check your FXML file 'MainPage.fxml'.";
        assert randomeRecepieButton != null : "fx:id=\"randomeRecepieButton\" was not injected: check your FXML file 'MainPage.fxml'.";

    }

    public void setheadertext(String username){
        headerText.setText(username + "´s cookbook.");
    }

}
