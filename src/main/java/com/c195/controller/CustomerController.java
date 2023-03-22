package com.c195.controller;

import com.c195.model.Country;
import com.c195.model.Customer;
import com.c195.model.FirstLevelDivision;
import com.c195.utility.DBAppointment;
import com.c195.utility.DBCountry;
import com.c195.utility.DBCustomer;
import com.c195.utility.DBFLDivision;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

/**
 * This class controls the Customer form and FXML file.
 */
public class CustomerController implements Initializable {

    Stage stage;
    Parent scene;
    public Button exitButton;
    public TableView<Customer> customerTable;
    public TableColumn<?, ?> custIDCol;
    public TableColumn<?, ?> custNameCol;
    public TableColumn<?, ?> addressCol;
    public TableColumn<?, ?> postalCodeCol;
    public TableColumn<?, ?> divisionCol;
    public TableColumn<?, ?> phoneCol;
    public Button addButton;
    public Button deleteButton;
    public Button saveButton;
    public TextField custNameText;
    public TextField custAddressText;
    public TextField custPostalCodeText;
    public TextField custPhoneText;
    public ComboBox<Country> custCountryComboBox;
    public ComboBox<FirstLevelDivision> custDivisionComboBox;
    public TextField custIDText;
    boolean setByProgram = false;

    /**
     * Exits the Customer form and takes user back to Main Menu form on button action.
     *
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
     * Updates database with modified customer data on button action.
     *
     * @param actionEvent
     * @throws SQLException
     */
    public void onActionSaveButton(ActionEvent actionEvent) throws SQLException {
        if (custNameText.getText().isEmpty() || custAddressText.getText().isEmpty() || custPostalCodeText.getText().isEmpty() || custPhoneText.getText().isEmpty() || custCountryComboBox.getValue() == null
                || custDivisionComboBox.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please select customer to update!");
            alert.showAndWait();
            return;
        }
        int custID = Integer.parseInt(custIDText.getText());
        String custName = custNameText.getText();
        String custAddress = custAddressText.getText();
        String custPostalCode = custPostalCodeText.getText();
        String custPhone = custPhoneText.getText();
        int divID = custDivisionComboBox.getSelectionModel().getSelectedItem().getFirstLevelDivisionId();

        DBCustomer.update(custID, custName, custAddress, custPostalCode, custPhone, divID);
        ObservableList<Customer> updatedCustomerList =  DBCustomer.getAllCustomers();
        customerTable.setItems(updatedCustomerList);
    }

    /**
     * Takes user to Add Customer form on button action.
     *
     * @param actionEvent
     * @throws IOException
     */
    public void onActionAddButton(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/com/c195/AddCustomer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    /**
     * Deletes customer from database on button action.
     *
     * @param actionEvent
     * @throws SQLException
     */
    public void onActionDeleteButton(ActionEvent actionEvent) throws SQLException {
        Customer selectedCustomer = customerTable.getSelectionModel().getSelectedItem();

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
                customerTable.setItems(DBCustomer.getAllCustomers());
                onActionClearButton(null);
            }
        }
    }

    /**
     * Clears text fields and combo boxes of data on button action.
     *
     * @param actionEvent
     */
    public void onActionClearButton(ActionEvent actionEvent) throws SQLException {
        custIDText.clear();
        custNameText.clear();
        custAddressText.clear();
        custPostalCodeText.clear();
        custPhoneText.clear();
        custDivisionComboBox.getSelectionModel().clearSelection();
        custCountryComboBox.getSelectionModel().clearSelection();
    }

    /**
     * Sets Division Combo box based on Country selected in Country combo box.
     *
     * @param actionEvent
     * @throws SQLException
     */
    public void onActionCountryComboBox(ActionEvent actionEvent) throws SQLException {
        if (setByProgram == true){
            return;
        } else {
            Country selectedCountry = custCountryComboBox.getSelectionModel().getSelectedItem();
            ObservableList<FirstLevelDivision> divByCountryList = DBFLDivision.getDivListByCountry(selectedCountry.getCountryId());
            custDivisionComboBox.setItems(divByCountryList);
        }
    }

    /**
     * Initializes class and sets form for initialization state.
     * Implements listener on table to set fields and combo boxes.
     * Lambda #4 - uses lambda in listener method to set up event handling.
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<Customer> customers;
        ObservableList<Country> countries;
        ObservableList<FirstLevelDivision> divisions;

        try {
            countries = DBCountry.getAllCountries();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            divisions = DBFLDivision.getAllDivisions();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            customers = DBCustomer.getAllCustomers();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        customerTable.setItems(customers);
        custIDCol.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        custNameCol.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        addressCol.setCellValueFactory(new PropertyValueFactory<>("customerAddress"));
        postalCodeCol.setCellValueFactory(new PropertyValueFactory<>("customerPostalCode"));
        phoneCol.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
        divisionCol.setCellValueFactory(new PropertyValueFactory<>("customerDivisionId"));

        custCountryComboBox.setItems(countries);
        custCountryComboBox.setVisibleRowCount(5);
        custCountryComboBox.setPromptText("Choose Country");
        custDivisionComboBox.setItems(divisions);
        custDivisionComboBox.setVisibleRowCount(5);
        custDivisionComboBox.setPromptText("Choose Division");

        //lambda #4
        customerTable.getSelectionModel().selectedItemProperty().addListener((obs, oldCustomer, newCustomer) -> {
            if (newCustomer != null) {
                Customer selectedCustomer = newCustomer;
                custIDText.setText((String.valueOf(selectedCustomer.getCustomerId())));
                custNameText.setText(selectedCustomer.getCustomerName());
                custAddressText.setText(selectedCustomer.getCustomerAddress());
                custPostalCodeText.setText(selectedCustomer.getCustomerPostalCode());
                custPhoneText.setText(selectedCustomer.getCustomerPhone());
                custDivisionComboBox.setItems(divisions);

                for (int i = 0; i < divisions.size(); i++) {
                    setByProgram = true;
                    FirstLevelDivision potentialDivision = divisions.get(i);
                    if (potentialDivision.getFirstLevelDivisionId() == selectedCustomer.getCustomerDivisionId()) {
                        Country selectCountry;
                        try {
                            selectCountry = DBCountry.getCountry(potentialDivision.getFLDivCountryId());
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                        custCountryComboBox.setValue(selectCountry);
                        custDivisionComboBox.setValue(potentialDivision);
                        break;
                    }
                }
                setByProgram = false;
            }
        });
    }

}