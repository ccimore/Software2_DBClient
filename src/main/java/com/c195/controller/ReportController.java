package com.c195.controller;

import com.c195.model.*;
import com.c195.utility.DBAppointment;
import com.c195.utility.DBContact;
import com.c195.utility.DBReport;
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
import java.util.ResourceBundle;

public class ReportController implements Initializable {

    Stage stage;
    Parent scene;
    public TableView<Appointment> contactReportTable;
    public TableColumn<?, ?> apptIDCol;
    public TableColumn<?, ?> titleCol;
    public TableColumn<?, ?> typeCol;
    public TableColumn<?, ?> descriptionCol;
    public TableColumn<?, ?> startCol;
    public TableColumn<?, ?> endCol;
    public TableColumn<?, ?> custIDCol;
    public ComboBox<Contact> contactCombo;
    public TableColumn<?, ?> monthCol;
    public TableColumn<?, ?> typeTotalCol;
    public TableView<TypeTotal> typeTotalTable;
    public TableColumn<?, ?> totalCol;
    public TableColumn<?, ?> divisionCol;
    public TableView<DivisionTotal> divisionTable;
    public TableColumn<?, ?> divisionTotalCustCol;
    public TableView<MonthTotal> monthTotalTable;
    public TableColumn<?, ?> monthTotalCol;




    public void onActionExitButton(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/com/c195/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionContactCombo(ActionEvent actionEvent) throws SQLException {
        Contact selectedContact = contactCombo.getSelectionModel().getSelectedItem();
        ObservableList<Appointment> apptByContactList = DBAppointment.getApptByContactID(selectedContact.getContactId());
        contactReportTable.setItems(apptByContactList);
        apptIDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        custIDCol.setCellValueFactory(new PropertyValueFactory<>("apptCustomerId"));
    }




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Contact> contactsList;
        ObservableList<TypeTotal> typeTotalsList;
        ObservableList<MonthTotal> monthTotalsList;
        ObservableList<DivisionTotal> divisionTotalsList;
        ObservableList<Appointment> allAppointmentsList;

        try {
            allAppointmentsList = DBAppointment.getAllAppointments();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        contactReportTable.setItems(allAppointmentsList);
        apptIDCol.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("appointmentTitle"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("appointmentType"));
        descriptionCol.setCellValueFactory(new PropertyValueFactory<>("appointmentDescription"));
        startCol.setCellValueFactory(new PropertyValueFactory<>("start"));
        endCol.setCellValueFactory(new PropertyValueFactory<>("end"));
        custIDCol.setCellValueFactory(new PropertyValueFactory<>("apptCustomerId"));

        try {
            contactsList = DBContact.getAllContacts();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        contactCombo.setItems(contactsList);

        try {
            typeTotalsList = DBReport.getAllTypeTotals();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        typeTotalTable.setItems(typeTotalsList);
        typeTotalCol.setCellValueFactory(new PropertyValueFactory<>("type"));
        totalCol.setCellValueFactory(new PropertyValueFactory<>("total"));

        try {
            monthTotalsList = DBReport.getAllMonthTotals();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        monthTotalTable.setItems(monthTotalsList);
        monthCol.setCellValueFactory(new PropertyValueFactory<>("month"));
        monthTotalCol.setCellValueFactory(new PropertyValueFactory<>("total"));

        try {
            divisionTotalsList = DBReport.getAllDivisionTotals();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        divisionTable.setItems(divisionTotalsList);
        divisionCol.setCellValueFactory(new PropertyValueFactory<>("divisionName"));
        divisionTotalCustCol.setCellValueFactory(new PropertyValueFactory<>("total"));
    }


}