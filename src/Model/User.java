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

    /**
     * This method gets user ID
     * @return int user ID
     */
    public int getUserID() {
        return userID;
    }

    /**
     * This method gets user's username
     * @return String value of user's username
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method gets user's password
     * @return String value of user's password
     */
    public String getPassword() {
        return password;
    }
}
