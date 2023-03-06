package com.c195.controller;

import com.c195.model.Appointment;
import com.c195.model.Customer;
import com.c195.utility.DBAppointment;
import com.c195.utility.DBCustomer;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {


    Stage stage;
    Parent scene;

    public TableView<Appointment> apptTable;
    public TableColumn<?, ?> apptIDCol;
    public TableColumn<?, ?> apptTitleCol;
    public TableColumn<?, ?> apptDescriptionCol;
    public TableColumn<?, ?> apptLocationCol;
    public TableColumn<?, ?> apptTypeCol;
    public TableColumn<?, ?> apptStartCol;
    public TableColumn<?, ?> apptEndCol;
    public TableColumn<?, ?> apptCustIDCol;
    public TableColumn<?, ?> apptUserIDCol;
    public TableColumn<?, ?> apptContactIDCol;
    public Button apptAddButton;
    public Button apptUpdateButton;
    public Button apptDeleteButton;
    public RadioButton apptWeekRadioB;
    public RadioButton apptMonthRadioB;
    public RadioButton apptAllRadioB;
    public TableView<Customer> custTable;
    public TableColumn<?, ?> custIDCol;
    public TableColumn<?, ?> custNameCol;
    public TableColumn<?, ?> custAddressCol;
    public TableColumn<?, ?> custPostalCodeCol;
    public TableColumn<?, ?> custDivisionCol;
    public TableColumn<?, ?> custCountryCol;
    public TableColumn<?, ?> custPhoneCol;
    public Button customersAddButton;
    public Button customersUpdateButton;
    public Button custDeleteButton;
    public Button reportsButton;
    public Button logoutButton;


   

    public void onActionCustomersAddButton(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/com/c195/AddCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionCustomersUpdateButton(ActionEvent actionEvent) throws IOException {

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/com/c195/Customer.fxml"));
            loader.load();

            CustomerController custController = loader.getController();
            custController.sendCustomer(custTable.getSelectionModel().getSelectedIndex(), custTable.getSelectionModel().getSelectedItem());

            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            Parent scene = loader.getRoot();
            stage.setScene(new Scene(scene));
            stage.show();
        } catch (NullPointerException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Please select customer to update!");
            alert.showAndWait();
        }
    }

    public void onActionReportsButton(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/com/c195/Report.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionApptAddButton(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/com/c195/AddAppointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionApptUpdateButton(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/com/c195/Appointment.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionApptDeleteButton(ActionEvent actionEvent) throws SQLException {

        Appointment selectedAppt = apptTable.getSelectionModel().getSelectedItem();

        if (selectedAppt == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Select appointment to delete!");
            alert.showAndWait();
            return;
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will delete appointment.  Do you want to continue?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                DBAppointment.deleteAppointment(selectedAppt.getAppointmentId());
                apptTable.setItems(DBAppointment.getAllAppointments());
            }
        }
    }


    public void onActionApptWeekRadioB(ActionEvent actionEvent) {
    }

    public void onActionMonthRadioB(ActionEvent actionEvent) {
    }

    public void onActionApptAllRadioB(ActionEvent actionEvent) {
    }

    public void onActionCustDeleteButton(ActionEvent actionEvent) {
    }

    public void onActionLogoutButton(ActionEvent actionEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){


        try {
            apptTable.setItems(DBAppointment.getAllAppointments());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        apptIDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        apptTitleCol.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        apptDescriptionCol.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        apptLocationCol.setCellValueFactory(new PropertyValueFactory<>("appointmentLocation"));
        apptTypeCol.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        apptStartCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        apptEndCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        apptCustIDCol.setCellValueFactory(new PropertyValueFactory<>("apptCustomerId"));
        apptUserIDCol.setCellValueFactory(new PropertyValueFactory<>("apptUserId"));
        apptContactIDCol.setCellValueFactory(new PropertyValueFactory<>("apptContactId"));

        try {
            custTable.setItems(DBCustomer.getAllCustomers());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        custIDCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        custNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        custAddressCol.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        custPostalCodeCol.setCellValueFactory(new PropertyValueFactory<>("customerPostalCode"));
        custPhoneCol.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
        custDivisionCol.setCellValueFactory(new PropertyValueFactory<>("customerDivisionId"));


    }
}