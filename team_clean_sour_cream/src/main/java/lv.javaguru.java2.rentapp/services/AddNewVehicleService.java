package lv.javaguru.java2.rentapp.services;

import lv.javaguru.java2.rentapp.domain.Vehicle;
import lv.javaguru.java2.rentapp.database.Database;

public class AddNewVehicleService {

    Database database;

    public AddNewVehicleService(Database database) {
        this.database = database;
    }

    public void execute(Vehicle vehicle) {
        database.addNewVehicle(vehicle);
    }
}
