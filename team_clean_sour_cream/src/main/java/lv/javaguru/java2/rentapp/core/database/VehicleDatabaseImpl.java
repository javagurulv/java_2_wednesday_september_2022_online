package lv.javaguru.java2.rentapp.core.database;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lv.javaguru.java2.rentapp.core.services.search_criterias.SearchCriteria;
import lv.javaguru.java2.rentapp.domain.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@Setter
@EqualsAndHashCode
public class VehicleDatabaseImpl implements VehicleDatabase {

    private Long nextId = 1L;
    private List<Vehicle> vehiclesDB = new ArrayList<>();

    @Override
    public void addNewVehicle(Vehicle vehicle) {
        vehicle.setId(nextId);
        vehiclesDB.add(vehicle);
        nextId++;
    }

    @Override
    public void deleteVehicleByPlateNumber(String plateNumber) {
       vehiclesDB.removeIf(vehicle -> vehicle.getPlateNumber().equals(plateNumber));
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return new ArrayList<>(vehiclesDB);
    }

    @Override
    public List<Vehicle> search(SearchCriteria searchCriteria) {
        return vehiclesDB.stream().filter(searchCriteria).collect(Collectors.toList());
    }

    @Override
    public Vehicle getById(Long id) {
        Optional<Vehicle> vehicles = vehiclesDB.stream().filter(vehicle -> vehicle.getId().equals(id)).findFirst();
        return vehicles.get();
    }


}
