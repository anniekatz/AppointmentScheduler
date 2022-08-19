package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Establish connection to MySQL database
public abstract class ConnectDB {
    // URL parts
    private static final String DBProtocol = "jdbc:";
    private static final String DBVendor = "mysql:";
    private static final String DBServer = "//localhost:3306/";
    private static final String DBName = "client_schedule";
    private static final String DBTZServer = "?connectionTimeZone=SERVER";

    // Final URL for connecting to database
    private static final String JDBC_URL = DBProtocol + DBVendor + DBServer + DBName + DBTZServer;

    // Add database driver, username, and password
    private static final String DB_Driver = "com.mysql.cj.jdbc.Driver";
    private static final String DB_User = "sqlUser";
    private static final String DB_Pass = "Passw0rd!";

    // Connection variable
    public static Connection DBConnection;

    // Method to connect to database upon program open
    public static void Connect() {
        try {
            Class.forName(DB_Driver);
            DBConnection = DriverManager.getConnection(JDBC_URL, DB_User, DB_Pass);
            System.out.println("Database successfully connected.");
        } catch (SQLException e) {
            System.out.println("Error: Connection Failed. Check output console.");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Error: Database driver not found.");
            e.printStackTrace();
        }
    }

    // Method to disconnect from database once program is closed
    public static void Disconnect() {
        try {
            DBConnection.close();
            System.out.println("Database successfully disconnected.");
        } catch (SQLException e) {
            System.out.println("Error: Disconnect Failed. Check output console.");
            e.printStackTrace();
        }
    }

}
