package com.c195;

import com.c195.model.Appointment;
import com.c195.model.Customer;
import com.c195.utility.DBAppointment;
import com.c195.utility.DBCustomer;
import com.c195.utility.FruitsQuery;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.c195.utility.JDBC;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("iCalendar");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException {

        JDBC.openConnection();

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
            System.out.println("Delete Failed!");
        }
/*
        ObservableList<Customer> custList = DBCustomer.getAllCustomers();

        if (custList != null) {
            System.out.println("List created.");
        }

        ObservableList<Appointment > apptList = DBAppointment.getAllAppointments();

        if (apptList != null) {
            System.out.println("List created.");
        }

 */


        int rowsApptInsert = DBAppointment.insert("Test", "Test description", "Test Location", "Test Session", LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now(), "SCRIPT", LocalDateTime.now(), "SCRIPT", 2, 2, 2);
        if(rowsApptInsert > 0){
            System.out.println("Insert Successful!");
        } else {
            System.out.println("Insert Failed!");
        }

/*
        int rowsApptDelete = DBAppointment.deleteAppointment(4);
        if(rowsApptDelete > 0){
            System.out.println("Delete Successful!");
        } else {
            System.out.println("Delete Failed!");
        }

 */

        Appointment appt1 = new Appointment(1, "sfl", "sldjfgl", "sldjf", "lsdfjkls", LocalDateTime.now(),LocalDateTime.now(), 3, 3, 3);
        System.out.println(appt1.getApptContactId());


        System.out.println("Program Launch!");

        launch();

        JDBC.closeConnection();
    }
}