package Database.QueryTables;

import Database.QueryUtils;
import Model.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This class is used to query the contacts table in database.
 * */
public class ContactsTable {

    /**
     * This method is used to get all contacts from the database.
     * @return returns observable list containing Contacts and their information.
     */
    public static ObservableList<Contact> getContacts() {
        // Initialize empty Observable List
        ObservableList<Contact> contactList = FXCollections.observableArrayList();
        // Create query to get all contacts
        try {
            String query = "SELECT * FROM contacts;";
            QueryUtils.setPS(query);
            PreparedStatement PS = QueryUtils.getPS();
            PS.execute();
            ResultSet RS = PS.getResultSet();
            // Loop through records to get data for all contacts
            while (RS.next()) {
                int contactID = RS.getInt("Contact_ID");
                String name = RS.getString("Contact_Name");
                String email = RS.getString("Email");
                // Add each contact to Observable List
                Contact newContact = new Contact(contactID, name, email);
                contactList.add(newContact);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return contactList;
    }
}
