package Controller;

import Model.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

public class CustomerPage {
    // Navigation buttons
    @FXML
    private Button ReportsButton;
    @FXML
    private Button AppointmentsButton;


    // Customer TableView Variables
    @FXML
    private TableView<Customer> CustomerTable;
    @FXML
    private TableColumn<Customer, String> CustomerTableAddressColumn;
    @FXML
    private TableColumn<Customer, String> CustomerTableCountryColumn;
    @FXML
    private TableColumn<Customer, Integer> CustomerTableCustomerIDColumn;
    @FXML
    private TableColumn<Customer, Integer> CustomerTableDivisionColumn;
    @FXML
    private TableColumn<Customer, String> CustomerTableNameColumn;
    @FXML
    private TableColumn<Customer, String> CustomerTablePhoneColumn;
    @FXML
    private TableColumn<Customer, String> CustomerTablePostalCodeColumn;

    // Creating/Updating/Deleting Form Variables
    @FXML
    private Button AddUpdateButton;
    @FXML
    private TextField AddressTextField;
    @FXML
    private ComboBox<?> CountryComboBox;
    @FXML
    private TextField CustomerIDTextField;
    @FXML
    private Button DeleteButton;
    @FXML
    private ComboBox<?> DivisionComboBox;
    @FXML
    private TextField NameTextField;
    @FXML
    private TextField PhoneTextField;
    @FXML
    private TextField PostalCodeTextField;



    // Navigation button methods
    @FXML
    void NavToAppointments(ActionEvent event) throws IOException {
        ControllerUtils.NavToAppointments(event);
    }
    @FXML
    void NavToReports(ActionEvent event) throws IOException {
        ControllerUtils.NavToReports(event);
    }
}
