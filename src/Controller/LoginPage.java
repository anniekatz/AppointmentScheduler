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
        String UserID = UserIDTextField.getText();
        String Password = PasswordTextField.getText();
        Boolean ValidationCheck = ValidateUser(UserID, Password);
        if (ValidationCheck) {
            LoginTracker("Successful Login Attempt", UserID);
            ShowAppointments(UserID);
            ControllerUtils.NavigateToWindow(event, "/View/HomePage.fxml", "Home Page");
        } else {
            LoginTracker("Unsuccessful Login Attempt", UserID);
            System.out.println("Invalid Login");
            // pop up error message
        }
    }

    public void LoginTracker(String LoginStatus, String UserID) throws IOException {
        ZoneId UserTZ = ZoneId.systemDefault();
        ZonedDateTime ts = ZonedDateTime.now(UserTZ);
        DateTimeFormatter readable = DateTimeFormatter.ofPattern( "uuuu-MM-dd HH:mm:ss O" );
        FileWriter fw = new FileWriter("login_activity.txt", true);
        fw.write("User: '" + UserID + "' - " + LoginStatus + " at " + ts.format(readable) + "\n");
        fw.close();
    }

    public Boolean ValidateUser(String UserID, String Password) throws IOException {
        Boolean LoginStatus;
        if (UserID.equals("test") && Password.equals("test")) {
            System.out.println("Valid user");
            LoginStatus = true;
        } else {
            System.out.println("Invalid user");
            LoginStatus = false;

        }
        return LoginStatus;
    }

    public void ShowAppointments(String UserID) throws IOException {
        System.out.println("Showing appointments for " + UserID);
        // Show upcoming appointments
    }


}
