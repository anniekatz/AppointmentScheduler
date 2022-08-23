package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Class to establish connection to MySQL database
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
    private static final String dbUser = "sqlUser";
    private static final String dbPass = "Passw0rd!";

    // Create Connection variable
    public static Connection dbConnection;

    // Method to connect to database upon program open
    // Used in main method
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

    // Method to disconnect from database once GUI is closed
    // Used in main method
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
