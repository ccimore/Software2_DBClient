package com.c195.controller;

import com.c195.model.Country;
import com.c195.model.FirstLevelDivision;
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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddCustomerController implements Initializable {

    public Button exitButton;
    public Button saveButton;
    public TextField customerIDText;
    public TextField customerNameText;
    public TextField addressText;
    public TextField postalCodeText;
    public TextField phoneNumberText;
    public ComboBox<Country> countryCombo;
    public ComboBox<FirstLevelDivision> divisionCombo;
    Stage stage;
    Parent scene;




    public void onActionExitButton(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/com/c195/Customer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionSaveButton(ActionEvent actionEvent) throws SQLException, IOException {

        if (customerNameText.getText().isEmpty() || addressText.getText().isEmpty() || postalCodeText.getText().isEmpty() || phoneNumberText.getText().isEmpty() || countryCombo.getValue() == null
                || divisionCombo.getValue() == null){
            Alert alert = new Alert(Alert.AlertType.WARNING, "Please fill out all fields!");
            alert.showAndWait();
            return;
        }
        String name = customerNameText.getText();
        String address = addressText.getText();
        String postalCode = postalCodeText.getText();
        String phoneNumber = phoneNumberText.getText();
        FirstLevelDivision division = (FirstLevelDivision) divisionCombo.getValue();
        int rowsAffected = DBCustomer.insert(name, address, postalCode, phoneNumber, division.getFirstLevelDivisionId());
        if(rowsAffected > 0){
            System.out.println("Insert Successful!");
        } else {
            System.out.println("Insert Failed!");
        }
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/com/c195/Customer.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionCountryCombo(ActionEvent actionEvent) throws SQLException {

        Country selectedCountry = countryCombo.getSelectionModel().getSelectedItem();
        ObservableList<FirstLevelDivision> divByCountryList = DBFLDivision.getDivListByCountry(selectedCountry.getCountryId());
        divisionCombo.setItems(divByCountryList);

         
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

        countryCombo.setItems(countries);
        divisionCombo.setItems(divisions);

    }


}