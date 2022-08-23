package Model;

// Model to represent a customer
public class Customer {

    private int customerID;
    private String customerName;
    private String customerAddress;
    private String customerZipCode;
    private String customerPhone;
    private int divisionID;

    // Customer constructor
    public Customer(int customerID, String customerName, String customerAddress, String customerZipCode, String customerPhone, int divisionID) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerZipCode = customerZipCode;
        this.customerPhone = customerPhone;
        this.divisionID = divisionID;
    }

    // Get customer information methods
    public int getCustomerID() {
        return customerID;
    }
    public String getCustomerName() {
        return customerName;
    }
    public String getCustomerAddress() {
        return customerAddress;
    }
    public String getCustomerZipCode() {
        return customerZipCode;
    }
    public String getCustomerPhone() { return customerPhone; }
    public int getDivisionID() {
        return divisionID;
    }

}
