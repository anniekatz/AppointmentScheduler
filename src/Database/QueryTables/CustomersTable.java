package Database.QueryTables;

import Database.QueryUtils;
import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomersTable {
    public static ObservableList<Customer> GetCustomers() {
        ObservableList<Customer> CustomerList = FXCollections.observableArrayList();

        try {
        String Query = "SELECT * FROM customers;";
        QueryUtils.SetPS(Query);
        PreparedStatement PS = QueryUtils.GetPS();
        PS.execute();
        ResultSet RS = PS.getResultSet();

        while (RS.next()) {
            int CustomerID = RS.getInt("Customer_ID");
            String CustomerName = RS.getString("Customer_Name");
            String CustomerAddress = RS.getString("Address");
            String CustomerZipCode = RS.getString("Postal_Code");
            String CustomerPhone = RS.getString("Phone");
            int DivisionID = RS.getInt("Division_ID");

            Customer NewCustomer = new Customer(CustomerID, CustomerName, CustomerAddress, CustomerPhone, CustomerZipCode, DivisionID);
            CustomerList.add(NewCustomer);
        }}
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return CustomerList;
    }

    // Delete customer from database
    public static void DeleteCustomer(int CustomerID) throws SQLException {
        String Query = "DELETE FROM customers WHERE Customer_ID =  '" + CustomerID + "';";
        QueryUtils.SetPS(Query);
        PreparedStatement PS = QueryUtils.GetPS();
        PS.execute();
    }

    // Update customer in database
    public static void UpdateCustomer(int CustomerID, String CustomerName, String CustomerAddress, String CustomerZipCode, String CustomerPhone, int DivisionID) throws SQLException {
        String Query = "UPDATE customers SET Customer_Name = '" + CustomerName + "', Address = '" + CustomerAddress + "', Postal_Code = '" + CustomerZipCode + "', Phone = '" + CustomerPhone + "', Division_ID = '" + DivisionID + "' WHERE Customer_ID = '" + CustomerID + "';";
        QueryUtils.SetPS(Query);
        PreparedStatement PS = QueryUtils.GetPS();
        PS.execute();
    }
}
