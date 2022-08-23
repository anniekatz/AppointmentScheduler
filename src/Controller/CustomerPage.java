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

import java.net.URL;
import java.util.ResourceBundle;

/**
 * CustomerPage Controller class is used to view, add, edit, and delete customers in a CustomerPage view
 * Implements Initializable interface
 */
public class CustomerPage implements Initializable {

    @FXML
    private Button ReportsButton;
    @FXML
    private Button AppointmentsButton;
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
    @FXML
    private Button AddUpdateButton;
    @FXML
    public Button ResetButton;
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

    /**
     * Method initializes the CustomerPage controller class.
     * It initializes customer tableview, combo box data, and listener for row selection/deselection.
     * Lambda expression is a JavaFX Listener method for row selection/deselection
     *      Lambda is ideal as this custom JavaFX method is only used in one function and is only used in this page
     *      Lambda adds table listener to PopulateForm when row is selected and depopulates form upon deselection (using reset button)
     * @param url The url location used to resolve relative paths for the root; is considered null if the location is not known.
     * @param resourceBundle The resources used to localize the root; is considered null if the root was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Initialize customer tableview
        CustomerTableCustomerIDColumn.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("CustomerID"));
        CustomerTableNameColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("CustomerName"));
        CustomerTableAddressColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("CustomerAddress"));
        CustomerTablePhoneColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("CustomerPhone"));
        CustomerTablePostalCodeColumn.setCellValueFactory(new PropertyValueFactory<Customer, String>("CustomerZipCode"));
        CustomerTableDivisionColumn.setCellValueFactory(new PropertyValueFactory<Customer, Integer>("DivisionID"));
        ObservableList<Customer> FullCustomersList = CustomersTable.getCustomers();
        CustomerTable.setItems(FullCustomersList);

        // Initialize valid combo box data
        InitializeComboBox();

        // Lambda expression for listener for row selection/deselection and form population/depopulation
        CustomerTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                // Populate form with selected row data
                Customer SelectedCustomer = CustomerTable.getSelectionModel().getSelectedItem();
                PopulateForm(SelectedCustomer);
            }
        });
    }

    /**
     * Method populates form based on selected customer row.
     * @param SelectedCustomer Customer object from row selection
     */
    void PopulateForm(Customer SelectedCustomer) {
        CustomerIDTextField.setText(Integer.toString(SelectedCustomer.getCustomerID()));
        NameTextField.setText(SelectedCustomer.getCustomerName());
        AddressTextField.setText(SelectedCustomer.getCustomerAddress());
        PhoneTextField.setText(SelectedCustomer.getCustomerPhone());
        PostalCodeTextField.setText(SelectedCustomer.getCustomerZipCode());
        DivisionComboBox.setValue(GetCustomerDivision(SelectedCustomer.getDivisionID()));
        CountryComboBox.setValue(GetCustomerCountry(SelectedCustomer.getDivisionID()));
    }

    /**
     * Method initializes valid data for the country combo box
     * Adds all countries from database for selection
     */
    void InitializeComboBox() {
        ObservableList<Country> CountriesList = CountriesTable.getCountries();
        ObservableList<String> CountryNamesList = FXCollections.observableArrayList();
        for (Country Country : CountriesList) {
            CountryNamesList.add(Country.getCountry());
        }
        CountryComboBox.setItems(CountryNamesList);
        CountryComboBox.setEditable(true);
    }

    /** Method runs after country is chosen; populates division combo box with valid divisions for that country
     * @param event ActionEvent that is triggered when country is chosen from combo box
     */
    @FXML
    void ChooseCountryComboBox(ActionEvent event) {
        // Create division lists for different countries
        ObservableList<Division> DivisionsList = DivisionsTable.getDivisions();
        ObservableList<String> USDivisionNamesList = FXCollections.observableArrayList();
        ObservableList<String> CADivisionNamesList = FXCollections.observableArrayList();
        ObservableList<String> UKDivisionNamesList = FXCollections.observableArrayList();

        // Assign each division to a country
        for (Division Division : DivisionsList) {
            if (Division.getCountryID() == 1) {
                USDivisionNamesList.add(Division.getDivisionName());
            } else if (Division.getCountryID() == 2) {
                UKDivisionNamesList.add(Division.getDivisionName());
            } else if (Division.getCountryID() == 3) {
                CADivisionNamesList.add(Division.getDivisionName());
            }
        }
        // Populate division combo box with correct division list based on country chosen
        String CountrySelection = CountryComboBox.getSelectionModel().getSelectedItem();
        switch (CountrySelection) {
            case "U.S" -> DivisionComboBox.setItems(USDivisionNamesList);
            case "Canada" -> DivisionComboBox.setItems(CADivisionNamesList);
            case "UK" -> DivisionComboBox.setItems(UKDivisionNamesList);
        }
        DivisionComboBox.setEditable(true);
    }

    /** Method gets division name from customer's division ID to ensure valid, readable data in combo box
     * @param DivisionID
     * @return String division name to fill combo box
     */
    String GetCustomerDivision(int DivisionID) {
        ObservableList<Division> DivisionsList = DivisionsTable.getDivisions();
        String Name = "";
        // For each division in division table, get name if ID matches
        for (Division Division : DivisionsList) {
            if (Division.getDivisionID() == DivisionID) {
                Name = Division.getDivisionName();
            }
        }
        return Name;
    }

    /** Get country name from customer's division ID to ensure valid data in division combo box
     * @param DivisionID Division ID to get country name from
     * @return String country name to fill country combo box upon customer selection
     * */
    String GetCustomerCountry(int DivisionID) {
        ObservableList<Division> DivisionsList = DivisionsTable.getDivisions();
        ObservableList<Country> CountriesList = CountriesTable.getCountries();
        String Name = "";
        int CountryID = 0;
        // For each division in division table, get matching country ID
        for (Division Division : DivisionsList) {
            if (Division.getDivisionID() == DivisionID) {
                CountryID = Division.getCountryID();
            }
        }
        // For each country in country table, get name if ID matches
        for (Country Country : CountriesList) {
            if (Country.getCountryID() == CountryID) {
                Name = Country.getCountry();
            }
        }
        return Name;
    }

    // Add/Update Customer Button Event Handler
    // Add/Updates customers in database and on page
    @FXML
    void AddUpdateCustomer(ActionEvent event) {
        // if any part of form (besides database-autogenerated ID) is null, show alert and do not proceed
        if (NameTextField.getText().isEmpty() || AddressTextField.getText().isEmpty() || PhoneTextField.getText().isEmpty() || PostalCodeTextField.getText().isEmpty() || CountryComboBox.getSelectionModel().isEmpty() || DivisionComboBox.getSelectionModel().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Add/Update Error");
            alert.setContentText("Please fill out all fields");
            alert.showAndWait();
        }
        // If all fields contain valid data, proceed with add/update
        else {
            // Get Division ID from Division ComboBox
            int DivisionID = GetDivisionID(DivisionComboBox.getValue());

            // if CustomerID is empty, add new customer; else, update existing customer in database
            if (CustomerIDTextField.getText().isEmpty()) {
                CustomersTable.addCustomer(NameTextField.getText(), AddressTextField.getText(), PostalCodeTextField.getText(), PhoneTextField.getText(), DivisionID);
            } else {
                CustomersTable.updateCustomer(Integer.parseInt(CustomerIDTextField.getText()), NameTextField.getText(), AddressTextField.getText(), PostalCodeTextField.getText(), PhoneTextField.getText(), DivisionID);
            }

            // Update tableview with new data
            ObservableList<Customer> FullCustomersList = CustomersTable.getCustomers();
            CustomerTable.setItems(FullCustomersList);
        }}

    // Get division ID from division name chosen (for adding/updating a customer)
    int GetDivisionID(String DivisionName) {
        int DivisionID = 0;
        for (Division Division : DivisionsTable.getDivisions()) {
            if (Division.getDivisionName().equals(DivisionName)) {
                DivisionID = Division.getDivisionID();
            }
        }
        return DivisionID;
    }

    // Delete customer button event handler
    // Deletes customer from database and on page
    @FXML
    void DeleteCustomer(ActionEvent event) {
        Customer SelectedCustomer = CustomerTable.getSelectionModel().getSelectedItem();
        // Make sure a customer is selected in order to delete
        if (SelectedCustomer != null) {
            // Check if customer has any existing appointments in database
            // If so, show alert and do not delete customer
            int NumCustAppts = GetCustomerAppointment(SelectedCustomer.getCustomerID());
            if (NumCustAppts > 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Error");
                alert.setContentText("Customer has appointments scheduled in database; cannot delete");
                alert.showAndWait();
            } else {
                // Ask user to confirm delete
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Confirmation");
                alert.setContentText("Are you sure you want to delete this customer?");
                alert.showAndWait();
                // Only delete upon confirmation
                if (alert.getResult() == ButtonType.OK) {
                    // Delete customer from database
                    CustomersTable.deleteCustomer(SelectedCustomer.getCustomerID());
                    // Delete customer from tableview
                    CustomerTable.getItems().remove(SelectedCustomer);
                }
            }
        }
    }

    // Checks if customer has any existing appointments before deleting customer
    int GetCustomerAppointment(int CustomerID) {
        ObservableList<Appointment> AppointmentsList = AppointmentsTable.getAppointments();
        // For each customer ID, check if any appointments exist in database
        int NumAppts = 0;
        for (Appointment Appointment : AppointmentsList) {
            if (Appointment.getCustomerID() == CustomerID) {
                NumAppts++;
            }
        }
        return NumAppts;
    }

    // Event handler if reset/deselect button is clicked
    @FXML
    void ResetButtonClicked(ActionEvent event) throws RuntimeException{
        // If a customer is selected, deselect it
        if (CustomerTable.getSelectionModel().getSelectedItem() != null) {
            CustomerTable.getSelectionModel().clearSelection();
        }
        // Nullify fields
        CustomerIDTextField.setText("");
        NameTextField.setText("");
        AddressTextField.setText("");
        PhoneTextField.setText("");
        PostalCodeTextField.setText("");
        DivisionComboBox.setValue("");
        CountryComboBox.setValue("");
    }

    // Navigation button methods
    // Navigate to another page
    @FXML
    void NavToAppointments(ActionEvent event) {
        ControllerUtils.NavToAppointments(event);
    }
    @FXML
    void NavToReports(ActionEvent event) {
        ControllerUtils.NavToReports(event);
    }

}

