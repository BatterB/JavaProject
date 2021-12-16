package com.example.javaproject.controllers;

import com.example.javaproject.doctorsAppointment.DoctorAppointmentFactory;
import com.example.javaproject.doctorsAppointment.Doctors;
import com.example.javaproject.doctorsAppointment.DoctorsAppoinments;
import com.example.javaproject.doctorsAppointment.SurgeonAppointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class AppointmentController {

    int row = 0,column=0;

    @FXML
    private Button AddNewAppoint;

    @FXML
    private Button Send;

    @FXML
    private DatePicker Date;

    @FXML
    private ComboBox Box;

    @FXML
    private GridPane GridPanel;

    @FXML
    void initialize() {
        AddNewAppoint.setOnAction(event -> {
            GridPanel.getChildren().clear();
            ObservableList<String> observableList = FXCollections.observableArrayList("Хирург", "Кардиолог", "Дантист", "Психиатр", "Дерматолог");

            Box = new ComboBox(observableList);
            Date = new DatePicker();
            Send = new Button("Записаться");

            Box.setTranslateY(-35);
            Box.setTranslateX(30);
            Send.setTranslateY(40);
            Send.setTranslateX(40);
            Send.setId("Send");

            GridPanel.add(Box, row, column);
            GridPanel.add(Date, row, column);
            GridPanel.add(Send, row, column);
            column++;
            if (column > 4) {
                column = 0;
                row++;
            }
            System.out.println(Send);
            NewAppoint();
        });
    }

    void NewAppoint(){
        Send.setOnAction(event ->{
            LocalDate calendar = Date.getValue();
            String boxValue = (String) Box.getValue();
            try{
                DoctorAppointmentFactory factory = new DoctorAppointmentFactory();
                DoctorsAppoinments doctor =null;
                if(boxValue=="Хирург") {
                    doctor = factory.createAppointment(Doctors.SURGEON);
                } else if(boxValue=="Кардиолог"){
                    doctor = factory.createAppointment(Doctors.CARDIOLOGIST);
                }else if (boxValue=="Дантист"){
                    doctor = factory.createAppointment(Doctors.DENTIST);
                }else if (boxValue =="Психиатр"){
                    doctor = factory.createAppointment(Doctors.PSYCHIATRIST);
                }else if (boxValue=="Дерматолог") {
                    doctor = factory.createAppointment(Doctors.DERMATOLOGIST);
                }
                doctor.makeAppointment(calendar);
            }
            catch (IllegalArgumentException e){
                e.printStackTrace();
            }catch (NullPointerException e){
                e.printStackTrace();
            }
            GridPanel.getChildren().clear();
            Label doctor = new Label("Врач: "+ boxValue);
            Label date = new Label("Время приема: "+ calendar);
            GridPanel.add(doctor, row, column);
            GridPanel.add(date, row, column);
            doctor.setStyle("-fx-font-size: 24px");
            date.setStyle("-fx-font-size: 12px");
    }
        );}
}
