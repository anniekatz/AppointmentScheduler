package Database.QueryTables;

import Database.QueryUtils;
import Model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

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
            String Name = RS.getString("Customer_Name");
            String Address = RS.getString("Address");
            String ZipCode = RS.getString("Postal_Code");
            String Phone = RS.getString("Customer_Phone");
            LocalDateTime CreateDate = RS.getTimestamp("Create_Date").toLocalDateTime();
            String CreatedBy = RS.getString("Created_By");
            LocalDateTime LastUpdate = RS.getTimestamp("Last_Update").toLocalDateTime();
            String LastUpdatedBy = RS.getString("Last_Updated_By");
            int DivisionID = RS.getInt("Division_ID");

            Customer NewCustomer = new Customer(CustomerID, Name, Address, Phone, ZipCode, CreateDate, CreatedBy, LastUpdate, LastUpdatedBy, DivisionID);
            CustomerList.add(NewCustomer);
        }} catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return CustomerList;
    }
}
