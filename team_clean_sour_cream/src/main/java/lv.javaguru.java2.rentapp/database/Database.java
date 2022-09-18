package lv.javaguru.java2.rentapp.database;

import lv.javaguru.java2.rentapp.domain.Vehicle;

import java.util.List;

public interface Database {

    void addNewVehicle(Vehicle vehicle);

    void deleteVehicleByPlateNumber(String plateNumber);

    List<Vehicle> getAllVehicles();

}
