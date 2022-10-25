package lv.javaguru.java2.rentapp.core.services;

import lv.javaguru.java2.rentapp.core.database.VehicleDatabase;
import lv.javaguru.java2.rentapp.core.responses.ShowAllVehiclesResponse;
import lv.javaguru.java2.rentapp.domain.Vehicle;

import java.util.List;

public class ShowAllVehiclesService {

    VehicleDatabase vehicleDatabase;

    public ShowAllVehiclesService(VehicleDatabase vehicleDatabase) {
        this.vehicleDatabase = vehicleDatabase;
    }

    public ShowAllVehiclesResponse execute() {
        List<Vehicle> vehicles = vehicleDatabase.getAllVehicles();
        return new ShowAllVehiclesResponse(vehicles);
    }
}
