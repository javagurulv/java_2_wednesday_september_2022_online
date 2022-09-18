package lv.javaguru.java2.rentapp.services;

import lv.javaguru.java2.rentapp.database.Database;

public class DeleteVehicleByPlateNumberService {

    Database database;

    public DeleteVehicleByPlateNumberService(Database database) {
        this.database = database;
    }

    public void execute(String plateNumber) {
        database.deleteVehicleByPlateNumber(plateNumber);
    }

    public Database getDatabase() {
        return database;
    }
}
