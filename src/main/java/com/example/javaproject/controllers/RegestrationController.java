package com.example.javaproject.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.javaproject.Application;
import com.example.javaproject.DatabaseHandler;
import com.example.javaproject.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegestrationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button LoginButton;

    @FXML
    private ChoiceBox<?> account_type;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField password_field;

    @FXML
    private PasswordField repeat_password;

    @FXML
    private Button toSignPage;

    @FXML
    void initialize() {
        toSignPage.setOnAction(event -> {
            toSignPage.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Application.class.getResource("hello-view.fxml"));

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

        LoginButton.setOnAction(event -> {
            signUpNewUser();
        });
    }

    private void signUpNewUser() {
        DatabaseHandler dbHandler = new DatabaseHandler();
        String username = login_field.getText().trim();
        String password = password_field.getText().trim();
        String accountType = (String) account_type.getValue();
        int newId = dbHandler.getLastId("appointments")+1;
        User user = new User(newId,username,password,accountType);
        if(!password.equals("") && !username.equals(""))
            if (password.equals(repeat_password.getText().trim()))
                dbHandler.SignUpUser(user);
            else{

            }
    }

}