package Model;

/**
 * This class represents the model for a user.
 */
public class User {

    private int userID;
    private String username;
    private String password;


    /**
     * This is the constructor for a user.
     * @param userID Int value of user ID
     * @param username String value of user's username
     * @param password String value of user's password
     */
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
