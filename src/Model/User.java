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
    public int GetUserID() {
        return UserID;
    }
    public String GetUsername() {
        return Username;
    }
    public String GetPassword() {
        return Password;
    }
    public LocalDateTime GetCreateDate() { return CreateDate; }
    public String GetCreatedBy() {
        return CreatedBy;
    }
    public LocalDateTime GetLastUpdate() {
        return LastUpdate;
    }
    public String GetLastUpdateBy() {
        return LastUpdateBy;
    }
}
