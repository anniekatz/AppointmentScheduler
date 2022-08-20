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
    public int GetCustomerID() {
        return CustomerID;
    }
    public String GetCustomerName() {
        return CustomerName;
    }
    public String GetCustomerAddress() {
        return CustomerAddress;
    }
    public String GetCustomerZipCode() {
        return CustomerZipCode;
    }
    public String GetCustomerPhone() { return CustomerPhone; }
    public int GetDivisionID() {
        return DivisionID;
    }

    // Set methods
    public void SetCustomerID(int CustomerID) {
        this.CustomerID = CustomerID;
    }
    public void SetCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }
    public void SetCustomerAddress(String CustomerAddress) {
        this.CustomerAddress = CustomerAddress;
    }
    public void SetCustomerZipCode(String CustomerZipCode) {
        this.CustomerZipCode = CustomerZipCode;
    }
    public void SetCustomerPhone(String CustomerPhone) {
        this.CustomerPhone = CustomerPhone;
    }
    public void SetDivisionID(int DivisionID) {
        this.DivisionID = DivisionID;
    }
}
