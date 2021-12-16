package com.example.javaproject.doctorsAppointment;

import com.example.javaproject.DatabaseHandler;

import java.time.LocalDate;

public class SurgeonAppointment implements DoctorsAppoinments{
    @Override
    public void makeAppointment(LocalDate date) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        dbHandler.newAppointment(date,"Хирург");
    }
}
