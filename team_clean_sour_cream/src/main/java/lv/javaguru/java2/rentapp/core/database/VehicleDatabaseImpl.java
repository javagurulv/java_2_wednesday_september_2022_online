package lv.javaguru.java2.rentapp.core.database;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lv.javaguru.java2.rentapp.core.requests.SearchVehicleRequest;
import lv.javaguru.java2.rentapp.core.services.search_criterias.SearchCriteria;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//@Component
@Deprecated()
@Getter
@Setter
@EqualsAndHashCode
public class VehicleDatabaseImpl implements VehicleDatabase {

    private Long nextId = 1L;
    private List<Vehicle> vehiclesDB = new ArrayList<>(TestData.getTestList());

    @Override
    public Long addNewVehicle(Vehicle vehicle) {
        Long id = nextId;
        vehicle.setId(nextId);
        vehiclesDB.add(vehicle);
        nextId++;
        return id;
    }

    @Override
    public void deleteVehicleByPlateNumber(String plateNumber) {
        vehiclesDB.removeIf(vehicle -> vehicle.getPlateNumber().equals(plateNumber));
    }

    @Override
    public List<Vehicle> getAllVehicles() {
        return new ArrayList<>(vehiclesDB);
    }

//    @Override
//    public List<Vehicle> search(SearchCriteria searchCriteria) {
//        return vehiclesDB.stream().filter(searchCriteria).collect(Collectors.toList());
//    }

    @Override
    public List<Vehicle> search(SearchVehicleRequest request) {
        return null;
    }

    @Override
    public Optional<Vehicle> getById(Long id) {
        return vehiclesDB.stream()
                .filter(vehicle -> vehicle.getId().equals(id))
                .findAny();
    }
}
