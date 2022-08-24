package Database.QueryTables;

import Database.QueryUtils;
import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class is used to query customers table in database
 */
public class CustomersTable {

    /**
     * This method is used to get all customers from the database.
     * @return ObservableList List containing customers and their information.
     */
    public static ObservableList<Customer> getCustomers() {
        // Initialize empty Observable List
        ObservableList<Customer> customerList = FXCollections.observableArrayList();
        try {
            // Query to get all customers
            String query = "SELECT * FROM customers;";
            QueryUtils.setPS(query);
            PreparedStatement PS = QueryUtils.getPS();
            PS.execute();
            ResultSet RS = PS.getResultSet();
            // Loop through records to get data for all customers
            while (RS.next()) {
                int customerID = RS.getInt("Customer_ID");
                String customerName = RS.getString("Customer_Name");
                String customerAddress = RS.getString("Address");
                String customerZipCode = RS.getString("Postal_Code");
                String customerPhone = RS.getString("Phone");
                int divisionID = RS.getInt("Division_ID");
                // Add each customer to Observable List
                Customer newCustomer = new Customer(customerID, customerName, customerAddress, customerPhone, customerZipCode, divisionID);
                customerList.add(newCustomer);
        }} catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return customerList;
    }

    /**
     * This method is used to delete a customer from the database.
     * @param customerID The ID of the customer to be deleted.
     */
    public static void deleteCustomer(int customerID) {
        try {
            // Query to delete customer based on ID
            String query = "DELETE FROM customers WHERE Customer_ID =  '" + customerID + "';";
            QueryUtils.setPS(query);
            PreparedStatement PS = QueryUtils.getPS();
            PS.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method is used to update an existing customer in the database.
     * @param customerID The ID of the customer to be updated.
     * @param customerName The new name of the customer.
     * @param customerAddress The new address of the customer.
     * @param customerPhone The new phone number of the customer.
     * @param customerZipCode The new zip code of the customer.
     * @param divisionID The new division ID of the customer.
     */
    public static void updateCustomer(int customerID, String customerName, String customerAddress, String customerZipCode, String customerPhone, int divisionID) {
        try {
        // Update customer in customers table where Customer_ID = customerID
        String query = "UPDATE customers SET Customer_Name = '" + customerName + "', Address = '" + customerAddress + "', Postal_Code = '" + customerZipCode + "', Phone = '" + customerPhone + "', Division_ID = '" + divisionID + "' WHERE Customer_ID = '" + customerID + "';";
        QueryUtils.setPS(query);
        PreparedStatement PS = QueryUtils.getPS();
        PS.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

/**
     * This method is used to add a new customer to the database.
     * @param customerName The name of the customer.
     * @param customerAddress The address of the customer.
     * @param customerZipCode The zip code of the customer.
     * @param customerPhone The phone number of the customer.
     * @param divisionID The division ID of the customer.
     */
    public static void addCustomer(String customerName, String customerAddress, String customerZipCode, String customerPhone, int divisionID) {
        try {
            // Query to add new customer to customers table and generate new Customer ID
            String query = "INSERT INTO customers (Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES ('" + customerName + "', '" + customerAddress + "', '" + customerZipCode + "', '" + customerPhone + "', '" + divisionID + "');";
            QueryUtils.setPS(query);
            PreparedStatement PS = QueryUtils.getPS();
            PS.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
