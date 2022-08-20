package Model;

import java.time.LocalDateTime;

public class Country {
    private int CountryID;
    private String Country;
    private LocalDateTime CreateDate;
    private String CreatedBy;
    private LocalDateTime LastUpdate;
    private String LastUpdateBy;

    // Country constructor
    public Country(int CountryID, String Country, LocalDateTime CreateDate, String CreatedBy, LocalDateTime LastUpdate, String LastUpdateBy) {
        this.CountryID = CountryID;
        this.Country = Country;
        this.CreateDate = CreateDate;
        this.CreatedBy = CreatedBy;
        this.LastUpdate = LastUpdate;
        this.LastUpdateBy = LastUpdateBy;
    }

    // Get methods
    public int getCountryID() {
        return CountryID;
    }
    public String GetCountry() {
        return Country;
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
    public String getLastUpdateBy() { return LastUpdateBy; }
}

