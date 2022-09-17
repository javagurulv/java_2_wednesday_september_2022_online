package lv.javaguru.java2.rentapp.services;

import lv.javaguru.java2.rentapp.domain.Vehicle;
import lv.javaguru.java2.rentapp.database.Database;

import java.util.List;

public class ShowAllVehiclesService {

    Database database;

    public ShowAllVehiclesService(Database database) {
        this.database = database;
    }

    public List<Vehicle> execute() {
        return database.getAllVehicles();
    }
}
