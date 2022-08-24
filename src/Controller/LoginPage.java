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

/**
 * The Login Controller class implements the initializable interface.
 * This class is linked to the Login Page view to log in the user.
 */
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
    // Switches between French and English depending on user's system language settings
    public ResourceBundle rb = ResourceBundle.getBundle("Languages/Lang", Locale.getDefault());

    /**
     * This method initializes the login page.
     * It sets the language and locale labels to the current language and locale.
     * @param url URL location of the associated FXML file
     * @param resourceBundle ResourceBundle resource of the FXML file
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Check user language and change label if necessary
        ChangeUserLanguage(rb);
        // Check user locale and replace label
        CheckUserTZ(rb);
    }

    /**
     * This method replaces items on the login form with correct system language (English or French).
     * @param rb ResourceBundle language page to be used.
     */
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

    /**
     * This method checks user's system timezone and displays it in a label.
     * @param rb ResourceBundle language page to be used.
     */
    public void CheckUserTZ(ResourceBundle rb) {
        if (TimeZone.getDefault().getID() != null) {
            // Set LocaleLabel to display user's timezone
            LocaleLabel.setText(TimeZone.getDefault().getID());
        } else {
            LocaleLabel.setText(rb.getString("TimezoneError"));
        }
    }

    /**
     * This method logs the user in and navigates to the home page if the login is successful.
     * It will show an error message if the login is unsuccessful.
     * It will track user login attempts upon button clicks.
     * @param event ActionEvent is triggered when the login button is clicked.
     */
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

    /**
     * This method tracks login attempts in login_activity.txt.
     * It runs upon login button click.
     * @param LoginStatus String status of the login attempt.
     * @param Username String username of the user attempting to log in.
     */
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

    /**
     * This method validates the user's username and password upon login button click.
     * @param Username String username of the user attempting to log in.
     * @param Password String password of the user attempting to log in.
     * @return boolean true if user is valid, false if user is invalid.
     */
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

    /**
     * This method displays a pop-up error message if the username or password is empty upon login button click.
     * @param rb ResourceBundle language to be used to display error message.
     */
    private void SendLoginEmptyErrorMessage(ResourceBundle rb) {
        Alert EmptyLogin = new Alert(Alert.AlertType.ERROR);
        EmptyLogin.setTitle(rb.getString("Error"));
        EmptyLogin.setHeaderText(rb.getString("ErrHeader"));
        EmptyLogin.setContentText(rb.getString("EmptyErrMessage"));
        EmptyLogin.showAndWait();
    }

    /**
     * This method displays a pop-up error message if the username or password is invalid upon login button click.
     * @param rb ResourceBundle language to be used to display error message.
     */
    public void SendInvalidLoginErrorMessage(ResourceBundle rb) {
        // Shows message based on user language
        Alert LoginError = new Alert(Alert.AlertType.ERROR);
        LoginError.setTitle(rb.getString("Error"));
        LoginError.setHeaderText(rb.getString("ErrHeader"));
        LoginError.setContentText(rb.getString("ErrMessage"));
        LoginError.showAndWait();
    }

    /**
     * This method displays a pop-up when the user successfully logs in.
     * It shows the number of appointments the user has in the next 15 minutes, if any, in the correct language.
     * @param Username String username of the user who just logged in.
     * @param rb ResourceBundle language to be used to display error message.
     */
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

    /**
     * This method gets the number of appointments the user has in the next 15 minutes.
     * It is used to display a message to the user if there are any upcoming appointments upon login.
     * @param UserID int user ID of the user who just logged in
     * @return int number of appointments the user has in the next 15 minutes.
     */
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

    /**
     * This method gets the user ID based on the username of the user who just logged in, to check for upcoming appointments.
     * @param Username String username of the user who just logged in.
     * @return int user ID of the user who just logged in.
     */
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
