package com.c195.model;

import java.time.LocalDateTime;

/**
 * The Customer class is the blueprint for the Customer objects.
 */
public class Customer {

    private int customerId;
    private String customerName;
    private String customerAddress;
    private String customerPostalCode;
    private String customerPhone;
    public int customerDivisionId;

    /**
     * This is the constructor for the Customer objects.
     *
     * @param customerId Customer ID
     * @param customerName Customer Name
     * @param customerAddress Customer Address
     * @param customerPostalCode Customer Postal Code
     * @param customerPhone Customer Phone
     * @param customerDivisionId Customer Division ID
     */
    public Customer(int customerId, String customerName, String customerAddress, String customerPostalCode, String customerPhone, int customerDivisionId) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerPostalCode = customerPostalCode;
        this.customerPhone = customerPhone;
        this.customerDivisionId = customerDivisionId;
    }

    /**
     * Retrieves customer ID.
     *
     * @return Customer ID
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * Sets customer ID.
     *
     * @param customerId Customer ID
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * Retrieves customer name.
     *
     * @return Customer name
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Sets customer name.
     *
     * @param customerName Customer name
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Retrieves customer address.
     *
     * @return Customer address
     */
    public String getCustomerAddress() {
        return customerAddress;
    }

    /**
     * Sets customer address.
     *
     * @param customerAddress Customer address
     */
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    /**
     * Retrieves customer postal code.
     *
     * @return Postal code
     */
    public String getCustomerPostalCode() {
        return customerPostalCode;
    }

    /**
     * Sets customer postal code.
     *
     * @param customerPostalCode Customer postal code.
     */
    public void setCustomerPostalCode(String customerPostalCode) {
        this.customerPostalCode = customerPostalCode;
    }

    /**
     * Retrieves customer phone number.
     *
     * @return Customer phone number
     */
    public String getCustomerPhone() {
        return customerPhone;
    }

    /**
     * Sets customer phone number.
     *
     * @param customerPhone Customer phone number
     */
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    /**
     * Retrieves customer division ID.
     *
     * @return Customer Division ID
     */
    public int getCustomerDivisionId() {
        return customerDivisionId;
    }

    /**
     * Sets customer division ID.
     *
     * @param customerDivisionId Customer Division ID
     */
    public void setCustomerDivisionId(int customerDivisionId) {
        this.customerDivisionId = customerDivisionId;
    }

    /**
     * Overrides default toString method to return custom string representation of Customer object.
     *
     * @return Custom string representation of Customer object.
     */
    @Override
    public String toString(){
        return (customerName + " (ID: " +Integer.toString(customerId) + ")");
    }
}
