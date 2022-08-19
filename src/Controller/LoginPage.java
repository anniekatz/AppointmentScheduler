package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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

    // Resource bundle for language and locale
    public ResourceBundle rb = ResourceBundle.getBundle("Languages/Lang", Locale.getDefault());

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        System.out.println("Initializing");

        // Check user language and change display if necessary
        ChangeUserLanguage(rb);
        // Check user locale and replace label
        CheckUserTZ();
    }

    // Login button method
    @FXML
    void OnActionLogin(ActionEvent event) throws IOException, SQLException {
        String Username = UsernameTextField.getText();
        String Password = PasswordTextField.getText();
        boolean ValidationCheck = ValidateUser(Username, Password);
        if (ValidationCheck) {
            LoginTracker("Successful Login Attempt", Username);
            ShowAppointments(Username, rb);
            ControllerUtils.NavigateToWindow(event, "/View/HomePage.fxml", "Home Page");
        } else {
            LoginTracker("Unsuccessful Login Attempt", Username);
            // Pop-up error message if incorrect login
            SendErrorMessage(rb);
        }
    }

    // Track login attempts in text file
    public void LoginTracker(String LoginStatus, String Username) throws IOException {
        ZoneId UserTZ = ZoneId.systemDefault();
        ZonedDateTime ts = ZonedDateTime.now(UserTZ);
        DateTimeFormatter readable = DateTimeFormatter.ofPattern( "uuuu-MM-dd HH:mm:ss O" );
        FileWriter fw = new FileWriter("login_activity.txt", true);
        fw.write("User: '" + Username + "' - " + LoginStatus + " at " + ts.format(readable) + "\n");
        fw.close();
    }

    // Validate user login
    public boolean ValidateUser(String Username, String Password) throws IOException, SQLException {
        boolean LoginStatus;
        LoginStatus = Database.QueryTables.UsersTable.CheckValidUsers(Username, Password);
        if (LoginStatus) {
            System.out.println("Valid user");
        } else {
            System.out.println("Invalid user");
        }
        return LoginStatus;
    }


    // Change language if necessary
    public void ChangeUserLanguage(ResourceBundle rb) {
        if (rb.getLocale().getLanguage().equals("fr")) {
            LangLabel.setText(rb.getString("Lang"));
            TitleLabel.setText(rb.getString("Title"));
            LoginButton.setText(rb.getString("Login"));
            UsernameTextField.setPromptText(rb.getString("Username"));
            PasswordTextField.setPromptText(rb.getString("Password"));
        } else {
            LangLabel.setText(rb.getString("Lang"));
        }
    }

    // Check user's system timezone
    public void CheckUserTZ() {
        if (TimeZone.getDefault().getID() != null) {
            LocaleLabel.setText((TimeZone.getDefault().getID()).substring(0, 3));
        } else {
            LocaleLabel.setText("ERR");
        }
    }

    // Create alert for incorrect login
    public void SendErrorMessage(ResourceBundle rb) {
        Alert LoginError = new Alert(Alert.AlertType.ERROR);
        LoginError.setTitle(rb.getString("Error"));
        LoginError.setHeaderText(rb.getString("ErrHeader"));
        LoginError.setContentText(rb.getString("ErrMessage"));
        LoginError.showAndWait();
    }

    // Show upcoming appointments for user
    public void ShowAppointments(String Username, ResourceBundle rb) throws IOException {
        // TODO: Check database for num appts for user
        int NumAppts = 0;
        Alert ApptAlert = new Alert(Alert.AlertType.INFORMATION);
        ApptAlert.setTitle(rb.getString("UpAppts"));
        if (NumAppts == 0) {
            ApptAlert.setHeaderText(rb.getString("UpApptsFalse"));
            ApptAlert.showAndWait();
        }
        else {
            ApptAlert.setHeaderText(rb.getString("UpApptsTrue1") + " " + NumAppts + " " + rb.getString("UpApptsTrue2"));
            ApptAlert.showAndWait();
        }
    }

}
