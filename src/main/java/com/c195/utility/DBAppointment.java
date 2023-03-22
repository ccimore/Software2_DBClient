package com.c195.utility;

import com.c195.model.Appointment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * This class works with the database to assist the Appointment class.
 */
public abstract class DBAppointment {

    public static ObservableList<Appointment> allAppointmentsList;

    /**
     * Retrieves Appointment data from database.
     *
     * @return All appointments list
     * @throws SQLException
     */
    public static ObservableList<Appointment> getAllAppointments() throws SQLException{

        String sql = "SELECT * FROM APPOINTMENTS";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();


        ObservableList<Appointment> appointmentObservableList = FXCollections.observableArrayList();

        while (rs.next()) {
            int apptID = rs.getInt("Appointment_ID");
            String apptTitle = rs.getString("Title");
            String apptDescription = rs.getString("Description");
            String apptLocation = rs.getString("Location");
            String apptType = rs.getString("Type");
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
            int customerID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            int contactID = rs.getInt("Contact_ID");

            Appointment appointment = new Appointment(apptID, apptTitle, apptDescription, apptLocation, apptType, start, end, customerID, userID, contactID);
            appointmentObservableList.add(appointment);
        }
        allAppointmentsList = appointmentObservableList;
        return allAppointmentsList;
    }

    /**
     * Retrieves database appointment data by contact ID.
     *
     * @param contactID Contact ID
     * @return Appointment by contact list
     * @throws SQLException
     */
    public static ObservableList<Appointment> getApptByContactID(int contactID) throws SQLException {
        String sql = "SELECT * FROM APPOINTMENTS WHERE Contact_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, contactID);
        ResultSet rs = ps.executeQuery();

        ObservableList<Appointment> apptByContactList = FXCollections.observableArrayList();

        while (rs.next()) {
            int apptID = rs.getInt("Appointment_ID");
            String apptTitle = rs.getString("Title");
            String apptDescription = rs.getString("Description");
            String apptLocation = rs.getString("Location");
            String apptType = rs.getString("Type");
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
            int customerID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            int contID = rs.getInt("Contact_ID");

            Appointment appointment = new Appointment(apptID, apptTitle, apptDescription, apptLocation, apptType, start, end, customerID, userID, contID);
            apptByContactList.add(appointment);
        }

        return apptByContactList;
    }

    /**
     * Retrieves appointment data by customer ID from the database.
     *
     * @param custID Customer ID
     * @return Appointment by customer list
     * @throws SQLException
     */
    public static ObservableList<Appointment> getApptByCustomerID(int custID) throws SQLException {
        String sql = "SELECT * FROM APPOINTMENTS WHERE Customer_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, custID);
        ResultSet rs = ps.executeQuery();

        ObservableList<Appointment> apptByContactList = FXCollections.observableArrayList();

        while (rs.next()) {
            int apptID = rs.getInt("Appointment_ID");
            String apptTitle = rs.getString("Title");
            String apptDescription = rs.getString("Description");
            String apptLocation = rs.getString("Location");
            String apptType = rs.getString("Type");
            LocalDateTime start = rs.getTimestamp("Start").toLocalDateTime();
            LocalDateTime end = rs.getTimestamp("End").toLocalDateTime();
            int customerID = rs.getInt("Customer_ID");
            int userID = rs.getInt("User_ID");
            int contID = rs.getInt("Contact_ID");

            Appointment appointment = new Appointment(apptID, apptTitle, apptDescription, apptLocation, apptType, start, end, customerID, userID, contID);
            apptByContactList.add(appointment);
        }

        return apptByContactList;
    }

    /**
     * Deletes appointment from database by appointment ID.
     *
     * @param apptID Appointment ID
     * @return Rows affected
     * @throws SQLException
     */
    public static int deleteAppointment(int apptID) throws SQLException {
        String sql = "DELETE FROM APPOINTMENTS WHERE APPOINTMENT_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, apptID);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    /**
     * Deletes appointment from database by customer ID.
     *
     * @param custID Customer ID
     * @return Rows affected
     * @throws SQLException
     */
    public static int deleteAppointmentByCustID(int custID) throws SQLException {
        String sql = "DELETE FROM APPOINTMENTS WHERE CUSTOMER_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setInt(1, custID);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    /**
     * Inserts appointment into data base.
     *
     * @param title Appointment title
     * @param description Appointment description
     * @param location Appointment location
     * @param type Appointment type
     * @param start Appointment start
     * @param end Appointment end
     * @param createDate Appointment create date
     * @param createBY Appointment created by
     * @param updateDate Appointmnent update date
     * @param updateBy Appointment update by
     * @param custID Appointment customer ID
     * @param userID Appointment User ID
     * @param contactID Appointment contact ID
     * @return Rows affected
     * @throws SQLException
     */
    public static int insert(String title, String description, String location, String type, LocalDateTime start, LocalDateTime end, LocalDateTime createDate, String createBY, LocalDateTime updateDate, String updateBy, int custID, int userID, int contactID) throws SQLException {
        String sql = "INSERT INTO APPOINTMENTS (Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, title);
        ps.setString(2, description);
        ps.setString(3, location);
        ps.setString(4, type);
        ps.setTimestamp(5, Timestamp.valueOf(start));
        ps.setTimestamp(6, Timestamp.valueOf(end));
        ps.setTimestamp(7, Timestamp.valueOf(createDate));
        ps.setString(8, createBY);
        ps.setTimestamp(9, Timestamp.valueOf(updateDate));
        ps.setString(10, updateBy);
        ps.setInt(11, custID);
        ps.setInt(12, userID);
        ps.setInt(13, contactID);

        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

    /**
     * Updates appointment in database by appointment ID.
     *
     * @param apptID Appointment ID
     * @param apptTitle Appointment title
     * @param apptDescription Appointment description
     * @param apptLocation Appointment location
     * @param apptType Appointment type
     * @param apptStart Appointment start
     * @param apptEnd Appointment end
     * @param apptCustID Appointment customer ID
     * @param apptUserID Appointment user ID
     * @param apptContactID Appointment contact ID
     * @return Rows affected
     * @throws SQLException
     */
    public static int update(int apptID, String apptTitle, String apptDescription, String apptLocation, String apptType, LocalDateTime apptStart, LocalDateTime apptEnd, int apptCustID, int apptUserID, int apptContactID) throws SQLException {
        String sql = "UPDATE appointments SET Title = ?, Description = ?, Location = ?, Type = ?, Start = ?, End = ?, Last_Update = ?, Last_Updated_By = ?, Customer_ID = ?, User_ID = ?, Contact_ID = ? WHERE Appointment_ID = ?";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ps.setString(1, apptTitle);
        ps.setString(2, apptDescription);
        ps.setString(3, apptLocation);
        ps.setString(4, apptType);
        ps.setTimestamp(5, Timestamp.valueOf(apptStart));
        ps.setTimestamp(6, Timestamp.valueOf(apptEnd));
        ps.setTimestamp(7, Timestamp.valueOf(LocalDateTime.now()));
        ps.setString(8, "admin");
        ps.setInt(9, apptCustID);
        ps.setInt(10, apptUserID);
        ps.setInt(11, apptContactID);
        ps.setInt(12, apptID);
        int rowsAffected = ps.executeUpdate();
        return rowsAffected;
    }

}
