package Database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * This class is used as a utility to execute SQL queries in the database.
 */
public abstract class QueryUtils {
    static PreparedStatement dbPS;

    /**
     * Method sets PreparedStatement object based on given SQL query.
     * @param query SQL query to be executed.
     */
    public static void setPS(String query) {
        try {
            dbPS = ConnectDB.dbConnection.prepareStatement(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method returns PreparedStatement object.
     * @return PreparedStatement
     */
    public static PreparedStatement getPS() {
        return dbPS;
    }
}
