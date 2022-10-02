package lv.javaguru.java2.eBooking.core.service;

import lv.javaguru.java2.eBooking.core.database.Database;

public class RemoveAppointmentService {
    private Database database;

    public RemoveAppointmentService(Database database) {
        this.database = database;
    }

    public void execute(Long appointmentId){
        database.deleteAppointmentById(appointmentId);
    }
}
