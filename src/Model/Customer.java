package Model;

import java.time.LocalDateTime;

public class Customer {
    private int CustomerID;
    private String Name;
    private String Address;
    private String ZipCode;
    private String Phone;
    private LocalDateTime CreateDate;
    private String CreatedBy;
    private LocalDateTime LastUpdate;
    private String LastUpdateBy;
    private int DivisionID;

    // Customer constructor
    public Customer(int CustomerID, String Name, String Address, String ZipCode, String Phone, LocalDateTime CreateDate, String CreatedBy, LocalDateTime LastUpdate, String LastUpdateBy, int DivisionID) {
        this.CustomerID = CustomerID;
        this.Name = Name;
        this.Address = Address;
        this.ZipCode = ZipCode;
        this.Phone = Phone;
        this.CreateDate = CreateDate;
        this.CreatedBy = CreatedBy;
        this.LastUpdate = LastUpdate;
        this.LastUpdateBy = LastUpdateBy;
        this.DivisionID = DivisionID;
    }

    // Get methods
    public int GetCustomerID() {
        return CustomerID;
    }
    public String GetName() {
        return Name;
    }
    public String GetAddress() {
        return Address;
    }
    public String GetZipCode() {
        return ZipCode;
    }
    public String GetPhone() {
        return Phone;
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
    public int GetDivisionID() {
        return DivisionID;
    }
}
