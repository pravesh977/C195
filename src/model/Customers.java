package model;

public class Customers {

    private int customerId;
    private String customerName;
    private String customerAddress;
    private String customerPostalCode;
    private String customerPhone;
    private int divisionId;
    private String divisionName;

    /** Constructor for Customer Class*/
    public Customers(int customerId, String customerName, String customerAddress, String customerPostalCode, String customerPhone, int divisionId, String divisionName) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostalCode = customerPostalCode;
        this.customerPhone = customerPhone;
        this.divisionId = divisionId;
        this.divisionName = divisionName;
    }

    /** Getter for Customer Id*/
    public int getCustomerId() {
        return customerId;
    }

    /** Getter for Customer Name*/
    public String getCustomerName() {
        return customerName;
    }

    /** Getter for Customer Address*/
    public String getCustomerAddress() {
        return customerAddress;
    }

    /** Getter for Customer Postal Code*/
    public String getCustomerPostalCode() {
        return customerPostalCode;
    }

    /** Getter for Customer Phone*/
    public String getCustomerPhone() {
        return customerPhone;
    }

    /** Getter for Customer Division Id*/
    public int getDivisionId() {
        return divisionId;
    }

    public String getDivisionName() {
        return divisionName;
    }
}
