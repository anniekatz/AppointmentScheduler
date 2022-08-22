package Controller;

import Database.QueryTables.AppointmentsTable;
import Database.QueryTables.ContactsTable;
import Database.QueryTables.CustomersTable;
import Model.Appointment;
import Model.Contact;
import Model.Customer;
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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReportPage implements Initializable {

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
    private Button AppointmentsButton;
    @FXML
    private ComboBox<String> ChooseContactComboBox;
    @FXML
    private ComboBox<String> ChooseMonthComboBox;
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
    @FXML
    private Button CustomersButton;
    @FXML
    void NavToAppointments(ActionEvent event) throws IOException {
        ControllerUtils.NavToAppointments(event);
    }
    @FXML
    void NavToCustomers(ActionEvent event) throws IOException {
        ControllerUtils.NavToCustomers(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resources) {
        // Report 1 Initializables
        AppointmentTotalsTableTypeColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
        AppointmentTotalsTableTotalColumn.setCellValueFactory(new PropertyValueFactory<>("Total"));
        InitializeMonthComboBox();

        // Report 2 Initializables
        ContactScheduleTableAppointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("AppointmentID"));
        ContactScheduleTableCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        ContactScheduleTableDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("Description"));
        ContactScheduleTableEndColumn.setCellValueFactory(new PropertyValueFactory<>("End"));
        ContactScheduleTableStartColumn.setCellValueFactory(new PropertyValueFactory<>("Start"));
        ContactScheduleTableTitleColumn.setCellValueFactory(new PropertyValueFactory<>("Title"));
        ContactScheduleTableTypeColumn.setCellValueFactory(new PropertyValueFactory<>("Type"));
        InitializeContactComboBox();

        // Report 3 Initializables
        CustomerTotalsTableCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<>("CustomerID"));
        CustomerTotalsTableTotalFutureColumn.setCellValueFactory(new PropertyValueFactory<>("TotalFuture"));
        CustomerTotalsTableTotalPastColumn.setCellValueFactory(new PropertyValueFactory<>("TotalPast"));
        InitializeReport3();
    }

    // Report 1 Methods
    void InitializeMonthComboBox() {
        // Fill ChooseMonthComboBox with months
        ChooseMonthComboBox.getItems().addAll("All Months","January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
    }
    // Method to run when month is chosen
    @FXML
    void PopulateReport1Table(ActionEvent event){
        String Month = ChooseMonthComboBox.getValue();
        ObservableList<Report1> ReportList = AppointmentsTable.GetReport1(Month);
        AppointmentTotalsTable.setItems(ReportList);
    }


    // Report 2 Methods
    void InitializeContactComboBox(){
        ObservableList<Contact> Contacts = ContactsTable.GetContacts();
        ObservableList<String> ContactNames = FXCollections.observableArrayList();
        for (Contact Contact : Contacts) {
            ContactNames.add(Contact.getContactID() + "- " + Contact.getName());
        }
        ChooseContactComboBox.setItems(ContactNames);
    }
    // Method to run when contact is chosen
    @FXML
    void PopulateReport2Table(ActionEvent event){
        // Get ContactID from chosen contact in ChooseContactComboBox
        int ContactID = Integer.parseInt(ChooseContactComboBox.getValue().split("-")[0]);
        ObservableList<Report2> ReportList = AppointmentsTable.GetReport2(ContactID);
        ContactScheduleTable.setItems(ReportList);
    }

    // Report 3 Methods
    void InitializeReport3(){
        ObservableList<Report3> ReportList = AppointmentsTable.GetReport3();
        CustomerTotalsTable.setItems(ReportList);
    }
}

