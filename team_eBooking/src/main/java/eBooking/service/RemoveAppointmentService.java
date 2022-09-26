package eBooking.service;

import eBooking.database.Database;

public class RemoveAppointmentService {
    private Database database;

    public RemoveAppointmentService(Database database) {
        this.database = database;
    }

    public void execute(Long appointmentId){
        database.deleteAppointmentById(appointmentId);
    }
}
