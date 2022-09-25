package lv.javaguru.java2.rentapp.core.services;

import lv.javaguru.java2.rentapp.core.responses.ShowAllVehiclesResponse;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import lv.javaguru.java2.rentapp.core.database.Database;

import java.util.List;

public class ShowAllVehiclesService {

    Database database;

    public ShowAllVehiclesService(Database database) {
        this.database = database;
    }

    public ShowAllVehiclesResponse execute() {
        List<Vehicle> vehicles = database.getAllVehicles();
        return new ShowAllVehiclesResponse(vehicles);
    }
}
