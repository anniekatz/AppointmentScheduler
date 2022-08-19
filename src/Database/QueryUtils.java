package Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class QueryUtils {
    // Create PreparedStatement object
    static PreparedStatement DB_PS;

    // Set PreparedStatement object based on SQL query
    public static void SetPS(String query) throws SQLException {
        DB_PS = ConnectDB.DBConnection.prepareStatement(query);
    }

    // Get PreparedStatement object
    public static PreparedStatement GetPS() {
        return DB_PS;
    }
}
