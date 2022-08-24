package Model;

/**
 * This class represents the model for a customer.
 */
public class Customer {

    private int customerID;
    private String customerName;
    private String customerAddress;
    private String customerZipCode;
    private String customerPhone;
    private int divisionID;

    /**
     * This is the constructor for a customer.
     * @param customerID Int value of customer ID
     * @param customerName String value of customer name
     * @param customerAddress String value of customer address
     * @param customerZipCode String value of customer zip code
     * @param customerPhone String value of customer phone
     * @param divisionID Int value of customer division ID
     */
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
