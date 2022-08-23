package Model;

// Model to represent a user
public class User {
    // User variables
    private int userID;
    private String username;
    private String password;


    // User constructor
    public User(int userID, String username, String password) {
        this.userID = userID;
        this.username = username;
        this.password = password;
    }

    // Get user information methods
    public int getUserID() {
        return userID;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}
