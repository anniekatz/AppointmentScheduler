package Controller;

import Database.QueryTables.AppointmentsTable;
import Database.QueryTables.ContactsTable;
import Database.QueryTables.CustomersTable;
import Database.QueryTables.UsersTable;
import Model.Appointment;
import Model.Contact;
import Model.Customer;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.time.*;
import java.util.ResourceBundle;

// Appointment Controller class implements Initializable interface
// This class is used by AppointmentPage view to view, create, update, and delete appointments
public class AppointmentPage implements Initializable {

    // Variable declaration
    // Toggle filter variables to filter appointments by month or week
    @FXML
    private ToggleGroup FilterApptTG;
    @FXML
    private RadioButton AllFilterRadioButton;
    @FXML
    private RadioButton MonthFilterRadioButton;
    @FXML
    private RadioButton WeekFilterRadioButton;

    // Tableview variables
    @FXML
    private TableView<Appointment> ApptTable;
    @FXML
    private TableColumn<Appointment, Integer> ApptTableAppointmentIDColumn;
    @FXML
    private TableColumn<Appointment, Integer> ApptTableContactColumn;
    @FXML
    private TableColumn<Appointment, Integer> ApptTableCustomerIDColumn;
    @FXML
    private TableColumn<Appointment, String> ApptTableDescriptionColumn;
    @FXML
    private TableColumn<Appointment, LocalDateTime> ApptTableEndColumn;
    @FXML
    private TableColumn<Appointment, String> ApptTableLocationColumn;
    @FXML
    private TableColumn<Appointment, LocalDateTime> ApptTableStartColumn;
    @FXML
    private TableColumn<Appointment, String> ApptTableTitleColumn;
    @FXML
    private TableColumn<Appointment, String> ApptTableTypeColumn;
    @FXML
    private TableColumn<Appointment, Integer> ApptTableUserIDColumn;

    // Form variables for adding, updating, or deleting appointment
    @FXML
    private TextField AppointmentIDTextField;
    @FXML
    private ComboBox<String> ContactComboBox;
    @FXML
    private ComboBox<String> CustIDComboBox;
    @FXML
    private Button CustomersButton;
    @FXML
    private Button DeleteButton;
    @FXML
    private TextField DescriptionTextField;
    @FXML
    private DatePicker EndDatePicker;
    @FXML
    private ComboBox<String> EndTimeComboBox;
    @FXML
    private Button AddUpdateButton;
    @FXML
    private TextField LocationTextField;
    @FXML
    private Button ReportsButton;
    @FXML
    private DatePicker StartDatePicker;
    @FXML
    private ComboBox<String> StartTimeComboBox;
    @FXML
    private TextField TitleTextField;
    @FXML
    private TextField TypeTextField;
    @FXML
    private ComboBox<String> UserIDComboBox;
    @FXML
    private Button ResetButton;

    // Initialize FX page method
    @Override
    public void initialize(URL url, ResourceBundle resources) {
        // Initialize Appointment tableview columns
        ApptTableAppointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("AppointmentID"));
        ApptTableContactColumn.setCellValueFactory(new PropertyValueFactory<>("ContactID"));
        ApptTableCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        ApptTableDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
        ApptTableEndColumn.setCellValueFactory(new PropertyValueFactory<>("End"));
        ApptTableLocationColumn.setCellValueFactory(new PropertyValueFactory<>("Location"));
        ApptTableStartColumn.setCellValueFactory(new PropertyValueFactory<>("Start"));
        ApptTableTitleColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
        ApptTableTypeColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
        ApptTableUserIDColumn.setCellValueFactory(new PropertyValueFactory<>("UserID"));
        // Get appointments from database and populate tableview
        ObservableList<Appointment> ApptList = AppointmentsTable.getAppointments();
        ApptTable.setItems(ApptList);

        // Initialize Combo Boxes
        InitializeComboBoxes();

        // Initialize Start DatePicker and End DatePicker
        StartDatePicker.setValue(LocalDate.now());
        EndDatePicker.setValue(LocalDate.now());
        EndDatePicker.setEditable(false);

        // JavaFX Lambda expression for tableview row selection listener
        // Lambda is ideal as this custom JavaFX listener method is called only for this form and it is only called in one method
        // Populate form with selected appointment info, and depopulate form upon deselection
        ApptTable.getSelectionModel().selectedItemProperty().addListener((observable, oldSelection, newSelection) -> {
            if (newSelection != null) {
                Appointment appointment = ApptTable.getSelectionModel().getSelectedItem();
                // Populate form based on selected appointment
                PopulateForm(appointment);
            }
        });
    }

    // Method to initialize Combo Boxes with valid data
    void InitializeComboBoxes() {
        // Initialize Contact ComboBox with valid data from contacts table from database
        ObservableList<Contact> ContactList = ContactsTable.getContacts();
        ObservableList<String> ContactNames = FXCollections.observableArrayList();
        for (Contact Contact : ContactList) {
            ContactNames.add(Contact.getContactID() + " (" + Contact.getName() + ")");
        }
        ContactComboBox.setItems(ContactNames);
        ContactComboBox.setEditable(true);

        // Initialize UserID Combo Box using user table from database
        ObservableList<User> UserList = UsersTable.getUsers();
        ObservableList<String> UserNames = FXCollections.observableArrayList();
        for (User User : UserList) {
            UserNames.add(User.getUserID() + " (" + User.getUsername() +")");
        }
        UserIDComboBox.setItems(UserNames);
        UserIDComboBox.setEditable(true);

        // Initialize CustomerID ComboBox using customer table from database
        ObservableList<Customer> CustomerList = CustomersTable.getCustomers();
        ObservableList<String> CustomerNames = FXCollections.observableArrayList();
        for (Customer Customer : CustomerList) {
            CustomerNames.add(Customer.getCustomerID() + " (" + Customer.getCustomerName() + ")");
        }
        CustIDComboBox.setItems(CustomerNames);
        CustIDComboBox.setEditable(true);

        // Initialize Start Time Combo Box using business hours converted to system time
        StartTimeComboBox.setItems(StartTimeConversion());
        StartTimeComboBox.setEditable(true);
    }

    // Method to populate form from selected appointment method
    void PopulateForm(Appointment appointment) {
        AppointmentIDTextField.setText(Integer.toString(appointment.getAppointmentID()));
        ContactComboBox.setValue((Integer.toString(appointment.getContactID())));
        CustIDComboBox.setValue(Integer.toString(appointment.getCustomerID()));
        DescriptionTextField.setText(appointment.getDescription());
        EndDatePicker.setValue(appointment.getEnd().toLocalDate());
        EndTimeComboBox.setValue(appointment.getEnd().toLocalTime().toString());
        LocationTextField.setText(appointment.getLocation());
        StartDatePicker.setValue(appointment.getStart().toLocalDate());
        StartTimeComboBox.setValue(appointment.getStart().toLocalTime().toString());
        TitleTextField.setText(appointment.getTitle());
        TypeTextField.setText(appointment.getType());
        UserIDComboBox.setValue(Integer.toString(appointment.getUserID()));
    }

    // Start Time Conversion helper method to populate Start Time Combo Box
    // This method converts business hours (8AM-10PM EST) to system time
    // Uses ControllerUtils class method GetNewTime to get new time
    ObservableList<String> StartTimeConversion() {
        OffsetTime SystemStartTime = ControllerUtils.GetNewTime(ZoneId.of("America/New_York"), ZoneId.systemDefault(),8,0);
        OffsetTime SystemEndTime = ControllerUtils.GetNewTime(ZoneId.of("America/New_York"), ZoneId.systemDefault(),22,0);
        ObservableList<String> StartTimeList = FXCollections.observableArrayList();
        // Every 30 minutes during opening hours, an appointment can be chosen
        // Appointment start time cannot be at closing time
        while (SystemStartTime.isBefore(SystemEndTime)) {
            StartTimeList.add(SystemStartTime.toString().substring(0, SystemStartTime.toString().length() - 6));
            SystemStartTime = SystemStartTime.plusMinutes(30);
        }
        return StartTimeList;
    }

    // Once Start time is chosen, populate list of end times after start time
    // Uses time zone conversion method GetNewTime from ControllerUtils class to convert business hours to system time
    @FXML
    void PopulateEndComboBox(ActionEvent event) {
        if (StartTimeComboBox.getValue() != null) {
            LocalTime StartTime = LocalTime.parse(StartTimeComboBox.getValue());
            OffsetTime EndTime = ControllerUtils.GetNewTime(ZoneId.of("America/New_York"), ZoneId.systemDefault(), 22, 0);
            ObservableList<String> EndTimeList = FXCollections.observableArrayList();
            // An appointment end time can be chosen after the start time
            while (StartTime.isBefore(LocalTime.from(EndTime))) {
                StartTime = StartTime.plusMinutes(30);
                EndTimeList.add(StartTime.toString());
            }
            // Populate combo box
            EndTimeComboBox.setItems(EndTimeList);
            EndTimeComboBox.setEditable(true);
        }
    }

    // Choose appointment date in DatePicker
    @FXML
    void ChooseApptDate(ActionEvent event) {
        LocalDate ApptDate = StartDatePicker.getValue();
        EndDatePicker.setValue(ApptDate);
        // End date for appointment must be the same as start date
        EndDatePicker.setEditable(false);
        }

    // Filter appointment table view by all, this month, or the next week
    // Radio buttons used to navigate between views
    @FXML
    void FilterApptView(ActionEvent event) {
        ObservableList<Appointment> ApptList = AppointmentsTable.getAppointments();
        // if AllFilterRadioButton is selected, show all appointments
        if (AllFilterRadioButton.isSelected()) {
            ApptTable.setItems(ApptList);
        }
        // if MonthFilterRadioButton is selected, show appointments for the current month
        else if (MonthFilterRadioButton.isSelected()) {
            // For appointments in appointment list, if the start date is in the current month, add to filtered list
            ObservableList<Appointment> FilteredApptList = FXCollections.observableArrayList();
            for (Appointment Appt : ApptList) {
                if (Appt.getStart().getMonthValue() == LocalDateTime.now().getMonthValue()) {
                    FilteredApptList.add(Appt);
                }
            }
            ApptTable.setItems(FilteredApptList);
        }
        // If WeekFilterRadioButton selected, show appointments for the next week
        else if (WeekFilterRadioButton.isSelected()) {
            ObservableList<Appointment> FilteredApptList = FXCollections.observableArrayList();
            // If appointment is within the next 7 days, add to filtered list
            for (Appointment Appt : ApptList) {
                if (Appt.getStart().isAfter(LocalDateTime.now()) && Appt.getStart().isBefore(LocalDateTime.now().plusDays(7))) {
                    FilteredApptList.add(Appt);
                }
            }
            ApptTable.setItems(FilteredApptList);
        }
    }

    // Add or Update Appointment method for add/update button
    // Updates in database then tableview updates
    @FXML
    void AddUpdateAppointment(ActionEvent event) {
        // if any part of form (besides autogenerated ID) is null, alert shows
        // Appointment will not update if required fields are null
        if (ContactComboBox.getValue() == null || CustIDComboBox.getValue() == null || LocationTextField.getText().isEmpty() || EndDatePicker.getValue() == null || EndTimeComboBox.getValue() == null || StartDatePicker.getValue() == null || StartTimeComboBox.getValue() == null || TypeTextField.getText().isEmpty() || UserIDComboBox.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please fill out required fields (Location, Type, Customer, User, Contact, Start Date/Time, End Date/Time)");
            alert.showAndWait();
        }
        else {
            // Convert chosen appointment start and end times to UTC to store database
            LocalDateTime LocalStart = LocalDateTime.of(StartDatePicker.getValue(), LocalTime.parse(StartTimeComboBox.getValue()));
            LocalDateTime LocalEnd = LocalDateTime.of(EndDatePicker.getValue(), LocalTime.parse(EndTimeComboBox.getValue()));

            // Uses ConvertToUTC method from ControllerUtils class to convert local time to UTC
            // Turns Date into String to store in database
            String Start = ControllerUtils.ConvertToUTC(LocalStart);
            String End = ControllerUtils.ConvertToUTC(LocalEnd);

            // Get ID Values from combo boxes to store in database
            String UserID = UserIDComboBox.getValue();
            String CustomerID = CustIDComboBox.getValue();
            String ContactID = ContactComboBox.getValue();

            // If a space exists in any combo boxes, get the ID from the end of the string
            // get substring up until " ", which will only be ID value. After space is the name associated with the ID
            if (UserID.contains(" ")) {
                UserID = UserID.substring(0, UserID.indexOf(" "));
            }
            if (CustomerID.contains(" ")) {
                CustomerID = CustomerID.substring(0, CustomerID.indexOf(" "));
            }
            if (ContactID.contains(" ")) {
                ContactID = ContactID.substring(0, ContactID.indexOf(" "));
            }

            // Get int from string to store in database
            int intUserID = Integer.parseInt(UserID);
            int intCustomerID = Integer.parseInt(CustomerID);
            int intContactID = Integer.parseInt(ContactID);

            // Add ApptID variable to help check appointment overlap
            // If adding an appointment, a stub variable (-1) will be used for appointment overlap check
            // If updating existing appointment, ID is used; must not compare appointment time to itself
            int ApptID;
            if (AppointmentIDTextField.getText().isEmpty()) {
                ApptID = -1;
            }
            else {
                ApptID = Integer.parseInt(AppointmentIDTextField.getText());
            }

            // If customer has an overlapping appointment, show error message
            // Appointment will not be added if there is an overlap
            if (CheckAppointmentOverlap(LocalStart, LocalEnd, ApptID, intCustomerID)) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("Customer cannot have overlapping appointments");
                alert.showAndWait();
            }
            else {
                // If appointment ID is empty, add new appointment
                if (AppointmentIDTextField.getText().isEmpty()) {
                    AppointmentsTable.addAppointment(TitleTextField.getText(), DescriptionTextField.getText(), LocationTextField.getText(), TypeTextField.getText(), Start, End, intCustomerID, intUserID, intContactID);
                }
                // If appointment ID is not empty, update existing appointment
                else {
                    int AppointmentID = Integer.parseInt(AppointmentIDTextField.getText());
                    AppointmentsTable.updateAppointment(AppointmentID, TitleTextField.getText(), DescriptionTextField.getText(), LocationTextField.getText(), TypeTextField.getText(), Start, End, intCustomerID, intUserID, intContactID);
                }
                // Update TableView based on new or updated appointment
                ObservableList<Appointment> FullAppointmentList = AppointmentsTable.getAppointments();
                ApptTable.setItems(FullAppointmentList);
            }
        }
    }

    // Delete appointment method for delete button
    // Removes appointment from database and tableview
    @FXML
    void DeleteAppointment(ActionEvent event) {
        // Pop-up alert box to confirm deletion
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete");
        alert.setHeaderText("Are you sure you want to delete this appointment?");
        alert.setContentText("This cannot be undone.");
        alert.showAndWait();
        // If No, nothing will delete
        // If Yes, delete appointment from database and a confirmation pop-up will display
        if (alert.getResult() == ButtonType.OK) {
            AppointmentsTable.deleteAppointment(ApptTable.getSelectionModel().getSelectedItem().getAppointmentID());
            ApptTable.getItems().remove(ApptTable.getSelectionModel().getSelectedItem());
            Alert DeleteConfirmed = new Alert(Alert.AlertType.INFORMATION);
            DeleteConfirmed.setTitle("Appointment Deleted");
            DeleteConfirmed.setHeaderText("Appointment Deleted");
            DeleteConfirmed.setContentText("Appointment has been deleted.");
            DeleteConfirmed.showAndWait();
        }
    }

    // Method to check if customer has any overlapping appointments
    // Loops through a customer's appointments
    // if appointment start and end overlaps another appointment start and end, return true
    boolean CheckAppointmentOverlap(LocalDateTime start, LocalDateTime end, int ApptID, int CustID) {
        boolean check = false;
        for (Appointment Appt : AppointmentsTable.getAppointments()) {
            // Only the customer's appointments are checked for overlap
            if (CustID == Appt.getCustomerID()) {
                // If updating an existing appointment, do not count existing appointment as overlapping appointment
                if (ApptID != Appt.getAppointmentID()) {
                    if (Appt.getStart().equals(start) || Appt.getEnd().equals(end)) {
                        check = true;
                    } else if (Appt.getStart().isAfter(start) && Appt.getStart().isBefore(end)) {
                        check = true;
                    } else if (Appt.getEnd().isAfter(start) && Appt.getEnd().isBefore(end)) {
                        check = true;
                    } else if (Appt.getStart().isBefore(start) && Appt.getEnd().isAfter(end)) {
                        check = true;
                    }
                }
            }
        }
        return check;
    }

    // Method to reset form and/or deselect row if Reset Button is clicked
    @FXML
    void ResetButtonClicked(ActionEvent event) throws RuntimeException {
        // Deselect row if one is selected
        if (ApptTable.getSelectionModel().getSelectedItem() != null) {
            ApptTable.getSelectionModel().clearSelection();
        }
        // Reset form fields
        AppointmentIDTextField.setText("");
        ContactComboBox.setValue("");
        CustIDComboBox.setValue("");
        DescriptionTextField.setText("");
        StartDatePicker.setValue(LocalDate.now());
        LocationTextField.setText("");
        StartTimeComboBox.setValue(null);
        EndTimeComboBox.setValue(null);
        TitleTextField.setText("");
        TypeTextField.setText("");
        UserIDComboBox.setValue("");
    }

    // Navigation button methods
    // Use ControllerUtils Navigation to navigate to other scenes
    @FXML
    void NavToCustomers(ActionEvent event) {
        ControllerUtils.NavToCustomers(event);
    }
    @FXML
    void NavToReports(ActionEvent event) {
        ControllerUtils.NavToReports(event);
    }
}

