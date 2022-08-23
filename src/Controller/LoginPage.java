package Controller;

import Database.QueryTables.AppointmentsTable;
import Database.QueryTables.UsersTable;
import Model.Appointment;
import Model.User;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.TimeZone;

// Login Controller class implements initializable interface
// This class is used by LoginPage view to log in the user
public class LoginPage implements Initializable {

    // View variables
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
    // Switches between French and English depending on user's system language settings
    public ResourceBundle rb = ResourceBundle.getBundle("Languages/Lang", Locale.getDefault());

    // Initialize the login page
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Check user language and change label if necessary
        ChangeUserLanguage(rb);
        // Check user locale and replace label
        CheckUserTZ(rb);
    }

    // Method to change language using Resource Bundle in Language package
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
    public void CheckUserTZ(ResourceBundle rb) {
        if (TimeZone.getDefault().getID() != null) {
            // Set LocaleLabel to display user's timezone
            LocaleLabel.setText(TimeZone.getDefault().getID());
        } else {
            LocaleLabel.setText(rb.getString("TimezoneError"));
        }
    }

    // Login button method
    @FXML
    void OnActionLogin(ActionEvent event) {
        String Username = UsernameTextField.getText();
        String Password = PasswordTextField.getText();
        // if either username or password is empty, display error message
        if (Username.isEmpty() || Password.isEmpty()) {
            SendLoginEmptyErrorMessage(rb);
        }
        // Check if username and password are valid
        boolean ValidationCheck = ValidateUser(Username, Password);
        if (ValidationCheck) {
            // If user is valid, display successful login in login_activity.txt
            LoginTracker("Successful Login Attempt", Username);
            // Show if valid user has any appointments
            ShowAppointments(Username, rb);
            // Navigate to home page
            ControllerUtils.NavigateToWindow(event, "/View/HomePage.fxml", "Home Page");
        } else {
            // If user is invalid, display unsuccessful login in login_activity.txt
            LoginTracker("Unsuccessful Login Attempt", Username);
            // Display pop-up error message if incorrect login
            SendInvalidLoginErrorMessage(rb);
        }
    }

    // Method to track login attempts in text file login_activity.txt
    public void LoginTracker(String LoginStatus, String Username) {
        try {
            // Get user's system time zone
            ZoneId UserTZ = ZoneId.systemDefault();
            // Get current system time
            ZonedDateTime ts = ZonedDateTime.now(UserTZ);

            // Make time readable and add user's timezone info to file
            DateTimeFormatter readableTime = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss O");

            // Initialize tracker file and add info if one doesn't exist
            // If file already exists, append username, status, and readable time info to it
            // Close file when done
            FileWriter fw = new FileWriter("login_activity.txt", true);
            fw.write("User: '" + Username + "' - " + LoginStatus + " at " + ts.format(readableTime) + "\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Validate user login and return true if successful
    public boolean ValidateUser(String Username, String Password) {
        boolean check = false;
        // Loop through users and check if username matches
        ObservableList<User> UserList = UsersTable.getUsers();
        for (User user : UserList) {
            if (user.getUsername().equals(Username)) {
                // If username is a match, check password
                if (user.getPassword().equals(Password)) {
                    check = true;
                }
            }
        }
        return check;
    }

    // Method to send alert if username and password fields are empty
    // Displays in correct language
    private void SendLoginEmptyErrorMessage(ResourceBundle rb) {
        Alert EmptyLogin = new Alert(Alert.AlertType.ERROR);
        EmptyLogin.setTitle(rb.getString("Error"));
        EmptyLogin.setHeaderText(rb.getString("ErrHeader"));
        EmptyLogin.setContentText(rb.getString("EmptyErrMessage"));
        EmptyLogin.showAndWait();
    }

    // Method to send alert for incorrect login
    // Displays in correct language
    public void SendInvalidLoginErrorMessage(ResourceBundle rb) {
        // Shows message based on user language
        Alert LoginError = new Alert(Alert.AlertType.ERROR);
        LoginError.setTitle(rb.getString("Error"));
        LoginError.setHeaderText(rb.getString("ErrHeader"));
        LoginError.setContentText(rb.getString("ErrMessage"));
        LoginError.showAndWait();
    }

    // Show upcoming appointments for logged-in user
    // Displays in correct language
    public void ShowAppointments(String Username, ResourceBundle rb) {
        // Get user ID based on valid username
        int UserID = GetUserID(Username);
        // Get number of appointments based on user ID
        int NumAppts = GetUserAppointment(UserID);

        // Create alert to display number of upcoming appointments
        Alert ApptAlert = new Alert(Alert.AlertType.INFORMATION);
        ApptAlert.setTitle(rb.getString("UpAppts"));
        if (NumAppts == 0) {
            ApptAlert.setHeaderText(rb.getString("UpApptsFalse"));
            ApptAlert.showAndWait();
        }
        else {
            // Show number of user appointments if there are any
            ApptAlert.setHeaderText(rb.getString("UpApptsTrue1") + " " + NumAppts + " " + rb.getString("UpApptsTrue2"));
            ApptAlert.showAndWait();
        }
    }

    // Check for num of upcoming appointments for user
    int GetUserAppointment(int UserID) {
        ObservableList<Appointment> AppointmentsList = AppointmentsTable.getAppointments();
        int NumAppts = 0;
        // Loop through appointments from database
        for (Appointment Appointment : AppointmentsList) {
            // Only get number of appointments for user with matching ID
            if (Appointment.getUserID() == UserID) {
                // Only get num of appointments for appointments within 15 minutes
                if (Appointment.getStart().isAfter(LocalDateTime.now()) && Appointment.getStart().isBefore(LocalDateTime.now().plusMinutes(15))) {
                    NumAppts++;
                }
            }
        }
        return NumAppts;
    }

    // Method to get user ID based on username (to check for upcoming appointments)
    int GetUserID(String Username) {
        ObservableList<User> UsersList = UsersTable.getUsers();
        // Initialize user ID with stub integer
        int UserID = -1;
        // Loop through users from database
        for (User User : UsersList) {
            // Check for matching username
            // Note: All usernames must be unique
            if (User.getUsername().equals(Username)) {
                UserID = User.getUserID();
            }
        }
        return UserID;
    }

}
