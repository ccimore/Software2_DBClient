package com.c195;

/**
 *
 * @author Chris Cimorelli
 *
 * An Appointment Management Application
 *
 * My Javadocs folder is inside my src folder.
 * From Project Structure tab, I added Maven Project library for mysql connector 8.0.25.
 * Maven: mysql:mysql-connector-java:8.0.25
 * login_activity.txt file is in project folder.
 *
 *
 *
 *
 * */


import com.c195.utility.*;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;



/** This main class creates an application for appointment management */
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
        System.out.println("Program Launch!");
        launch();

        JDBC.closeConnection();
    }
}