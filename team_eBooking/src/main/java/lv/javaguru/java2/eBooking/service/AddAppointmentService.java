package lv.javaguru.java2.eBooking.service;

import lv.javaguru.java2.eBooking.Appointment;
import lv.javaguru.java2.eBooking.database.Database;

public class AddAppointmentService {

    private Database database;

    public AddAppointmentService(Database database) {
        this.database = database;
    }
    public void execute(String masterName,String typeOfService){
        database.saveAppointment(new Appointment(masterName, typeOfService));
    }
}
