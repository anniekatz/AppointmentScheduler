package Model;

import java.time.LocalDateTime;
import java.util.Date;

public class User {
    private int UserID;
    private String Username;
    private String Password;
    private LocalDateTime CreateDate;
    private String CreatedBy;
    private LocalDateTime LastUpdate;
    private String LastUpdateBy;

    // User constructor
    public User(int UserID, String Username, String Password, LocalDateTime CreateDate, String CreatedBy, LocalDateTime LastUpdate, String LastUpdateBy) {
        this.UserID = UserID;
        this.Username = Username;
        this.Password = Password;
        this.CreateDate = CreateDate;
        this.CreatedBy = CreatedBy;
        this.LastUpdate = LastUpdate;
        this.LastUpdateBy = LastUpdateBy;
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
    public LocalDateTime getCreateDate() { return CreateDate; }
    public String getCreatedBy() {
        return CreatedBy;
    }
    public LocalDateTime getLastUpdate() {
        return LastUpdate;
    }
    public String getLastUpdateBy() {
        return LastUpdateBy;
    }
}
