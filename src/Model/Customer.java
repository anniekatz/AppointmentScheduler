package Model;

import java.time.LocalDateTime;

public class Customer {
    private int CustomerID;
    private String CustomerName;
    private String CustomerAddress;
    private String CustomerZipCode;
    private String CustomerPhone;
    private int DivisionID;

    // Customer constructor
    public Customer(int CustomerID, String CustomerName, String CustomerAddress, String CustomerZipCode, String CustomerPhone, int DivisionID) {
        this.CustomerID = CustomerID;
        this.CustomerName = CustomerName;
        this.CustomerAddress = CustomerAddress;
        this.CustomerZipCode = CustomerZipCode;
        this.CustomerPhone = CustomerPhone;
        this.DivisionID = DivisionID;
    }

    // Get methods
    public int getCustomerID() {
        return CustomerID;
    }
    public String getCustomerName() {
        return CustomerName;
    }
    public String getCustomerAddress() {
        return CustomerAddress;
    }
    public String getCustomerZipCode() {
        return CustomerZipCode;
    }
    public String getCustomerPhone() { return CustomerPhone; }
    public int getDivisionID() {
        return DivisionID;
    }

    // Set methods
    public void setCustomerID(int CustomerID) { this.CustomerID = CustomerID; }
    public void setCustomerName(String CustomerName) { this.CustomerName = CustomerName; }
    public void setCustomerAddress(String CustomerAddress) { this.CustomerAddress = CustomerAddress; }
    public void setCustomerZipCode(String CustomerZipCode) { this.CustomerZipCode = CustomerZipCode; }
    public void setCustomerPhone(String CustomerPhone) { this.CustomerPhone = CustomerPhone; }
    public void setDivisionID(int DivisionID) { this.DivisionID = DivisionID; }

}
