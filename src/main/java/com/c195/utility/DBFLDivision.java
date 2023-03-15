package com.c195.utility;

import com.c195.model.Country;
import com.c195.model.FirstLevelDivision;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DBFLDivision {

    public static ObservableList<FirstLevelDivision> getAllDivisions() throws SQLException {
        ObservableList<FirstLevelDivision> firstLevelDivList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM first_level_divisions";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int divID = rs.getInt("Division_ID");
            String divName = rs.getString("Division");
            int countryID = rs.getInt("Country_ID");
            FirstLevelDivision firstLevelDivision = new FirstLevelDivision(divID, divName, countryID);
            firstLevelDivList.add(firstLevelDivision);
        }
        return firstLevelDivList;
    }

    public static ObservableList<FirstLevelDivision> getDivListByCountry(int countryID) throws SQLException{
        ObservableList<FirstLevelDivision> divListByCountry = FXCollections.observableArrayList();
        String sql = "SELECT * FROM first_level_divisions WHERE Country_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, countryID);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int divID = rs.getInt("Division_ID");
            String divName = rs.getString("Division");
            int dCountryID = rs.getInt("Country_ID");
            FirstLevelDivision selectedDivision = new FirstLevelDivision(divID, divName, dCountryID);
            divListByCountry.add(selectedDivision);
        }
        return divListByCountry;
    }




}
