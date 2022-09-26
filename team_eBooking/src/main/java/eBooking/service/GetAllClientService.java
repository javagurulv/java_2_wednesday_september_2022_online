package eBooking.service;

import eBooking.database.Database;

public class GetAllClientService {

    private Database database;

    public GetAllClientService(Database database) {
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
