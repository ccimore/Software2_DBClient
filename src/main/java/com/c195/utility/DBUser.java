package com.c195.utility;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DBUser {

    public static int insert(String userName, String password) throws SQLException {
        String sql = "INSERT INTO USERS (User_Name, Password) VALUES(?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, userName);
        ps.setString(2, password);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    public static int update(int userID, String userName) throws SQLException {
        String sql = "UPDATE USERS SET User_Name = ? WHERE User_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, userName);
        ps.setInt(2, userID);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    public static int delete(int userID) throws SQLException {
        String sql = "DELETE FROM USERS WHERE User_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, userID);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    public static void select() throws SQLException {
        String sql = "SELECT * FROM USERS";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int userId = rs.getInt("User_ID");
            String userName = rs.getString("User_Name");
            System.out.print(userId + " | ");
            System.out.print(userName + "\n");
        }
    }

    public static void select(String userName) throws SQLException {
        String sql = "SELECT * FROM USERS WHERE User_Name = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, userName);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int userID = rs.getInt("User_ID");
            String name = rs.getString("User_Name");
            String pw = rs.getString("Password");
            System.out.print(userID + " | ");
            System.out.print(name + " | ");
            System.out.print(pw + "\n");
        }
    }

}
