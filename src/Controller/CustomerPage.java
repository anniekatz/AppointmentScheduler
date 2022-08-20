package Controller;

import Database.QueryTables.CustomersTable;
import Model.Customer;
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

    static ObservableList<Customer> FullCustomerList;

    // Initialize method
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize Customer TableView

        CustomerTableCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<Customer,Integer>("CustomerID"));
        CustomerTableNameColumn.setCellValueFactory(new PropertyValueFactory<Customer,String>("CustomerName"));
        CustomerTableAddressColumn.setCellValueFactory(new PropertyValueFactory<Customer,String>("CustomerAddress"));
        CustomerTablePhoneColumn.setCellValueFactory(new PropertyValueFactory<Customer,String>("CustomerPhone"));
        CustomerTablePostalCodeColumn.setCellValueFactory(new PropertyValueFactory<Customer,String>("CustomerPostalCode"));
        CustomerTableDivisionColumn.setCellValueFactory(new PropertyValueFactory<Customer,Integer>("DivisionID"));

        try {
            FullCustomerList = CustomersTable.GetCustomers();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        CustomerTable.setItems(FullCustomerList);

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


}
