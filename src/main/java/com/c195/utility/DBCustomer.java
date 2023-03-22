package com.c195.utility;

import com.c195.model.Customer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This abstract class assists with Customer data in database.
 */
public abstract class DBCustomer {

    /**
     * Retrieves all customer data from customers table in database.
     *
     * @return All customers list
     * @throws SQLException
     */
    public static ObservableList<Customer> getAllCustomers() throws SQLException{

        String sql = "SELECT * FROM CUSTOMERS";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();

        while (rs.next()) {
            int customerId = rs.getInt("Customer_ID");
            String customerName = rs.getString("Customer_Name");
            String customerAddress = rs.getString("Address");
            String customerPostalCode = rs.getString("Postal_Code");
            String customerPhone = rs.getString("Phone");
            int customerDivisionId = rs.getInt("Division_ID");

            Customer customer = new Customer(customerId, customerName, customerAddress, customerPostalCode, customerPhone, customerDivisionId);
            customerObservableList.add(customer);
        }
        return customerObservableList;
    }

    /**
     * Inserts customer data into customer table in database.
     *
     * @param customerName Customer name
     * @param address Customer address
     * @param postalCode Customer postal code
     * @param phone customer phone
     * @param divisionID Customer division ID
     * @return Rows affected
     * @throws SQLException
     */
    public static int insert(String customerName, String address, String postalCode, String phone, int divisionID) throws SQLException {
        String sql = "INSERT INTO CUSTOMERS (Customer_Name, Address, Postal_Code, Phone, Division_ID) VALUES(?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, customerName);
        ps.setString(2, address);
        ps.setString(3, postalCode);
        ps.setString(4, phone);
        ps.setInt(5, divisionID);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    /**
     * Updates customer data in customers table in database by customer ID.
     *
     * @param customerID Customer ID
     * @param customerName Customer name
     * @param customerAddress Customer address
     * @param postalCode Customer postal code
     * @param phone Customer phone
     * @param divID Customer division ID
     * @return Rows affected
     * @throws SQLException
     */
    public static int update(int customerID, String customerName, String customerAddress, String postalCode, String phone, int divID) throws SQLException {
        String sql = "UPDATE CUSTOMERS SET Customer_Name = ?, Address = ?, Postal_Code = ?, Phone = ?, Division_ID = ? WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, customerName);
        ps.setString(2, customerAddress);
        ps.setString(3, postalCode);
        ps.setString(4, phone);
        ps.setInt(5, divID);
        ps.setInt(6, customerID);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    /**
     * Deletes customer from database by customer ID.
     *
     * @param customerID Customer ID
     * @return Rows affected
     * @throws SQLException
     */
    public static int delete(int customerID) throws SQLException {
        String sql = "DELETE FROM CUSTOMERS WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, customerID);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    /**
     * Selects all customer data from customer table in database.
     *
     * @throws SQLException
     */
    public static void select() throws SQLException {
        String sql = "SELECT * FROM CUSTOMERS";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int fruitId = rs.getInt("Customer_ID");
            String fruitName = rs.getString("Customer_Name");
            System.out.print(fruitId + " | ");
            System.out.print(fruitName + "\n");
        }
    }


}
