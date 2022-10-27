package lv.javaguru.java2.rentapp.core.services;

import lv.javaguru.java2.rentapp.core.database.VehicleDatabase;
import lv.javaguru.java2.rentapp.core.requests.DeleteVehicleByPlateNumberRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.core.responses.DeleteVehicleByPlateNumberResponse;
import lv.javaguru.java2.rentapp.core.services.validators.DeleteVehicleByPlateNumberRequestValidator;
import lv.javaguru.java2.rentapp.domain.Vehicle;

import java.util.List;

public class DeleteVehicleByPlateNumberService {

    VehicleDatabase vehicleDatabase;
    private DeleteVehicleByPlateNumberRequestValidator deleteVehicleByPlateNumberValidator;

    public DeleteVehicleByPlateNumberService(VehicleDatabase vehicleDatabase, DeleteVehicleByPlateNumberRequestValidator deleteVehicleByPlateNumberValidator) {
        this.vehicleDatabase = vehicleDatabase;
        this.deleteVehicleByPlateNumberValidator = deleteVehicleByPlateNumberValidator;
    }

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

