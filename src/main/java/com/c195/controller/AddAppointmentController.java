package com.c195.controller;

import com.c195.model.Appointment;
import com.c195.model.Contact;
import com.c195.model.Customer;
import com.c195.model.User;
import com.c195.utility.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AddAppointmentController implements Initializable {

    public TextField apptIDText;
    public TextField titleText;
    public TextField descriptionText;
    public TextField locationText;
    public TextField typeText;
    public DatePicker startDatePicker;
    public DatePicker endDatePicker;
    public ComboBox<LocalTime> startTimeCombo;
    public ComboBox<LocalTime> endTimeCombo;
    public Button saveButton;
    public ComboBox<Contact> contactIDCombo;
    public ComboBox<Customer> customerIDCombo;
    public ComboBox<User> userIDCombo;
    public Button exitButton;
    Stage stage;
    Parent scene;


    public void onActionExitButton(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/com/c195/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }




    public void onActionSaveButton(ActionEvent actionEvent) throws SQLException, IOException {

        if (titleText.getText().isEmpty() || descriptionText.getText().isEmpty() || typeText.getText().isEmpty() || startDatePicker.getChronology() == null || startTimeCombo.getValue() == null || endDatePicker.getChronology() == null
                || endTimeCombo.getValue() == null || contactIDCombo.getValue() == null || customerIDCombo.getValue() == null || userIDCombo.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please fill out all fields!");
            alert.showAndWait();
            return;
        }
        LocalDate apptDateStart = startDatePicker.getValue();
        LocalDate apptDateEnd = endDatePicker.getValue();
        LocalTime apptTimeStart = startTimeCombo.getValue();
        LocalTime apptTimeEnd = endTimeCombo.getValue();

        String title = titleText.getText();
        String description = descriptionText.getText();
        String location = locationText.getText();
        String type = typeText.getText();
        Contact contact = contactIDCombo.getValue();
        Customer customer = customerIDCombo.getValue();
        User user = userIDCombo.getValue();
        LocalDateTime startDateTime = LocalDateTime.of(apptDateStart, apptTimeStart);
        LocalDateTime endDateTime = LocalDateTime.of(apptDateEnd, apptTimeEnd);
        LocalDateTime startDateTimeUTC = TimeHelper.convertToUTC(startDateTime);
        LocalDateTime endDateTimeUTC = TimeHelper.convertToUTC(endDateTime);


        int rowsAffected = DBAppointment.insert(title, description, location, type, startDateTimeUTC, endDateTimeUTC, LocalDateTime.now(), "admin", LocalDateTime.now(), "admin", customer.getCustomerId(), user.getUserId(), contact.getContactId());
        if (rowsAffected > 0) {
            System.out.println("Insert Successful!");
        } else {
            System.out.println("Insert Failed!");

        }
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/com/c195/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Contact> contacts;
        ObservableList<Customer> customers;
        ObservableList<User> users;
        ObservableList<LocalTime> apptTimeList = FXCollections.observableArrayList();

        LocalTime startTimeAppt = LocalTime.MIN.plusHours(8);
        LocalTime endTimeAppt = LocalTime.MAX.minusHours(1).minusMinutes(45);

        if (!startTimeAppt.equals(0) || !endTimeAppt.equals(0)){
            while (startTimeAppt.isBefore(endTimeAppt)) {
                apptTimeList.add(startTimeAppt);
                startTimeAppt = startTimeAppt.plusMinutes(15);
            }
        }
        startTimeCombo.setItems(apptTimeList);
        endTimeCombo.setItems(apptTimeList);


        try {
            contacts = DBContact.getAllContacts();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            customers = DBCustomer.getAllCustomers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            users = DBUser.getAllUsers();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        userIDCombo.setItems(users);
        customerIDCombo.setItems(customers);
        contactIDCombo.setItems(contacts);
    }

}