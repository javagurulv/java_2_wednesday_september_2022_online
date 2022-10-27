package lv.javaguru.java2.rentapp.core.services;

import lv.javaguru.java2.rentapp.core.database.VehicleDatabase;
import lv.javaguru.java2.rentapp.core.requests.DeleteVehicleByPlateNumberRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.core.responses.DeleteVehicleByPlateNumberResponse;
import lv.javaguru.java2.rentapp.core.services.validators.DeleteVehicleByPlateNumberRequestValidator;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeleteVehicleByPlateNumberService {

    @Autowired
    VehicleDatabase vehicleDatabase;
    @Autowired
    private DeleteVehicleByPlateNumberRequestValidator deleteVehicleByPlateNumberValidator;

    public DeleteVehicleByPlateNumberResponse execute(DeleteVehicleByPlateNumberRequest request) {

        List<CoreError> errors = deleteVehicleByPlateNumberValidator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteVehicleByPlateNumberResponse(errors);
        }
        vehicleDatabase.deleteVehicleByPlateNumber(request.getPlateNumber());
        return new DeleteVehicleByPlateNumberResponse("Your vehicle was removed from list.");
    }

    public void showAllVehiclesPlateNumbers() {
        System.out.println("Available are:");
        vehicleDatabase.getAllVehicles().stream()
                .map(Vehicle::getPlateNumber)
                .forEach(System.out::println);
        System.out.println();
    }
}

