package DatabaseUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class QueryBase {
    // Create PreparedStatement object
    PreparedStatement DB_PS;

    // Set PreparedStatement object based on SQL query
    public void SetPS(String query) throws SQLException {
        DB_PS = ConnectDB.DBConnection.prepareStatement(query);
    }

    // Get PreparedStatement object
    public PreparedStatement GetPS() {
        return DB_PS;
    }
}
