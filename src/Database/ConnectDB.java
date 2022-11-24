package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * This class is used to create and close the connection to a MySQL Database.
 */
public abstract class ConnectDB {

    // Local database connectors
    private static final String dbProtocol = "jdbc:";
    private static final String dbVendor = "mysql:";
    private static final String dbServer = "//localhost:3306/";
    private static final String dbName = "client_schedule";
    private static final String dbTZServer = "?connectionTimeZone=SERVER";

    // Final URL for connecting to local database
    private static final String jdbcURL = dbProtocol + dbVendor + dbServer + dbName + dbTZServer;


    // Add database driver, username, and password
    private static final String dbDriver = "com.mysql.cj.jdbc.Driver";
    private static final String dbUser = "<USERNAME>";
    private static final String dbPass = "<PASSWORD>";

    // Create Connection variable
    public static Connection dbConnection;

    /**
     * This method starts the connection with the MySQL Database.
     * Used by main method upon program open
     */
    public static void dbConnect() {
        try {
            Class.forName(dbDriver);
            dbConnection = DriverManager.getConnection(jdbcURL, dbUser, dbPass);
            System.out.println("Database successfully connected.");
        } catch (SQLException e) {
            System.out.println("Error: Database connection failed.");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Error: Database driver not found.");
            e.printStackTrace();
        }
    }

    /**
     * This method closes the connection with the MySQL Database.
     * Used by main method upon program close.
     */
    public static void dbDisconnect() {
        try {
            dbConnection.close();
            System.out.println("Database successfully disconnected.");
        } catch (SQLException e) {
            System.out.println("Error: Database disconnect Failed.");
            e.printStackTrace();
        }
    }
}
