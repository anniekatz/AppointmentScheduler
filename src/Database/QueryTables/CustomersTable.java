package Database.QueryTables;

import Database.QueryUtils;
import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Class to query customers table in database
public class CustomersTable {
    // Get full customers table
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

    // Method to delete customer from database
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

    // Method to update customer info in database
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
    // Method to add new customer to database
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
