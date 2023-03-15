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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {


    public ToggleGroup Tgroup;
    public Button customersButton;
    public TextField apptIDTextField;
    public TextField apptTitleTextField;
    public TextField apptDescriptionTextField;
    public TextField apptLocationTextField;
    public TextField apptTypeTextField;
    public ComboBox<Contact> apptContactCombo;
    public ComboBox<Customer> apptCustomerCombo;
    public ComboBox<User> apptUserCombo;
    public ComboBox<LocalTime> apptEndTimeCombo;
    public ComboBox<LocalTime> apptStartTimeCombo;
    public DatePicker apptStartDatePicker;
    public DatePicker apptEndDatePicker;
    public Button clearButton;
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

        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/com/c195/Customer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

        /*

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

         */
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

    public void onActionApptUpdateButton(ActionEvent actionEvent) throws IOException, SQLException {
        if(apptTitleTextField.getText().isEmpty() || apptDescriptionTextField.getText().isEmpty() || apptLocationTextField.getText().isEmpty() || apptTypeTextField.getText().isEmpty()
                || apptStartDatePicker.getChronology() == null || apptStartTimeCombo.getValue() == null || apptEndDatePicker.getChronology() == null
                || apptEndTimeCombo.getValue() == null || apptCustomerCombo.getValue() == null || apptUserCombo.getValue() == null || apptContactCombo.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please fill out all fields!");
            alert.showAndWait();
            return;
        }
        LocalDate apptDateStart = apptStartDatePicker.getValue();
        LocalDate apptDateEnd = apptEndDatePicker.getValue();
        LocalTime apptTimeStart = apptStartTimeCombo.getValue();
        LocalTime apptTimeEnd = apptEndTimeCombo.getValue();

        int apptID = Integer.parseInt(apptIDTextField.getText());
        String title = apptTitleTextField.getText();
        String description = apptDescriptionTextField.getText();
        String location = apptLocationTextField.getText();
        String type = apptTypeTextField.getText();
        Contact contact = apptContactCombo.getValue();
        Customer customer = apptCustomerCombo.getValue();
        User user = apptUserCombo.getValue();
        LocalDateTime startDateTime = LocalDateTime.of(apptDateStart, apptTimeStart);
        LocalDateTime endDateTime = LocalDateTime.of(apptDateEnd, apptTimeEnd);
        LocalDateTime startDateTimeUTC = TimeHelper.convertToUTC(startDateTime);
        LocalDateTime endDateTimeUTC = TimeHelper.convertToUTC(endDateTime);

        DBAppointment.update(apptID, title, description, location, type, startDateTimeUTC, endDateTimeUTC, customer.getCustomerId(), user.getUserId(), contact.getContactId());
        ObservableList<Appointment> updatedApptList = DBAppointment.getAllAppointments();
        apptTable.setItems(updatedApptList);


    }

    public void onActionApptDeleteButton(ActionEvent actionEvent) throws SQLException {

        Appointment selectedAppt = apptTable.getSelectionModel().getSelectedItem();
        int deleteApptID = selectedAppt.getAppointmentId();
        String deleteApptType = selectedAppt.getAppointmentType();

        if (selectedAppt == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Select appointment to delete!");
            alert.showAndWait();
            return;
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will delete appointment " + deleteApptID + " with appointment type " + deleteApptType + ".  Do you want to continue?");
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                DBAppointment.deleteAppointment(selectedAppt.getAppointmentId());
                apptTable.setItems(DBAppointment.getAllAppointments());
            }
        }
    }


    public void onActionApptWeekRadioB(ActionEvent actionEvent) {
        try {
            ObservableList<Appointment> allApptList = DBAppointment.getAllAppointments();
            ObservableList<Appointment> weekApptList = FXCollections.observableArrayList();

            LocalDateTime startCurrentWeek = LocalDateTime.now().minusWeeks(1);
            LocalDateTime endCurrentWeek = LocalDateTime.now().plusWeeks(1);

            if (allApptList != null)
                allApptList.forEach(appointment -> {
                    if (appointment.getStart().isAfter(startCurrentWeek) && appointment.getEnd().isBefore(endCurrentWeek)){
                        weekApptList.add(appointment);
                    }
                    apptTable.setItems(weekApptList);
                });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void onActionMonthRadioB(ActionEvent actionEvent) {
        try {
            ObservableList<Appointment> allApptList = DBAppointment.getAllAppointments();
            ObservableList<Appointment> monthApptList = FXCollections.observableArrayList();

            LocalDateTime startCurrentMonth = LocalDateTime.now().minusMonths(1);
            LocalDateTime endCurrentMonth = LocalDateTime.now().plusMonths(1);

            if (allApptList != null)
                allApptList.forEach(appointment -> {
                    if (appointment.getStart().isAfter(startCurrentMonth) && appointment.getEnd().isBefore(endCurrentMonth)){
                        monthApptList.add(appointment);
                    }
                    apptTable.setItems(monthApptList);
                });
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void onActionApptAllRadioB(ActionEvent actionEvent) {
        try {
            ObservableList<Appointment> allApptList = DBAppointment.getAllAppointments();

            if (allApptList != null){
                apptTable.setItems(allApptList);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void onActionCustDeleteButton(ActionEvent actionEvent) throws SQLException {
/*

        Customer selectedCustomer = custTable.getSelectionModel().getSelectedItem();

        if (selectedCustomer == null) {
            Alert noCustomer = new Alert(Alert.AlertType.ERROR, "Please select customer to delete!");
            noCustomer.showAndWait();
            return;
        }
        if (selectedCustomer != null) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will delete customer record.  Are you sure you want to continue?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                DBAppointment.deleteAppointmentByCustID(selectedCustomer.getCustomerId());
                DBCustomer.delete(selectedCustomer.getCustomerId());
                apptTable.setItems(DBAppointment.getAllAppointments());
                custTable.setItems(DBCustomer.getAllCustomers());
            }

        }


        //SELECT X,Y,Z FROM CUSTOMER TABLE JOIN DIVISION TABLE ON CUSTOMER TABLE DIVISION ID = DIVISION TABLE DIVISION ID;


 */

    }

    public void onActionLogoutButton(ActionEvent actionEvent) {
        JDBC.closeConnection();
        System.exit(0);
    }

    public void onActionCustomersButton(ActionEvent actionEvent) throws IOException {

        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/com/c195/Customer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }


    public void onActionClearButton(ActionEvent actionEvent) {
        apptIDTextField.clear();
        apptTitleTextField.clear();
        apptDescriptionTextField.clear();
        apptLocationTextField.clear();
        apptTypeTextField.clear();
        apptUserCombo.getSelectionModel().clearSelection();
        apptCustomerCombo.getSelectionModel().clearSelection();
        apptContactCombo.getSelectionModel().clearSelection();
        apptStartTimeCombo.getSelectionModel().clearSelection();
        apptEndTimeCombo.getSelectionModel().clearSelection();
        apptStartDatePicker.setValue(null);
        apptEndDatePicker.setValue(null);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle){

        ObservableList<Contact> contacts;
        ObservableList<Customer> customers;
        ObservableList<User> users;
        ObservableList<LocalTime> apptTimeList = FXCollections.observableArrayList();

        LocalTime startTimeAppt = LocalTime.of(8, 0);
        LocalTime endTimeAppt = LocalTime.of(22, 0);

        while (startTimeAppt.isBefore(endTimeAppt.plusSeconds(1))) {
            apptTimeList.add(startTimeAppt);
            startTimeAppt = startTimeAppt.plusMinutes(15);


        /*
        LocalTime startTimeAppt = LocalTime.MIN.plusHours(8);
        LocalTime endTimeAppt = LocalTime.MAX.minusHours(1).minusMinutes(45);

        if (!startTimeAppt.equals(0) || !endTimeAppt.equals(0)){
            while (startTimeAppt.isBefore(endTimeAppt)) {
                apptTimeList.add(startTimeAppt);
                startTimeAppt = startTimeAppt.plusMinutes(15);
            }
            */
        }

        apptStartTimeCombo.setItems(apptTimeList);
        apptStartTimeCombo.setPromptText("Choose Start Time");

        apptEndTimeCombo.setItems(apptTimeList);
        apptEndTimeCombo.setPromptText("Choose End Time");


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

        apptUserCombo.setItems(users);
        apptUserCombo.setVisibleRowCount(5);
        apptUserCombo.setPromptText("Choose User");
        apptCustomerCombo.setItems(customers);
        apptCustomerCombo.setVisibleRowCount(5);
        apptCustomerCombo.setPromptText("Choose Customer");
        apptContactCombo.setItems(contacts);
        apptContactCombo.setVisibleRowCount(5);
        apptContactCombo.setPromptText("Choose Contact");



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

        apptTable.getSelectionModel().selectedItemProperty().addListener((obs, oldAppointment, newAppointment) -> {
            if (newAppointment != null) {
                Appointment selectedAppt = newAppointment;
                LocalDateTime startLocalDateTime = TimeHelper.convertUTCToLocal(selectedAppt.getStart());
                LocalDateTime endLocalDateTime = TimeHelper.convertUTCToLocal(selectedAppt.getEnd());

                apptIDTextField.setText(String.valueOf(selectedAppt.getAppointmentId()));
                apptTitleTextField.setText(selectedAppt.getAppointmentTitle());
                apptDescriptionTextField.setText(selectedAppt.getAppointmentDescription());
                apptLocationTextField.setText(selectedAppt.getAppointmentLocation());
                apptTypeTextField.setText(selectedAppt.getAppointmentType());
                apptStartDatePicker.setValue(startLocalDateTime.toLocalDate());
                apptStartTimeCombo.setValue(startLocalDateTime.toLocalTime());
                apptEndDatePicker.setValue(endLocalDateTime.toLocalDate());
                apptEndTimeCombo.setValue(endLocalDateTime.toLocalTime());

                for(int i = 0; i < apptContactCombo.getItems().size(); i++){
                    Contact potentialContact = apptContactCombo.getItems().get(i);
                    if(potentialContact.getContactId() == selectedAppt.getApptContactId()){
                        apptContactCombo.setValue(potentialContact);
                        break;
                    }
                }

                for(int i = 0; i < apptCustomerCombo.getItems().size(); i++){
                    Customer potentialCustomer = apptCustomerCombo.getItems().get(i);
                    if(potentialCustomer.getCustomerId() == selectedAppt.getApptCustomerId()){
                        apptCustomerCombo.setValue(potentialCustomer);
                        break;
                    }
                }

                for(int i = 0; i < apptUserCombo.getItems().size(); i++){
                    User potentialUser = apptUserCombo.getItems().get(i);
                    if(potentialUser.getUserId() == selectedAppt.getApptUserId()){
                        apptUserCombo.setValue(potentialUser);
                        break;
                    }
                }



            }
        });




/*


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


 */

    }



}