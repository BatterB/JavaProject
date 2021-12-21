package com.example.javaproject.controllers;

import com.example.javaproject.doctorsAppointment.DoctorAppointmentFactory;
import com.example.javaproject.doctorsAppointment.Doctors;
import com.example.javaproject.doctorsAppointment.DoctorsAppoinments;
import com.example.javaproject.doctorsAppointment.SurgeonAppointment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
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
        AddNewAppoint.setOnAction(event -> AppointBlank());
    }

    void AppointBlank()
    {
        System.out.println(GridPanel.getChildren());
        AddNewAppoint.setVisible(false);
        ObservableList<String> observableList = FXCollections.observableArrayList("Хирург", "Кардиолог", "Дантист", "Психиатр", "Дерматолог");

        Box = new ComboBox(observableList);
        Date = new DatePicker();
        Send = new Button("Записаться");

        Box.setTranslateY(10);
        Box.setTranslateX(40);
        Date.setTranslateY(45);
        Send.setTranslateY(80);
        Send.setTranslateX(40);
        Send.setId("Send");

        AnchorPane pane = new AnchorPane();

        pane.getChildren().add(Box);
        pane.getChildren().add(Date);
        pane.getChildren().add(Send);
        GridPanel.add(pane, row, column);

        NewAppoint(pane);
    }

    void NewAppoint(AnchorPane pane){
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

            pane.getChildren().clear();
            Label doctor = new Label("Врач: "+ boxValue);
            Label date = new Label("Время приема: "+ calendar);
            pane.getChildren().add(doctor);
            pane.getChildren().add(date);
            doctor.setTranslateY(-5);
            doctor.setStyle("-fx-font-size: 24px; -fx-text-fill: white");
            date.setTranslateY(40);
            date.setStyle("-fx-font-size: 12px; -fx-text-fill: white");
            column++;
            if (column > 4) {
                column = 0;
                row++;
            }
            AddNewAppoint = new Button("Добавить запись");
            GridPanel.add(AddNewAppoint, row, column);
            AddNewAppoint.setOnAction(event1->AppointBlank());
            AddNewAppoint.setVisible(true);
    }
        );}
}
