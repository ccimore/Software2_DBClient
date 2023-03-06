package com.c195.controller;

import com.c195.model.Customer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {



    Stage stage;
    Parent scene;
    public Button saveButton;
    public TextField custNameText;
    public TextField custAddressText;
    public TextField custPostalCodeText;
    public TextField custPhoneText;
    public ComboBox<String> custCountryComboBox;
    public ComboBox<String> custDivisionComboBox;
    public TextField custIDText;

    private int currentIndex = 0;



    public void onActionExitButton(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/com/c195/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void onActionSaveButton(ActionEvent actionEvent) {
    }

    public void sendCustomer(int selectedIndex, Customer customer){
        currentIndex = selectedIndex;

        custIDText.setText(String.valueOf(customer.getCustomerId()));
        custNameText.setText(customer.getCustomerName());
        custAddressText.setText(customer.getCustomerAddress());
        custPostalCodeText.setText(customer.getCustomerPostalCode());
        custPhoneText.setText(customer.getCustomerPhone());


    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}