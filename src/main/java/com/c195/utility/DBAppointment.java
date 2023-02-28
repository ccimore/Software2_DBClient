package com.c195.utility;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public abstract class DBAppointment {

    public static int insert(String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, int custID, int userID, int contactID) throws SQLException {
        String sql = "INSERT INTO APPOINTMENTS (Title, Description, Location, Type, End, Customer_ID, User_ID, Contact_ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);

        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    public static int update(int customerID, String customerName) throws SQLException {
        String sql = "UPDATE CUSTOMERS SET Customer_Name = ? WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, customerName);
        ps.setInt(2, customerID);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    public static int delete(int customerID) throws SQLException {
        String sql = "DELETE FROM CUSTOMERS WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, customerID);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

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

    public static void select(int colorId) throws SQLException {
        String sql = "SELECT * FROM CUSTOMERS WHERE Division_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, colorId);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int fruitId = rs.getInt("Customer_ID");
            String fruitName = rs.getString("Customer_Name");
            int colorIdFK = rs.getInt("Division_ID");
            System.out.print(fruitId + " | ");
            System.out.print(fruitName + " | ");
            System.out.print(colorIdFK + "\n");
        }
    }
}
