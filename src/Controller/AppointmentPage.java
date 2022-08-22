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

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.ResourceBundle;

public class AppointmentPage implements Initializable {

    // Toggle filters for appointment table
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

    // Form variables
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

    // Initialize method
    @Override
    public void initialize(URL url, ResourceBundle resources) {
        // Initialize Appointment tableview
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

        ObservableList<Appointment> ApptList = AppointmentsTable.GetAppointments();
        ApptTable.setItems(ApptList);

        // Initialize ComboBoxes
        InitializeComboBoxes();

        // Initialize StartDatePicker
        StartDatePicker.setValue(LocalDate.now());
        EndDatePicker.setValue(LocalDate.now());
        EndDatePicker.setEditable(false);

        // Lambda expression for tableview row selection
        // Populate form with selected appointment info
        ApptTable.getSelectionModel().selectedItemProperty().addListener((observable, oldSelection, newSelection) -> {
            if (newSelection != null) {
                Appointment appointment = ApptTable.getSelectionModel().getSelectedItem();
                PopulateForm(appointment);
            }
        });
    }

    void PopulateForm(Appointment appointment) {
        AppointmentIDTextField.setText(Integer.toString(appointment.getAppointmentID()));
        ContactComboBox.setValue(Integer.toString(appointment.getContactID()));
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


    // Initialize ComboBoxes
    void InitializeComboBoxes() {
        // Initialize Contact ComboBox
        ObservableList<Contact> ContactList = ContactsTable.GetContacts();
        ObservableList<String> ContactNames = FXCollections.observableArrayList();
        for (Contact Contact : ContactList) {
            ContactNames.add(Contact.getContactID() + " (" + Contact.getName() + ")");
        }
        ContactComboBox.setItems(ContactNames);
        ContactComboBox.setEditable(true);

        // Initialize UserID ComboBox
        ObservableList<User> UserList = UsersTable.GetUsers();
        ObservableList<String> UserNames = FXCollections.observableArrayList();
        for (User User : UserList) {
            UserNames.add(User.getUserID() + " (" + User.getUsername() +")");
        }
        UserIDComboBox.setItems(UserNames);
        UserIDComboBox.setEditable(true);

        // Initialize CustomerID ComboBox
        ObservableList<Customer> CustomerList = CustomersTable.GetCustomers();
        ObservableList<String> CustomerNames = FXCollections.observableArrayList();
        for (Customer Customer : CustomerList) {
            CustomerNames.add(Customer.getCustomerID() + " (" + Customer.getCustomerName() + ")");
        }
        CustIDComboBox.setItems(CustomerNames);
        CustIDComboBox.setEditable(true);

        // Initialize Start Time ComboBox
        StartTimeComboBox.setItems(StartTimeConversion());
        StartTimeComboBox.setEditable(true);

    }

    // Start Time Conversion helper method to populate Start Time ComboBox
    ObservableList<String> StartTimeConversion() {
        OffsetTime SystemStartTime = ControllerUtils.GetNewTime(ZoneId.of("America/New_York"), ZoneId.systemDefault(),8,0);
        OffsetTime SystemEndTime = ControllerUtils.GetNewTime(ZoneId.of("America/New_York"), ZoneId.systemDefault(),22,0);
        ObservableList<String> StartTimeList = FXCollections.observableArrayList();

        while (SystemStartTime.isBefore(SystemEndTime)) {
            StartTimeList.add(SystemStartTime.toString().substring(0, SystemStartTime.toString().length() - 6));
            SystemStartTime = SystemStartTime.plusMinutes(30);
        }

        return StartTimeList;
    }

    // Once Start time is chosen, populate list of end times
    @FXML
    void PopulateEndComboBox(ActionEvent event) {
        LocalTime StartTime = LocalTime.parse(StartTimeComboBox.getValue());
        OffsetTime EndTime = ControllerUtils.GetNewTime(ZoneId.of("America/New_York"), ZoneId.systemDefault(),22,0);
        ObservableList<String> EndTimeList = FXCollections.observableArrayList();

        while (StartTime.isBefore(LocalTime.from(EndTime))) {
            StartTime = StartTime.plusMinutes(30);
            EndTimeList.add(StartTime.toString());
        }

        EndTimeComboBox.setItems(EndTimeList);
        EndTimeComboBox.setEditable(true);
    }

    // Choose appointment date
    @FXML
    void ChooseApptDate(ActionEvent event) {
        LocalDate ApptDate = StartDatePicker.getValue();
        if (ApptDate.isBefore(LocalDate.now())) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Invalid Date");
            alert.setContentText("Appointment date cannot be in the past.");
            alert.showAndWait();
            StartDatePicker.setValue(LocalDate.now());
        }
        else {
            EndDatePicker.setValue(ApptDate);
            EndDatePicker.setEditable(false);
        }

    }

    // Filter appointment view by all, this month, or the next week
    @FXML
    void FilterApptView(ActionEvent event) {
        ObservableList<Appointment> ApptList = AppointmentsTable.GetAppointments();
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

    // Add or Update Appt
    @FXML
    void AddUpdateAppointment(ActionEvent event) throws SQLException {
        // if any part of form (besides autogenerated ID) is null, try again
        if (ContactComboBox.getValue() == null || CustIDComboBox.getValue() == null || LocationTextField.getText().isEmpty() || EndDatePicker.getValue() == null || EndTimeComboBox.getValue() == null || StartDatePicker.getValue() == null || StartTimeComboBox.getValue() == null || TypeTextField.getText().isEmpty() || UserIDComboBox.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText("Please fill out required fields (Location, Type, Customer, User, Contact, Start Date/Time, End Date/Time)");
            alert.showAndWait();
        }
        else {
            // Convert time to UTC to store database
            LocalDateTime LocalStart = LocalDateTime.of(StartDatePicker.getValue(), LocalTime.parse(StartTimeComboBox.getValue()));
            LocalDateTime LocalEnd = LocalDateTime.of(EndDatePicker.getValue(), LocalTime.parse(EndTimeComboBox.getValue()));
            ZonedDateTime Start = ConvertToUTC(LocalStart);
            ZonedDateTime End = ConvertToUTC(LocalEnd);

            // Get ID Values from combo boxes to store in database
            int UserID = Integer.parseInt(UserIDComboBox.getValue().substring(0, UserIDComboBox.getValue().indexOf(" ")));
            int CustID = Integer.parseInt(CustIDComboBox.getValue().substring(0, CustIDComboBox.getValue().indexOf(" ")));
            int ContactID = Integer.parseInt(ContactComboBox.getValue().substring(0, ContactComboBox.getValue().indexOf(" ")));

        // If appointment ID is empty, add new appointment
            if (AppointmentIDTextField.getText().isEmpty()) {
                AppointmentsTable.AddAppointment(TitleTextField.getText(), DescriptionTextField.getText(), LocationTextField.getText(), TypeTextField.getText(), Start, End, UserID, CustID, ContactID);
            }
        // If appointment ID is not empty, update existing appointment
            else {
                int AppointmentID = Integer.parseInt(AppointmentIDTextField.getText());
                AppointmentsTable.UpdateAppointment(AppointmentID, TitleTextField.getText(), DescriptionTextField.getText(), LocationTextField.getText(), TypeTextField.getText(), Start, End, UserID, CustID, ContactID);
            }
            // Update TableView based on new appointment
            ObservableList<Appointment> FullAppointmentList = AppointmentsTable.GetAppointments();
            ApptTable.setItems(FullAppointmentList);
        }
    }

    // Delete appointment
    @FXML
    void DeleteAppointment(ActionEvent event) {
        // pop up box to confirm deletion
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Delete");
        alert.setHeaderText("Are you sure you want to delete this appointment?");
        alert.setContentText("This cannot be undone.");
        alert.showAndWait();
        // if yes, delete appointment
        if (alert.getResult() == ButtonType.OK) {
            AppointmentsTable.DeleteAppointment(ApptTable.getSelectionModel().getSelectedItem().getAppointmentID());
            ApptTable.getItems().remove(ApptTable.getSelectionModel().getSelectedItem());
        }
    }

    ZonedDateTime ConvertToUTC(LocalDateTime LocalDateTime) {
        ZonedDateTime SystemDateTime = LocalDateTime.atZone(ZoneId.systemDefault());
        ZonedDateTime UTCDateTime = SystemDateTime.withZoneSameInstant(ZoneId.of("UTC"));
        System.out.println(UTCDateTime);
        return UTCDateTime;
    }

    @FXML
    void ResetButtonClicked(ActionEvent event) throws RuntimeException {
        if (ApptTable.getSelectionModel().getSelectedItem() != null) {
            ApptTable.getSelectionModel().clearSelection();
        }
        AppointmentIDTextField.setText("");
        ContactComboBox.setValue("");
        CustIDComboBox.setValue("");
        DescriptionTextField.setText("");
        EndDatePicker.setValue(LocalDate.now());
        EndTimeComboBox.setValue("");
        LocationTextField.setText("");
        StartDatePicker.setValue(LocalDate.now());
        StartTimeComboBox.setValue("");
        TitleTextField.setText("");
        TypeTextField.setText("");
        UserIDComboBox.setValue("");
    }
    // Navigation button methods
    @FXML
    void NavToCustomers(ActionEvent event) throws IOException {
        ControllerUtils.NavToCustomers(event);
    }
    @FXML
    void NavToReports(ActionEvent event) throws IOException {
        ControllerUtils.NavToReports(event);
    }

}

