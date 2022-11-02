package lv.javaguru.java2.rentapp.core.services;

import lv.javaguru.java2.rentapp.core.database.VehicleDatabase;
import lv.javaguru.java2.rentapp.core.requests.ShowAllVehiclesRequest;
import lv.javaguru.java2.rentapp.core.responses.ShowAllVehiclesResponse;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShowAllVehiclesService {

    @Autowired
    VehicleDatabase vehicleDatabase;

    public ShowAllVehiclesResponse execute(ShowAllVehiclesRequest request) {
        List<Vehicle> vehicles = vehicleDatabase.getAllVehicles();
        if (vehicles.isEmpty()) {
            return new ShowAllVehiclesResponse("There is no vehicles in Data Base");
        }
        return new ShowAllVehiclesResponse(vehicles);
    }
}
