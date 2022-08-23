package Database.QueryTables;

import Database.QueryUtils;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// Class to query users table in database
public class UsersTable {
    // Get full users table
    public static ObservableList<User> getUsers() {
        ObservableList<User> userList = FXCollections.observableArrayList();
        try {
            // Create query to get all users
            String query = "SELECT * FROM users;";
            QueryUtils.setPS(query);
            PreparedStatement PS = QueryUtils.getPS();
            PS.execute();
            ResultSet RS = PS.getResultSet();
            // Loop through records to get data for all users
            while (RS.next()) {
                int userID = RS.getInt("User_ID");
                String username = RS.getString("User_Name");
                String password = RS.getString("Password");
                // Add each user to Observable List
                User newUser = new User(userID, username, password);
                userList.add(newUser);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return userList;
    }

}




