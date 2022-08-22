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
import java.util.logging.Filter;

public class CustomerPage implements Initializable {
    public Button ResetButton;
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
        CustomerTableCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("CustomerID"));
        CustomerTableNameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("CustomerName"));
        CustomerTableAddressColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("CustomerAddress"));
        CustomerTablePhoneColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("CustomerPhone"));
        CustomerTablePostalCodeColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("CustomerZipCode"));
        CustomerTableDivisionColumn.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("DivisionID"));

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

        // Add table listener to PopulateForm when row is selected
        CustomerTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                Customer SelectedCustomer = CustomerTable.getSelectionModel().getSelectedItem();
                PopulateForm(SelectedCustomer);
            }
        });

    }

    @FXML
    void AddUpdateCustomer(ActionEvent event) throws SQLException {
        // if any part of form is null, try again
        if (NameTextField.getText().isEmpty() || AddressTextField.getText().isEmpty() || PhoneTextField.getText().isEmpty() || PostalCodeTextField.getText().isEmpty() || CountryComboBox.getSelectionModel().isEmpty() || DivisionComboBox.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Add/Update Error");
            alert.setContentText("Please fill out all fields");
            alert.showAndWait();
        }
        else {
            int DivisionID = GetDivisionID(DivisionComboBox.getValue());
            // if CustomerID is empty, add new customer
            if (CustomerIDTextField.getText().isEmpty()) {

                CustomersTable.AddCustomer(NameTextField.getText(), AddressTextField.getText(), PostalCodeTextField.getText(), PhoneTextField.getText(), DivisionID);

            } else {
                CustomersTable.UpdateCustomer(Integer.parseInt(CustomerIDTextField.getText()), NameTextField.getText(), AddressTextField.getText(), PostalCodeTextField.getText(), PhoneTextField.getText(), DivisionID);

            }
            ObservableList<Customer> FullCustomersList = CustomersTable.GetCustomers();
            CustomerTable.setItems(FullCustomersList);
    }}

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
    void ChooseCountryComboBox(ActionEvent event) {
        FilterDivisionComboBox();
    }

    void FilterDivisionComboBox() {
        ObservableList<Division> DivisionsList = DivisionsTable.GetDivisions();
        ObservableList<String> DivisionNamesList = FXCollections.observableArrayList();
        ObservableList<String> USDivisionNamesList = FXCollections.observableArrayList();
        ObservableList<String> CADivisionNamesList = FXCollections.observableArrayList();
        ObservableList<String> UKDivisionNamesList = FXCollections.observableArrayList();
        for (Division Division : DivisionsList) {
            if (Division.getCountryID() == 1) {
                USDivisionNamesList.add(Division.getDivisionName());
            } else if (Division.getCountryID() == 2) {
                UKDivisionNamesList.add(Division.getDivisionName());
            } else if (Division.getCountryID() == 3) {
                CADivisionNamesList.add(Division.getDivisionName());
            }
            // Create full list of division names if country isn't chosen
            DivisionNamesList.add(Division.getDivisionName());
        }


        // If CountryComboBox not null, include country selection to filter divisions
        String CountrySelection = CountryComboBox.getSelectionModel().getSelectedItem();

        if (CountrySelection.equals("U.S")) {
            DivisionComboBox.setItems(USDivisionNamesList);
        } else if (CountrySelection.equals("Canada")) {
            DivisionComboBox.setItems(CADivisionNamesList);
        } else if (CountrySelection.equals("UK")) {
            DivisionComboBox.setItems(UKDivisionNamesList);
        } else {
            DivisionComboBox.setItems(DivisionNamesList);
        }
        DivisionComboBox.setEditable(true);
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

    // Populate form based on selected customer
    void PopulateForm(Customer SelectedCustomer) {
        CustomerIDTextField.setText(Integer.toString(SelectedCustomer.getCustomerID()));
        NameTextField.setText(SelectedCustomer.getCustomerName());
        AddressTextField.setText(SelectedCustomer.getCustomerAddress());
        PhoneTextField.setText(SelectedCustomer.getCustomerPhone());
        PostalCodeTextField.setText(SelectedCustomer.getCustomerZipCode());
        DivisionComboBox.setValue(GetCustomerDivision(SelectedCustomer.getDivisionID()));
        CountryComboBox.setValue(GetCustomerCountry(SelectedCustomer.getDivisionID()));

    }

    String GetCustomerDivision(int DivisionID) {
        ObservableList<Division> DivisionsList = DivisionsTable.GetDivisions();
        String Name = "";
        for (Division Division : DivisionsList) {
            if (Division.getDivisionID() == DivisionID) {
                Name = Division.getDivisionName();
            }
        }
        return Name;
    }

    String GetCustomerCountry(int DivisionID) {
        ObservableList<Division> DivisionsList = DivisionsTable.GetDivisions();
        ObservableList<Country> CountriesList = CountriesTable.GetCountries();
        String Name = "";
        int CountryID = 0;
        for (Division Division : DivisionsList) {
            if (Division.getDivisionID() == DivisionID) {
                CountryID = Division.getCountryID();
            }
        }
        for (Country Country : CountriesList) {
            if (Country.getCountryID() == CountryID) {
                Name = Country.getCountry();
            }
        }
        return Name;
    }

    int GetDivisionID(String DivisionName) {
        int DivisionID = 0;

        for (Division Division : DivisionsTable.GetDivisions()) {
            if (Division.getDivisionName().equals(DivisionName)) {
                DivisionID = Division.getDivisionID();
            }
        }
        return DivisionID;
    }

    @FXML
    void ResetButtonClicked(ActionEvent event) {
        if (CustomerTable.getSelectionModel().getSelectedItem() != null) {
            CustomerTable.getSelectionModel().clearSelection();
        }
        CustomerIDTextField.setText("");
        NameTextField.setText("");
        AddressTextField.setText("");
        PhoneTextField.setText("");
        PostalCodeTextField.setText("");
        DivisionComboBox.setValue(null);
        CountryComboBox.setValue(null);

    }
}

