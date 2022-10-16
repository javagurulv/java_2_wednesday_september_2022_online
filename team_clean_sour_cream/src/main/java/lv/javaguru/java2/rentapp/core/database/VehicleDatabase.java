package lv.javaguru.java2.rentapp.core.database;

import lv.javaguru.java2.rentapp.core.services.search_criterias.SearchCriteria;
import lv.javaguru.java2.rentapp.domain.Vehicle;

import java.util.List;

public interface VehicleDatabase {

    void addNewVehicle(Vehicle vehicle);

    void deleteVehicleByPlateNumber(String plateNumber);

    List<Vehicle> getAllVehicles();

    List<Vehicle> search(SearchCriteria searchCriteria);

    Vehicle getById(Long id);

}
