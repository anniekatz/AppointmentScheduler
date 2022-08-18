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
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;

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
    private TextField UsernameTextField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("Initializing");
        // Check user language and change display if necessary
        String UserLang = CheckUserLanguage();
        // Check user locale and replace label
        String TZ = CheckUserTZ();
        LocaleLabel.setText(TZ);
    }




    @FXML
    void OnActionLogin(ActionEvent event) throws IOException {
        String Username = UsernameTextField.getText();
        String Password = PasswordTextField.getText();
        boolean ValidationCheck = ValidateUser(Username, Password);
        if (ValidationCheck) {
            LoginTracker("Successful Login Attempt", Username);
            ShowAppointments(Username);
            ControllerUtils.NavigateToWindow(event, "/View/HomePage.fxml", "Home Page");
        } else {
            LoginTracker("Unsuccessful Login Attempt", Username);
            System.out.println("Invalid Login");
            // pop up error message
        }
    }

    public void LoginTracker(String LoginStatus, String Username) throws IOException {
        ZoneId UserTZ = ZoneId.systemDefault();
        ZonedDateTime ts = ZonedDateTime.now(UserTZ);
        DateTimeFormatter readable = DateTimeFormatter.ofPattern( "uuuu-MM-dd HH:mm:ss O" );
        FileWriter fw = new FileWriter("login_activity.txt", true);
        fw.write("User: '" + Username + "' - " + LoginStatus + " at " + ts.format(readable) + "\n");
        fw.close();
    }

    public boolean ValidateUser(String Username, String Password) throws IOException {
        boolean LoginStatus;
        if (Username.equals("test") && Password.equals("test")) {
            System.out.println("Valid user");
            LoginStatus = true;
        } else {
            System.out.println("Invalid user");
            LoginStatus = false;

        }
        return LoginStatus;
    }

    public void ShowAppointments(String Username) throws IOException {
        System.out.println("Showing appointments for " + Username);
        // Show upcoming appointments
    }

    public String CheckUserLanguage() {
        if (Locale.getDefault().getDisplayLanguage() == "French") {
            LangLabel.setText("FR");
            return "fr";
        } else {
            LangLabel.setText("EN");
            return "en";
        }
    }

    public String CheckUserTZ() {
        if (TimeZone.getDefault().getID() != null) {
            return (TimeZone.getDefault().getID()).substring(0, 3);
        } else {
            return "NULL";
        }
    }

}
