package com.c195.utility;

import com.c195.model.Country;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This abstract class assists with Countries data in the database.
 */
public abstract class DBCountry {

    /**
     * Retrieves all data from countries table in database.
     *
     * @return All countries list
     * @throws SQLException
     */
    public static ObservableList<Country> getAllCountries() throws SQLException{
        ObservableList<Country> allCountriesList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM countries";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()){
            int countryID = rs.getInt("Country_ID");
            String countryName = rs.getString("Country");
            Country country = new Country(countryID, countryName);
            allCountriesList.add(country);
        }
        return allCountriesList;
    }

    /**
     * Retrieves country data by country ID from database.
     *
     * @param countryID
     * @return Selected country list
     * @throws SQLException
     */
    public static Country getCountry(int countryID) throws SQLException{
        Country selectedCountry = null;
        String sql = "SELECT * FROM countries WHERE Country_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, countryID);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            int cID = rs.getInt("Country_ID");
            String cName = rs.getString("Country");
            selectedCountry = new Country(cID, cName);
        }
        return selectedCountry;
    }
}
