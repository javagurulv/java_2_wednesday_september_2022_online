package lv.javaguru.java2.rentapp.database;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lv.javaguru.java2.rentapp.Vehicle;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
@EqualsAndHashCode
public class InMemoryDatabaseImpl implements Database {

    List<Vehicle> vehiclesDB = new ArrayList<>();
    private Long id = 1L;
    @Override
    public void addNewVehicle(Vehicle vehicle) {
        vehicle.setId(id);
        vehiclesDB.add(vehicle);
        id++;
    }

    @Override
    public void deleteVehicleByPlateNumber(String plateNumber) {
        vehiclesDB.removeIf(vehicle -> vehicle.getPlateNumber().equals(plateNumber));
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehiclesDB;
    }

}
