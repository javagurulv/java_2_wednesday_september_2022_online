package lv.javaguru.java2.rentapp.core.database;

import lv.javaguru.java2.rentapp.core.requests.SearchVehicleRequest;
import lv.javaguru.java2.rentapp.core.services.search_criterias.SearchCriteria;
import lv.javaguru.java2.rentapp.domain.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleDatabase {

    Long addNewVehicle(Vehicle vehicle);

    void deleteVehicleByPlateNumber(String plateNumber);

    List<Vehicle> getAllVehicles();

    //  List<Vehicle> search(SearchCriteria searchCriteria);
    List<Vehicle> search(SearchVehicleRequest request);

    Optional<Vehicle> getById(Long id);

}
