package com.example.javaproject.controllers;

import com.example.javaproject.Application;
import com.example.javaproject.DatabaseHandler;
import com.example.javaproject.User;
import com.example.javaproject.animations.Shake;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button LoginButton;

    @FXML
    private Button SignUpButton;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    void initialize() {
        LoginButton.setOnAction(event -> {
            String loginText = login_field.getText().trim();
            String passwordText = password_field.getText().trim();

            if(!loginText.equals("") && !passwordText.equals("")){
                loginUser(loginText,passwordText);
            }
        });

        SignUpButton.setOnAction(event -> {
            SignUpButton.getScene().getWindow().hide();
            ChangeWindow("registration-window.fxml");
        });
    }
    private void ChangeWindow(String name){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Application.class.getResource(name));

        try{
            loader.load();
        } catch (IOException e){
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.showAndWait();
    }
    private void loginUser(String loginText, String passwordText) {
        DatabaseHandler dbHandler = new DatabaseHandler();

        User user = new User();
        user.setUsername(loginText);
        user.setPassword(passwordText);
        ResultSet result = dbHandler.getUser(user);



        try {
            int counter = 0;

            while (result.next()) {
                counter++;
            }

            if (counter>=1){
                LoginButton.getScene().getWindow().hide();
                ChangeWindow("main-window.fxml");
            }
            else{
                Shake userLoginAnim = new Shake(login_field);
                Shake passLoginAnim = new Shake(password_field);
                userLoginAnim.playAnim();
                passLoginAnim.playAnim();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        }

    }
