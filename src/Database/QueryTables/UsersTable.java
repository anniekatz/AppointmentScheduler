package Database.QueryTables;

import Database.ConnectDB;
import Database.QueryUtils;
import Model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Database.QueryUtils;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UsersTable {

    public static ObservableList<User> GetUsers() throws SQLException {
        ObservableList<User> UserList = FXCollections.observableArrayList();

        String Query = "SELECT * FROM users;";

        QueryUtils.SetPS(Query);
        PreparedStatement PS = QueryUtils.GetPS();

        PS.execute();
        ResultSet RS = PS.getResultSet();
        while (RS.next()) {
            int UserID = RS.getInt("User_ID");
            String Username = RS.getString("User_Name");
            String Password = RS.getString("Password");
            LocalDateTime CreateDate = RS.getTimestamp("Create_Date").toLocalDateTime();
            String CreatedBy = RS.getString("Created_By");
            LocalDateTime LastUpdate = RS.getTimestamp("Last_Update").toLocalDateTime();
            String LastUpdatedBy = RS.getString("Last_Updated_By");

            User NewUser = new User(UserID, Username, Password, CreateDate, CreatedBy, LastUpdate, LastUpdatedBy);
            UserList.add(NewUser);
        }
        return UserList;
    }

    public static boolean CheckValidUsers(String Username, String Password) throws SQLException {
        List<String> ValidUsernames = new ArrayList<String>();
        List<String> ValidPasswords = new ArrayList<String>();
        String Query = "SELECT * FROM users WHERE User_Name is NOT NULL;";

        QueryUtils.SetPS(Query);
        PreparedStatement PS = QueryUtils.GetPS();

        PS.execute();
        ResultSet RS = PS.getResultSet();

        while (RS.next()) {
            String ValidUsername = RS.getString("User_Name");
            String ValidPassword = RS.getString("Password");
            ValidUsernames.add(ValidUsername);
            ValidPasswords.add(ValidPassword);
        }
        // Check if Username in ValidUsernames
        boolean check;
        if (ValidUsernames.contains(Username)) {
            int index = ValidUsernames.indexOf(Username);
            check = ValidPasswords.get(index).equals(Password);
        } else {
            check = false;
        }

        return check;

    }


}




