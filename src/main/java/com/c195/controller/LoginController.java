package com.c195.controller;

import com.c195.utility.JDBC;
import javafx.event.ActionEvent;
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

public class LoginController implements Initializable{

    public TextField loginUserNameTextField;
    public TextField loginPasswordTextField;
    public Button loginButton;
    public ComboBox languageComboBox;
    public Label loginUserNameLabel;
    public Label loginPasswordLabel;
    public Label loginLocationLabel;
    public Label loginUserLocationLabel;
    public Label languageLabel;
    public Button exitButton;
    Stage stage;
    Parent scene;

    public void OnActionLoginUserNameText(ActionEvent actionEvent) {
    }

    public void OnActionLoginPasswordText(ActionEvent actionEvent) {
    }

    public void OnActionLoginButton(ActionEvent actionEvent) throws IOException {
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/com/c195/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void onActionExitButton(ActionEvent actionEvent) {
        JDBC.closeConnection();
        System.exit(0);
    }
}