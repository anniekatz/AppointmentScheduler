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

    public static ObservableList<User> GetUsers() {
        ObservableList<User> UserList = FXCollections.observableArrayList();
try {
        String Query = "SELECT * FROM users;";
        QueryUtils.SetPS(Query);
        PreparedStatement PS = QueryUtils.GetPS();
        PS.execute();
        ResultSet RS = PS.getResultSet();

        while (RS.next()) {
            int UserID = RS.getInt("User_ID");
            String Username = RS.getString("User_Name");
            String Password = RS.getString("Password");

            User NewUser = new User(UserID, Username, Password);
            UserList.add(NewUser);
        }} catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return UserList;
    }

}




