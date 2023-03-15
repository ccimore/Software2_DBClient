package com.c195.controller;

import com.c195.model.Appointment;
import com.c195.model.Contact;
import com.c195.model.Customer;
import com.c195.model.User;
import com.c195.utility.DBContact;
import com.c195.utility.DBCustomer;
import com.c195.utility.DBUser;
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
import java.time.LocalTime;
import java.util.ResourceBundle;

public class AppointmentController implements Initializable {

    Stage stage;
    Parent scene;
    public DatePicker startDatePicker;
    public ComboBox<LocalTime> startTimeCombo;
    public ComboBox<LocalTime> endTimeCombo;
    public Button saveButton;
    public DatePicker endDatePicker;
    public Button exitButton;
    public TextField apptIDText;
    public TextField apptTitleText;
    public TextField apptDescriptionText;
    public TextField apptLocationText;
    public TextField apptTypeText;
    public ComboBox<Contact> contactIDCombo;
    public ComboBox<Customer> customerIDCombo;
    public ComboBox<User> userIDCombo;





    public void onActionExitButton(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/com/c195/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    public void sendAppointment(int selectedIndex, Appointment appointment) {
        apptIDText.setText(String.valueOf(appointment.getAppointmentId()));
        apptTitleText.setText(appointment.getAppointmentTitle());
        apptDescriptionText.setText(appointment.getAppointmentDescription());
        apptLocationText.setText(appointment.getAppointmentLocation());
        apptTypeText.setText(appointment.getAppointmentType());







    }

    public void onActionSaveButton(ActionEvent actionEvent) {
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


