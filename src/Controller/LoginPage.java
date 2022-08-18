package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class LoginPage implements Initializable {
    @FXML
    private Label LangLabel;

    @FXML
    private Label LocaleLabel;

    @FXML
    private Button LoginButton;

    @FXML
    private TextField PasswordTextField;

    @FXML
    private Label TitleLabel;

    @FXML
    private TextField UserIDTextField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initializing");
    }

    // Check user language and change display if necessary
    // Check user locale and replace label


    @FXML
    void OnActionLogin(ActionEvent event) throws IOException {
        // Check if valid user input
        // If not valid,
        // Append failure to user activity log
        // Pop up error message

        // If valid,
        // Append success to user activity log
        String LoginStatus = "Successful Login Attempt";
        LoginTracker(LoginStatus);
        // Display upcoming appointment message
        // Navigate to home page
        ControllerUtils.NavigateToWindow(event, "/View/HomePage.fxml", "Home Page");
    }

    public void LoginTracker(String LoginStatus) throws IOException {
        ZoneId UserTZ = ZoneId.systemDefault();
        ZonedDateTime ts = ZonedDateTime.now(UserTZ);
        DateTimeFormatter readable = DateTimeFormatter.ofPattern( "uuuu-MM-dd HH:mm:ss O" );
        FileWriter fw = new FileWriter("login_activity.txt", true);
        fw.write("User: '" + UserIDTextField.getText() + "' - " + LoginStatus + " at " + ts.format(readable) + "\n");
        fw.close();
    }
}
