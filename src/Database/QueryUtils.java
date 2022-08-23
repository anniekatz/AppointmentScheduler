package Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

// Class to execute SQL queries
public abstract class QueryUtils {
    // Create PreparedStatement object
    static PreparedStatement dbPS;

    // Set PreparedStatement object based on given SQL query
    public static void setPS(String query) {
        try {
            dbPS = ConnectDB.dbConnection.prepareStatement(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Get PreparedStatement object
    public static PreparedStatement getPS() {
        return dbPS;
    }
}
