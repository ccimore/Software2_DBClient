package com.c195.utility;

import com.c195.model.Contact;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * This abstract class assists with contact data in the database.
 */
public abstract class DBContact {

    /**
     * Retrieves all contacts from database.
     *
     * @return All contacts list
     * @throws SQLException
     */
    public static ObservableList<Contact> getAllContacts() throws SQLException{
        ObservableList<Contact> contactList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM contacts";
        PreparedStatement ps = JDBC.connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int contactID = rs.getInt("Contact_ID");
            String contactName = rs.getString("Contact_Name");
            String email = rs.getString("Email");
            Contact contact = new Contact(contactID, contactName, email);
            contactList.add(contact);
        }
        return contactList;
    }
}
