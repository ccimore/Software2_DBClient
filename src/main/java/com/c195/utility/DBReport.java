package com.c195.utility;

import com.c195.model.DivisionTotal;
import com.c195.model.MonthTotal;
import com.c195.model.TypeTotal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBReport {

    public static ObservableList<MonthTotal> monthTotalList;
    public static ObservableList<TypeTotal> typeTotalList;
    public static ObservableList<DivisionTotal> divisionTotalList;

    public static ObservableList<MonthTotal> getAllMonthTotals() throws SQLException {
        String sql = "SELECT monthname(Start), COUNT(*) FROM APPOINTMENTS GROUP BY monthname(Start)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ObservableList<MonthTotal> monthTotalObservableList = FXCollections.observableArrayList();
        while (rs.next()) {
            String month = rs.getString("monthname(Start)");
            int total = rs.getInt("COUNT(*)");

            MonthTotal monthTotal = new MonthTotal(total, month);
            monthTotalObservableList.add(monthTotal);
        }
        monthTotalList = monthTotalObservableList;
        return monthTotalList;

    }

    public static ObservableList<TypeTotal> getAllTypeTotals() throws SQLException {
        String sql = "SELECT Type, COUNT(*) FROM appointments GROUP BY Type";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ObservableList<TypeTotal> typeTotalObservableList = FXCollections.observableArrayList();
        while (rs.next()) {
            String type = rs.getString("Type");
            int total = rs.getInt("COUNT(*)");

            TypeTotal typeTotal = new TypeTotal(total, type);
            typeTotalObservableList.add(typeTotal);
        }
        typeTotalList = typeTotalObservableList;
        return typeTotalList;
    }

    public static ObservableList<DivisionTotal> getAllDivisionTotals() throws SQLException {
        String sql = "SELECT first_level_divisions.Division_ID, Division, Count(customers.Division_ID) FROM first_level_divisions JOIN customers ON customers.Division_ID = first_level_divisions.Division_ID GROUP BY first_level_divisions.Division_ID";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        ObservableList<DivisionTotal> divTotalObservableList = FXCollections.observableArrayList();
        while (rs.next()) {
            int total = rs.getInt("Count(customers.Division_ID)");
            String name = rs.getString("Division");
            int divID = rs.getInt("Division_ID");

            DivisionTotal divTotal = new DivisionTotal(total, name, divID);
            divTotalObservableList.add(divTotal);
        }
        divisionTotalList = divTotalObservableList;
        return divTotalObservableList;
    }

}
