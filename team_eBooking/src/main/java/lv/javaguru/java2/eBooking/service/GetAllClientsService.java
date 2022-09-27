package lv.javaguru.java2.eBooking.service;

import lv.javaguru.java2.eBooking.database.Database;

public class GetAllClientsService {

    private Database database;

    public GetAllClientsService(Database database) {
        this.database = database;
    }

    public void execute() {
        if (database.getAllClients().isEmpty()) {
            System.out.println("Client list is empty");
        } else {
            database.getAllClients().forEach(System.out::println);
        }
    }
}
