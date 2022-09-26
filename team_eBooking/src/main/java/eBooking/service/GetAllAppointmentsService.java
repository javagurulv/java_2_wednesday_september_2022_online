package eBooking.service;

import eBooking.database.Database;

public class GetAllAppointmentsService {
    private Database database;

    public GetAllAppointmentsService(Database database) {
        this.database = database;
    }

    public void execute(){
        if (database.getAllAppointments().isEmpty()) {
            System.out.println("List is empty");
        } else {
            database.getAllClients().forEach(System.out::println);
        }
    }
}
