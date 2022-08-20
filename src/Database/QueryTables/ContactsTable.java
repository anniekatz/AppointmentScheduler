package Database.QueryTables;

import Database.QueryUtils;
import Model.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class ContactsTable {
    public static ObservableList<Contact> GetContacts() throws SQLException {
        ObservableList<Contact> ContactList = FXCollections.observableArrayList();

        String Query = "SELECT * FROM contacts;";
        QueryUtils.SetPS(Query);
        PreparedStatement PS = QueryUtils.GetPS();
        PS.execute();
        ResultSet RS = PS.getResultSet();

        while (RS.next()) {
            int ContactID = RS.getInt("Contact_ID");
            String Name = RS.getString("Contact_Name");
            String Email = RS.getString("Email");

            Contact NewContact = new Contact(ContactID, Name, Email);
            ContactList.add(NewContact);
        }
        return ContactList;
    }
}
