package Model;

import java.time.LocalDateTime;
import java.util.Date;

public class User {
    private int UserID;
    private String Username;
    private String Password;


    // User constructor
    public User(int UserID, String Username, String Password) {
        this.UserID = UserID;
        this.Username = Username;
        this.Password = Password;
    }

    // Get methods
    public int getUserID() {
        return UserID;
    }
    public String getUsername() {
        return Username;
    }
    public String getPassword() {
        return Password;
    }
}
