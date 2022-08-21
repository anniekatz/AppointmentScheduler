package Model;

import java.time.LocalDateTime;

public class Division {
    private int DivisionID;
    private String DivisionName;
    private int CountryID;

    // Divison constructor
    public Division(int DivisionID, String DivisionName, int CountryID) {
        this.DivisionID = DivisionID;
        this.DivisionName = DivisionName;
        this.CountryID = CountryID;
    }

    // Get methods
    public int getDivisionID() {
        return DivisionID;
    }
    public String getDivisionName() {
        return DivisionName;
    }
    public int getCountryID() {
        return CountryID;
    }
}

