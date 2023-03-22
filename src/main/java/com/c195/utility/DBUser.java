package com.c195.utility;

import com.c195.model.Country;
import com.c195.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This abstract class assists with User data in database.
 */
public abstract class DBUser {

    /**
     * Retrieves all user data from User table in database.
     *
     * @return All user list
     * @throws SQLException
     */
    public static ObservableList<User> getAllUsers() throws SQLException {
        ObservableList<User> allUsersList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM users";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            int userID = rs.getInt("User_ID");
            String userName = rs.getString("User_Name");
            String pw = rs.getString("Password");
            User user = new User(userID, userName, pw);
            allUsersList.add((user));
        }
        return allUsersList;
    }


    /**
     * Retrieves users from user table in database.
     *
     * @throws SQLException
     */
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

    /**
     * Retrieves user from user table in database by user name.
     *
     * @param userName User name
     * @throws SQLException
     */
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
