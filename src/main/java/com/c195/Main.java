package com.c195;

/**
 *
 * @author Chris Cimorelli
 *
 * An Appointment Management APplication
 *
 * My Javadocs folder is inside my src folder.
 * From Project Structure tab, I added Maven Project library for mysql connector 8.0.25.
 * Maven: mysql:mysql-connector-java:8.0.25
 *
 *
 *
 * */

import com.c195.model.Appointment;
import com.c195.model.Customer;
import com.c195.model.DivisionTotal;
import com.c195.model.TypeTotal;
import com.c195.utility.*;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Locale;
import java.util.ResourceBundle;


/** This main class creates an application for appoinemnt management */
public class Main extends Application {
    /**
     * The start method initializes the FXML stage and scene load of Login FXML file.
     *
     * @param stage The FXML stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("iCalendar");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * The main method initializes the application.
     *
     * @param args The main args parameter
     * @throws SQLException
     */
    public static void main(String[] args) throws SQLException {

        JDBC.openConnection();

        int workWeekStart = DayOfWeek.SUNDAY.getValue();
        System.out.println(workWeekStart);

        DayOfWeek monday = DayOfWeek.MONDAY;
        DayOfWeek sunday = DayOfWeek.SUNDAY;



        ObservableList<Appointment> custApptList = DBAppointment.getApptByCustomerID(2);
        System.out.println(custApptList);
        custApptList.forEach(Appointment -> {
            System.out.println(Appointment.getEnd());
            System.out.println(Appointment.getEnd());
        });
/*
        ResourceBundle rb = ResourceBundle.getBundle("com/c195/language/login", Locale.getDefault());
        if(Locale.getDefault().getLanguage().equals("en"))
            System.out.println(rb.getString("Location"));

        int login1 = DBLogin.loginCheck("admin", "admin");
        System.out.println(login1);

        LocalTime startTimeAppt = LocalTime.of(8, 0);
        LocalTime endTimeAppt = LocalTime.of(22, 0);


        LocalDateTime startTimeLDT = LocalDateTime.of(LocalDate.now(), startTimeAppt);
        LocalDateTime endTimeLDT = LocalDateTime.of(LocalDate.now(), endTimeAppt);
        LocalDateTime estStartLDT = TimeHelper.convertLocalToEst(startTimeLDT);
        System.out.println(estStartLDT);

        ObservableList<Appointment> custApptList = DBAppointment.getApptByCustomerID(1);
        System.out.println(custApptList);

        /*



        LocalDateTime UTCtime = TimeHelper.convertToUTC(LocalDateTime.now());
        System.out.println(UTCtime);




        int rowsAffected = FruitsQuery.insert("Cherries", 3);
        if(rowsAffected > 0){
            System.out.println("Insert Successful!");
        } else {
            System.out.println("Insert Failed!");
        }

        int rowsAffectedDelete = FruitsQuery.delete("Cherries");
        if(rowsAffectedDelete > 0){
            System.out.println("Delete Successful!");
        } else {
            System.out.println("Delete Failed!");       }



        ObservableList<Customer> custList = DBCustomer.getAllCustomers();

        if (custList != null) {
            System.out.println("List created.");
        }

        ObservableList<Appointment > apptList = DBAppointment.getAllAppointments();

        if (apptList != null) {
            System.out.println("List created.");
        }


        int rowsApptInsert = DBAppointment.insert("Test", "Test description", "Test Location", "Test Session", LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), "SCRIPT", LocalDateTime.now(), "SCRIPT", 2, 2, 2);
        if(rowsApptInsert > 0){
            System.out.println("Insert Successful!");
        } else {
            System.out.println("Insert Failed!");
        }




        int rowsApptDelete = DBAppointment.deleteAppointment(4);
        if(rowsApptDelete > 0){
            System.out.println("Delete Successful!");
        } else {
            System.out.println("Delete Failed!");
        }



        Appointment appt1 = new Appointment(1, "sfl", "sldjfgl", "sldjf", "lsdfjkls", LocalDateTime.now(),LocalDateTime.now(), 3, 3, 3);
        System.out.println(appt1.getApptContactId());


         */


        System.out.println("Program Launch!");

        launch();

        JDBC.closeConnection();
    }
}