package com.example.javaproject.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.javaproject.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainWindowController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button appointments;

    @FXML
    void initialize() {
        appointments.setOnAction(event -> {
            appointments.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Application.class.getResource("appointment.fxml"));

            try{
                loader.load();
            } catch (IOException e){
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });
    }
}
