package Controller;

import Database.QueryTables.AppointmentsTable;
import Database.QueryTables.ContactsTable;
import Model.Contact;
import Model.ReportModels.Report1;
import Model.ReportModels.Report2;
import Model.ReportModels.Report3;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


import java.net.URL;
import java.util.ResourceBundle;

// Reports Controller class implements Initializable Interface
// This class is used by ReportPage view to generate reports for the user in different tabs
public class ReportPage implements Initializable {

    // Navigation button variables
    @FXML
    private Button AppointmentsButton;
    @FXML
    private Button CustomersButton;

    // Report 1 view variables
    @FXML
    private Tab AppointmentTotalsTab;
    @FXML
    private TableView<Report1> AppointmentTotalsTable;
    @FXML
    private TableColumn<?, ?> AppointmentTotalsTableMonthColumn;
    @FXML
    private TableColumn<?, ?> AppointmentTotalsTableTotalColumn;
    @FXML
    private TableColumn<?, ?> AppointmentTotalsTableTypeColumn;
    @FXML
    private ComboBox<String> ChooseContactComboBox;
    @FXML
    private ComboBox<String> ChooseMonthComboBox;

    // Report 2 view variables
    @FXML
    private TextField ContactIDTextField;
    @FXML
    private Tab ContactScheduleTab;
    @FXML
    private TableView<Report2> ContactScheduleTable;
    @FXML
    private TableColumn<?, ?> ContactScheduleTableAppointmentIDColumn;
    @FXML
    private TableColumn<?, ?> ContactScheduleTableCustomerIDColumn;
    @FXML
    private TableColumn<?, ?> ContactScheduleTableDescriptionColumn;
    @FXML
    private TableColumn<?, ?> ContactScheduleTableEndColumn;
    @FXML
    private TableColumn<?, ?> ContactScheduleTableStartColumn;
    @FXML
    private TableColumn<?, ?> ContactScheduleTableTitleColumn;
    @FXML
    private TableColumn<?, ?> ContactScheduleTableTypeColumn;

    // Report 3 view variables
    @FXML
    private Tab CustomerTotalsTab;
    @FXML
    private TableView<Report3> CustomerTotalsTable;
    @FXML
    private TableColumn<?, ?> CustomerTotalsTableCustomerIDColumn;
    @FXML
    private TableColumn<?, ?> CustomerTotalsTableTotalFutureColumn;
    @FXML
    private TableColumn<?, ?> CustomerTotalsTableTotalPastColumn;

    // Method to initialize page and all reports
    @Override
    public void initialize(URL url, ResourceBundle resources) {
        // Report 1 Initializables
        // Report 1 - Appointment Totals by Month and Type
        AppointmentTotalsTableTypeColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
        AppointmentTotalsTableTotalColumn.setCellValueFactory(new PropertyValueFactory<>("Total"));
        InitializeMonthComboBox();

        // Report 2 Initializables
        // Report 2 - Contact Appointment Schedule
        ContactScheduleTableAppointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("AppointmentID"));
        ContactScheduleTableCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        ContactScheduleTableDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
        ContactScheduleTableEndColumn.setCellValueFactory(new PropertyValueFactory<>("End"));
        ContactScheduleTableStartColumn.setCellValueFactory(new PropertyValueFactory<>("Start"));
        ContactScheduleTableTitleColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
        ContactScheduleTableTypeColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
        InitializeContactComboBox();

        // Report 3 Initializables
        // Report 3 - Customers' Total Future and Past Appointments
        CustomerTotalsTableCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        CustomerTotalsTableTotalFutureColumn.setCellValueFactory(new PropertyValueFactory<>("TotalFuture"));
        CustomerTotalsTableTotalPastColumn.setCellValueFactory(new PropertyValueFactory<>("TotalPast"));
        InitializeReport3();
    }

    // Report 1 Methods
    void InitializeMonthComboBox() {
        // Fill ChooseMonthComboBox with valid months
        ChooseMonthComboBox.getItems().addAll("All Months","January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
    }
    // Method to run when month is chosen in combo box
    @FXML
    void PopulateReport1Table(ActionEvent event){
        // Generate tableview items from database based on month chosen
        String Month = ChooseMonthComboBox.getValue();
        ObservableList<Report1> ReportList = AppointmentsTable.generateReport1(Month);
        AppointmentTotalsTable.setItems(ReportList);
    }

    // Report 2 Methods
    void InitializeContactComboBox(){
        // Initialize combo box with a list of contacts from contacts table
        // Add contact names to make it easier for user to read
        ObservableList<Contact> Contacts = ContactsTable.getContacts();
        ObservableList<String> ContactNames = FXCollections.observableArrayList();
        for (Contact Contact : Contacts) {
            ContactNames.add(Contact.getContactID() + "- " + Contact.getName());
        }
        ChooseContactComboBox.setItems(ContactNames);
    }
    // Method to run when contact is chosen in combo box
    @FXML
    void PopulateReport2Table(ActionEvent event){
        // Get ContactID from chosen contact in ChooseContactComboBox
        // Remove unusable name data so database can be queried
        int ContactID = Integer.parseInt(ChooseContactComboBox.getValue().split("-")[0]);
        // Generate tableview items from database based on contact chosen
        ObservableList<Report2> ReportList = AppointmentsTable.generateReport2(ContactID);
        ContactScheduleTable.setItems(ReportList);
    }

    // Report 3 Method
    void InitializeReport3(){
        // Generate report 3 from appointment database
        ObservableList<Report3> ReportList = AppointmentsTable.generateReport3();
        CustomerTotalsTable.setItems(ReportList);
    }

    // Navigation Button Methods
    @FXML
    void NavToAppointments(ActionEvent event) {
        ControllerUtils.NavToAppointments(event);
    }
    @FXML
    void NavToCustomers(ActionEvent event) {
        ControllerUtils.NavToCustomers(event);
    }
}

