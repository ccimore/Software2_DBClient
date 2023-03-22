package com.c195.utility;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This abstract class assists with login data from database.
 */
public abstract class DBLogin {

    /**
     * Retrieves user login data from database.
     *
     * @param userName User name
     * @param password Password
     * @return User ID or -1
     * @throws SQLException
     */
    public static int loginCheck(String userName, String password) throws SQLException {
        try {


            String sql = "SELECT * FROM users WHERE User_Name = '" + userName + "' AND Password = '" + password + "'";
            PreparedStatement ps = JDBC.connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            rs.next();
            if (rs.getString("User_Name").equals(userName) && (rs.getString("Password").equals(password))) {
                return rs.getInt("User_ID");

            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

}
