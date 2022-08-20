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
    public int GetDivisionID() {
        return DivisionID;
    }
    public String GetDivisionName() {
        return DivisionName;
    }

    public LocalDateTime GetCreateDate() {
        return CreateDate;
    }
    public String GetCreatedBy() {
        return CreatedBy;
    }
    public LocalDateTime GetLastUpdate() {
        return LastUpdate;
    }
    public String GetLastUpdateBy() {
        return LastUpdateBy;
    }
    public int GetCountryID() {
        return CountryID;
    }
}

