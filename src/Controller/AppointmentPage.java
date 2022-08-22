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

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
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
    private ComboBox<?> EndTimeComboBox;
    @FXML
    private Button AddUpdateButton;
    @FXML
    private TextField LocationTextField;
    @FXML
    private Button ReportsButton;
    @FXML
    private DatePicker StartDatePicker;
    @FXML
    private ComboBox<?> StartTimeComboBox;
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
    }


    // Initialize ComboBoxes
    void InitializeComboBoxes() {
        // Initialize Contact ComboBox
        ObservableList<Contact> ContactList = ContactsTable.GetContacts();
        ObservableList<String> ContactNames = FXCollections.observableArrayList();
        for (Contact Contact : ContactList) {
            ContactNames.add(Contact.getName());
        }
        ContactComboBox.setItems(ContactNames);
        ContactComboBox.setEditable(true);

        // Initialize UserID ComboBox
        ObservableList<User> UserList = UsersTable.GetUsers();
        ObservableList<String> UserNames = FXCollections.observableArrayList();
        for (User User : UserList) {
            UserNames.add(User.getUsername());
        }
        UserIDComboBox.setItems(UserNames);
        UserIDComboBox.setEditable(true);

        // Initialize CustomerID ComboBox
        ObservableList<Customer> CustomerList = CustomersTable.GetCustomers();
        ObservableList<String> CustomerNames = FXCollections.observableArrayList();
        for (Customer Customer : CustomerList) {
            CustomerNames.add(Customer.getCustomerName());
        }
        CustIDComboBox.setItems(CustomerNames);
        CustIDComboBox.setEditable(true);
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

    @FXML
    void ResetButtonClicked(ActionEvent event) {
        AppointmentIDTextField.setText("");
        ContactComboBox.setValue("");
        CustIDComboBox.setValue("");
        DescriptionTextField.setText("");
        EndDatePicker.setValue(null);
        EndTimeComboBox.setValue(null);
        LocationTextField.setText("");
        StartDatePicker.setValue(null);
        StartTimeComboBox.setValue(null);
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

