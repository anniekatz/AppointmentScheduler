package Model;

import java.time.LocalDateTime;

public class Division {
    private int DivisionID;
    private String DivisionName;
    private LocalDateTime CreateDate;
    private String CreatedBy;
    private LocalDateTime LastUpdate;
    private String LastUpdateBy;
    private int CountryID;

    // Divison constructor
    public Division(int DivisionID, String DivisionName, LocalDateTime CreateDate, String CreatedBy, LocalDateTime LastUpdate, String LastUpdateBy, int CountryID) {
        this.DivisionID = DivisionID;
        this.DivisionName = DivisionName;
        this.CreateDate = CreateDate;
        this.CreatedBy = CreatedBy;
        this.LastUpdate = LastUpdate;
        this.LastUpdateBy = LastUpdateBy;
        this.CountryID = CountryID;
    }

    // Get methods
    public int getDivisionID() {
        return DivisionID;
    }
    public String getDivisionName() {
        return DivisionName;
    }
    public LocalDateTime getCreateDate() {
        return CreateDate;
    }
    public String getCreatedBy() {
        return CreatedBy;
    }
    public LocalDateTime getLastUpdate() {
        return LastUpdate;
    }
    public String getLastUpdateBy() {
        return LastUpdateBy;
    }
    public int getCountryID() {
        return CountryID;
    }
}

