package lv.javaguru.java2.rentapp.core.services;

import lv.javaguru.java2.rentapp.core.database.VehicleDatabase;
import lv.javaguru.java2.rentapp.core.responses.ShowAllVehiclesResponse;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ShowAllVehiclesService {

    @Autowired
    VehicleDatabase vehicleDatabase;

    public ShowAllVehiclesResponse execute() {
        List<Vehicle> vehicles = vehicleDatabase.getAllVehicles();
        return new ShowAllVehiclesResponse(vehicles);
    }
}
