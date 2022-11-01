package lv.javaguru.java2.rentapp.core.services;

import lv.javaguru.java2.rentapp.core.database.VehicleDatabase;
import lv.javaguru.java2.rentapp.core.requests.ShowAllVehiclesPlateNumbersRequest;
import lv.javaguru.java2.rentapp.core.responses.ShowAllVehiclesPlateNumbersResponse;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShowAllVehiclesPlateNumbersService {

    @Autowired
    private VehicleDatabase vehicleDatabase;

    public ShowAllVehiclesPlateNumbersResponse execute(ShowAllVehiclesPlateNumbersRequest request) {
        List<String> vehiclePlateNumbers = getVehiclePlateNumbers();
        if (vehiclePlateNumbers.isEmpty()) {
            return new ShowAllVehiclesPlateNumbersResponse("There is no any plate number");
        }
        return new ShowAllVehiclesPlateNumbersResponse(vehiclePlateNumbers);
    }

    private List<String> getVehiclePlateNumbers() {
        return vehicleDatabase.getAllVehicles().stream()
                .map(Vehicle::getPlateNumber)
                .collect(Collectors.toList());
    }
}
