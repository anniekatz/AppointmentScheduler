package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginPage implements Initializable {
    public Button LoginButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initializing");
    }

    public void OnButtonLogin(ActionEvent actionEvent) {
        System.out.println("OnButtonLogin clicked");
    }
}
