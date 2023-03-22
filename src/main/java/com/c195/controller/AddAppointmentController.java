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
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ResourceBundle;


/**
 * This class controls the Add Appointment form and FXML file.
 */
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

    /**
     * Exits the Add Appointment form and takes user back to Main Menu form on button action.
     * @param actionEvent
     * @throws IOException
     */
    public void onActionExitButton(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/com/c195/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    /**
     * Adds appointment to database and takes user back to Appointment form on button action.
     * Checks for conflicts with appointment information.
     *
     * @param actionEvent
     * @throws SQLException
     * @throws IOException
     */
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

        ObservableList<Appointment> custApptList = DBAppointment.getApptByCustomerID(customer.getCustomerId());

        int workWeekStart = DayOfWeek.MONDAY.getValue();
        int workWeekEnd = DayOfWeek.FRIDAY.getValue();
        int apptStartWeek = startDateTime.toLocalDate().getDayOfWeek().getValue();
        int apptEndWeek = endDateTime.toLocalDate().getDayOfWeek().getValue();

        if (endDateTime.isBefore(startDateTime) || startDateTime.isEqual(endDateTime)){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Invalid appointment start and end times!");
            alert.showAndWait();
            return;
        }
        if (apptStartWeek > workWeekEnd || apptEndWeek > workWeekEnd){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Appointments can only be set Monday through Friday!");
            alert.showAndWait();
            return;
        }
        for (Appointment appt : custApptList){
                if(((appt.getStart().isEqual(startDateTime)) || (appt.getStart().isAfter(startDateTime))) && (appt.getStart().isBefore(endDateTime))){
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Appointment times overlap with existing appointment!");
                    alert.showAndWait();
                    return;
                }
                if ((appt.getEnd().isAfter(startDateTime)) && ((appt.getEnd().isBefore(endDateTime)) || (appt.getEnd().isEqual(endDateTime)))) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Appointment times overlap with existing appointment!");
                    alert.showAndWait();
                    return;
                }
                if (((appt.getStart().isBefore(startDateTime)) || (appt.getStart().isEqual(startDateTime))) && ((appt.getEnd().isAfter(endDateTime)) || (appt.getEnd().isEqual(endDateTime)))){
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Appointment times overlap with existing appointment!");
                    alert.showAndWait();
                    return;
                }
            }
        int rowsAffected = DBAppointment.insert(title, description, location, type, startDateTime, endDateTime, LocalDateTime.now(), "admin", LocalDateTime.now(), "admin", customer.getCustomerId(), user.getUserId(), contact.getContactId());
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

    /**
     * Initializes class and sets form for initialization state.
     * Sets appointment time options in time combo boxes to eastern time relative to user time zone.
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Contact> contacts;
        ObservableList<Customer> customers;
        ObservableList<User> users;
        ObservableList<LocalTime> apptTimeList = FXCollections.observableArrayList();

        LocalTime startTimeAppt = LocalTime.of(8, 0);
        LocalTime endTimeAppt = LocalTime.of(22, 0);

        LocalDateTime startTimeLDT = LocalDateTime.of(LocalDate.now(), startTimeAppt);
        LocalDateTime endTimeLDT = LocalDateTime.of(LocalDate.now(), endTimeAppt);
        LocalDateTime estStartLDT = TimeHelper.convertLocalToEst(startTimeLDT);
        LocalDateTime estEndLDT = TimeHelper.convertLocalToEst(endTimeLDT);

        while (estStartLDT.isBefore(estEndLDT.plusSeconds(1))){
            apptTimeList.add(estStartLDT.toLocalTime());
            estStartLDT = estStartLDT.plusMinutes(15);
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