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

    /**
     * This method gets customer ID
     * @return Int value of customer ID
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * This method gets customer name
     * @return String value of customer name
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * This method gets customer address
     * @return String value of customer address
     */
    public String getCustomerAddress() {
        return customerAddress;
    }

    /**
     * This method gets customer zip code
     * @return String value of customer zip code
     */
    public String getCustomerZipCode() {
        return customerZipCode;
    }

    /**
     * This method gets customer phone
     * @return String value of customer phone
     */
    public String getCustomerPhone() { return customerPhone; }

    /**
     * This method gets customer division ID
     * @return Int value of customer division ID
     */
    public int getDivisionID() {
        return divisionID;
    }

}
