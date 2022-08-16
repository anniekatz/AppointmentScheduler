package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginPage implements Initializable {
    public Button LoginButton;
    public Label LocaleLabel;
    public Label LangLabel;
    public TextField UserIDTextField;
    public TextField PasswordTextField;
    public Label TitleLabel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initializing");
    }

    public void OnButtonLogin(ActionEvent event) {
        System.out.println("OnButtonLogin clicked");
    }
}
