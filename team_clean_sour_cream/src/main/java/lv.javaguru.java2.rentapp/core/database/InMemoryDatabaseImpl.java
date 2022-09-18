package lv.javaguru.java2.rentapp.core.database;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lv.javaguru.java2.rentapp.domain.Vehicle;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@EqualsAndHashCode
public class InMemoryDatabaseImpl implements Database {

    private Long nextId = 1L;
    private List<Vehicle> vehiclesDB = new ArrayList<>();

    @Override
    public void addNewVehicle(Vehicle vehicle) {
        vehicle.setId(nextId);
        vehiclesDB.add(vehicle);
        nextId++;
    }

    @Override
    public boolean deleteVehicleByPlateNumber(String plateNumber) {
        boolean isVehicleDeleted = false;
        Optional<Vehicle> vehicleToDeleteOpt = vehiclesDB.stream()
                .filter(vehicle -> vehicle.getPlateNumber().equals(plateNumber))
                .findFirst();
        if (vehicleToDeleteOpt.isPresent()) {
            Vehicle vehicleToDelete = vehicleToDeleteOpt.get();
            isVehicleDeleted = vehiclesDB.remove(vehicleToDelete);
        }
        return isVehicleDeleted;
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return vehiclesDB;
    }
}
