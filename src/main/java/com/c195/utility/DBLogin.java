package com.c195.utility;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBLogin {

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
