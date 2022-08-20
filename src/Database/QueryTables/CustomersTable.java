package Database.QueryTables;

import Database.QueryUtils;
import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomersTable {
    public static ObservableList<Customer> GetCustomers() throws SQLException {
        ObservableList<Customer> CustomerList = FXCollections.observableArrayList();

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
        }
        return CustomerList;
    }
}
