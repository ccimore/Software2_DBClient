package com.c195.controller;

import com.c195.model.Appointment;
import com.c195.utility.DBAppointment;
import com.c195.utility.DBLogin;
import com.c195.utility.JDBC;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 * This class controls the Login form and FXML file.
 */
public class LoginController implements Initializable{

    public TextField loginUserNameTextField;
    public TextField loginPasswordTextField;
    public Button loginButton;
    public Label loginUserNameLabel;
    public Label loginPasswordLabel;
    public Label loginUserLocationLabel;
    public Label languageLabel;
    public Button exitButton;
    Stage stage;
    Parent scene;

    /**
     * Validates user input and checks for appointments within 15 minutes after validated login on button action.
     * Writes user login attempt to login_activity.txt
     *
     * @param actionEvent
     * @throws IOException
     * @throws SQLException
     */
    public void OnActionLoginButton(ActionEvent actionEvent) throws IOException, SQLException {
        try {


            ObservableList<Appointment> AllAppointmentsList = DBAppointment.getAllAppointments();
            LocalDateTime curTimeMinus15Min = LocalDateTime.now().minusMinutes(15);
            LocalDateTime curTimePlus15Min = LocalDateTime.now().plusMinutes(15);
            LocalDateTime startTime;
            int getApptID = 0;
            LocalDateTime timeDisplay = null;
            boolean apptWithin15Min = false;

            ResourceBundle rb = ResourceBundle.getBundle("com/c195/language/login", Locale.getDefault());

            String userNameInput = loginUserNameTextField.getText();
            String passwordInput = loginPasswordTextField.getText();

            int userID = DBLogin.loginCheck(userNameInput, passwordInput);

            FileWriter fileWriter = new FileWriter("login_activity.txt", true);
            PrintWriter outputFile = new PrintWriter(fileWriter);

            if (userID > 0) {
                stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
                scene = FXMLLoader.load(getClass().getResource("/com/c195/MainMenu.fxml"));
                stage.setScene(new Scene(scene));
                stage.show();

                outputFile.print(userNameInput + " logged in at: " + Timestamp.valueOf(LocalDateTime.now()) + "\n");
                System.out.println("login successful");

                for (Appointment appointment : AllAppointmentsList) {
                    startTime = appointment.getStart();
                    if ((startTime.isAfter(curTimeMinus15Min) || startTime.isEqual(curTimeMinus15Min)) && (startTime.isBefore(curTimePlus15Min) || startTime.isEqual(curTimePlus15Min))) {
                        getApptID = appointment.getAppointmentId();
                        timeDisplay = startTime;
                        apptWithin15Min = true;
                    }
                }
                if (apptWithin15Min == true) {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "Appointment " + getApptID + " with start time of " + timeDisplay + " begins within 15 minutes!");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING, "No upcoming appointments within 15 minutes!");
                    alert.showAndWait();
                }
            } else if (userID < 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle(rb.getString("Error"));
                alert.setContentText(rb.getString("Incorrect"));
                alert.show();

                outputFile.print(userNameInput + " failed to login at: " + Timestamp.valueOf(LocalDateTime.now()) + "\n");
                System.out.println("login failed");
            }

            outputFile.close();
        } catch (RuntimeException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.WARNING, "Input Username and Password!");
            alert.showAndWait();
        }
    }

    /**
     * Closes database connection and program on button action.
     *
     * @param actionEvent
     */
    public void onActionExitButton(ActionEvent actionEvent) {
        JDBC.closeConnection();
        System.exit(0);
    }


    /**
     * Initializes class and sets form for initialization state.
     * Sets and uses locale to implement language resources.
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try
        {

            Locale locale = Locale.getDefault();
            Locale.setDefault(locale);

            ZoneId zone = ZoneId.systemDefault();

            loginUserLocationLabel.setText(String.valueOf(zone));

            ResourceBundle rb = ResourceBundle.getBundle("com/c195/language/login", Locale.getDefault());
            loginUserNameLabel.setText(rb.getString("User"));
            loginPasswordLabel.setText(rb.getString("Password"));

            loginButton.setText(rb.getString("Login"));
            exitButton.setText(rb.getString("Exit"));
            languageLabel.setText(rb.getString("Language"));


        } catch(MissingResourceException e) {
            System.out.println("Resource file missing: " + e);
        } catch (Exception e)
        {
            System.out.println(e);
        }
    }

}


