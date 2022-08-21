package Controller;

import Database.QueryTables.AppointmentsTable;
import Database.QueryTables.CountriesTable;
import Database.QueryTables.CustomersTable;
import Database.QueryTables.DivisionsTable;
import Model.Appointment;
import Model.Country;
import Model.Customer;
import Model.Division;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class CustomerPage implements Initializable {
    // Navigation buttons
    @FXML
    private Button ReportsButton;
    @FXML
    private Button AppointmentsButton;


    // Customer TableView Variables
    @FXML
    public TableView<Customer> CustomerTable;
    @FXML
    public TableColumn<Customer, String> CustomerTableAddressColumn;
    @FXML
    public TableColumn<Customer, Integer> CustomerTableCustomerIDColumn;
    @FXML
    public TableColumn<Customer, Integer> CustomerTableDivisionColumn;
    @FXML
    public TableColumn<Customer, String> CustomerTableNameColumn;
    @FXML
    public TableColumn<Customer, String> CustomerTablePhoneColumn;
    @FXML
    public TableColumn<Customer, String> CustomerTablePostalCodeColumn;

    // Creating/Updating/Deleting Form Variables
    @FXML
    private Button AddUpdateButton;
    @FXML
    private TextField AddressTextField;
    @FXML
    private ComboBox<String> CountryComboBox;
    @FXML
    private TextField CustomerIDTextField;
    @FXML
    private Button DeleteButton;
    @FXML
    private ComboBox<String> DivisionComboBox;
    @FXML
    private TextField NameTextField;
    @FXML
    private TextField PhoneTextField;
    @FXML
    private TextField PostalCodeTextField;



    // Initialize method
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Initialize Customer TableView
        CustomerTableCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<Customer,Integer>("CustomerID"));
        CustomerTableNameColumn.setCellValueFactory(new PropertyValueFactory<Customer,String>("CustomerName"));
        CustomerTableAddressColumn.setCellValueFactory(new PropertyValueFactory<Customer,String>("CustomerAddress"));
        CustomerTablePhoneColumn.setCellValueFactory(new PropertyValueFactory<Customer,String>("CustomerPhone"));
        CustomerTablePostalCodeColumn.setCellValueFactory(new PropertyValueFactory<Customer,String>("CustomerZipCode"));
        CustomerTableDivisionColumn.setCellValueFactory(new PropertyValueFactory<Customer,Integer>("DivisionID"));

        ObservableList<Customer> FullCustomersList = CustomersTable.GetCustomers();
        CustomerTable.setItems(FullCustomersList);

        // Initialize Country ComboBox
        ObservableList<Country> CountriesList = CountriesTable.GetCountries();
        ObservableList<String> CountryNamesList = FXCollections.observableArrayList();
        for (Country Country : CountriesList) {
            CountryNamesList.add(Country.getCountry());
        }
        CountryComboBox.setItems(CountryNamesList);
        CountryComboBox.setEditable(true);

        // Initialize Division ComboBox
        ObservableList<Division> DivisionsList = DivisionsTable.GetDivisions();
        ObservableList<String> DivisionNamesList = FXCollections.observableArrayList();
        for (Division Division : DivisionsList) {
            DivisionNamesList.add(Division.getDivisionName());
        }
        DivisionComboBox.setItems(DivisionNamesList);
        DivisionComboBox.setEditable(true);


    }

    // Delete customer
    @FXML
    void DeleteCustomer(ActionEvent event) throws SQLException {
        Customer SelectedCustomer = CustomerTable.getSelectionModel().getSelectedItem();
        if (SelectedCustomer != null) {
            // Check if customer has any appointments
            int NumCustAppts = GetCustomerAppointment(SelectedCustomer.getCustomerID());
            if (NumCustAppts > 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("Customer has appointments scheduled; cannot delete");
                alert.showAndWait();
            } else {
                // ask user to confirm delete
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Confirmation");
                alert.setContentText("Are you sure you want to delete this customer?");
                alert.showAndWait();
                if (alert.getResult() == ButtonType.OK) {
                    // Delete customer from database
                    CustomersTable.DeleteCustomer(SelectedCustomer.getCustomerID());
                    // Delete customer from tableview
                    CustomerTable.getItems().remove(SelectedCustomer);
                }

            }
        }
    }

    @FXML
    void PopulateCountryComboBox(ActionEvent event) {
        // Filter CountryComboBox based on DivisionComboBox chosen

    }

    @FXML
    void PopulateDivisionComboBox(ActionEvent event) {
        // Filter DivisionComboBox based on CountryComboBox chosen

    }

    // Navigation button methods
    @FXML
    void NavToAppointments(ActionEvent event) throws IOException {
        ControllerUtils.NavToAppointments(event);
    }
    @FXML
    void NavToReports(ActionEvent event) throws IOException {
        ControllerUtils.NavToReports(event);
    }

    int GetCustomerAppointment(int CustomerID) throws SQLException {
        ObservableList<Appointment> AppointmentsList = AppointmentsTable.GetAppointments();
        int NumAppts = 0;
        for (Appointment Appointment : AppointmentsList) {
            if (Appointment.getCustomerID() == CustomerID) {
                NumAppts++;
            }
        }
        return NumAppts;
    }




}