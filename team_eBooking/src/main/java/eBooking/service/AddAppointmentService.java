package eBooking.service;

import eBooking.Appointment;
import eBooking.database.Database;

public class AddAppointmentService {

    private Database database;

    public AddAppointmentService(Database database) {
        this.database = database;
    }
    public void execute(String masterName,String typeOfService){
        database.saveAppointment(new Appointment(masterName, typeOfService));
    }
}
