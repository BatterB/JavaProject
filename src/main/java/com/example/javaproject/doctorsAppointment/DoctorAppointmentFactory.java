package com.example.javaproject.doctorsAppointment;

public class DoctorAppointmentFactory {
    public DoctorsAppoinments createAppointment(Doctors doctor){
        DoctorsAppoinments toReturn = null;
        switch (doctor) {
            case DENTIST:
                toReturn = new DentistitAppointment();
                break;
            case SURGEON:
                toReturn = new SurgeonAppointment();
                break;
            case CARDIOLOGIST:
                toReturn = new CardiologistAppointment();
                break;
            case PSYCHIATRIST:
                toReturn = new PsychiatristAppointment();
                break;
            case DERMATOLOGIST:
                toReturn = new DermatologistAppointment();
                break;
            default:
                throw new IllegalArgumentException("Wrong doughnut type:" + doctor);
        }
        return toReturn;
    }
}
