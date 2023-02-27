package com.c195;

import com.c195.helper.FruitsQuery;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.c195.helper.JDBC;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        stage.setTitle("Hello!");
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

        /*

        int rowsAffected = FruitsQuery.delete(7);

        if(rowsAffected > 0){
            System.out.println("Delete Successful!");
        } else {
            System.out.println("Delete Failed!");
        }

         */


        System.out.println("Hello world!");

        launch();

        JDBC.closeConnection();
    }
}