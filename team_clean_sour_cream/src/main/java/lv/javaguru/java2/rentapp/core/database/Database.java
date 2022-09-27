package lv.javaguru.java2.rentapp.core.database;

import lv.javaguru.java2.rentapp.core.services.search_criterias.SearchCriteria;
import lv.javaguru.java2.rentapp.domain.Vehicle;

import java.util.List;

public interface Database {

    void addNewVehicle(Vehicle vehicle);

    boolean deleteVehicleByPlateNumber(String plateNumber);

    List<Vehicle> getAllVehicles();

    List<Vehicle> search(SearchCriteria searchCriteria);

}
