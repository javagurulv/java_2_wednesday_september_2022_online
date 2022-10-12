package lv.javaguru.java2.rentapp.core.services;

import lv.javaguru.java2.rentapp.core.database.Database;
import lv.javaguru.java2.rentapp.core.requests.DeleteVehicleByPlateNumberRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.core.responses.DeleteVehicleByPlateNumberResponse;
import lv.javaguru.java2.rentapp.core.services.validators.DeleteVehicleByPlateNumberRequestValidator;
import lv.javaguru.java2.rentapp.domain.Vehicle;

import java.util.List;

public class DeleteVehicleByPlateNumberService {

    Database database;
    private DeleteVehicleByPlateNumberRequestValidator deleteVehicleByPlateNumberValidator;

    public DeleteVehicleByPlateNumberService(Database database, DeleteVehicleByPlateNumberRequestValidator deleteVehicleByPlateNumberValidator) {
        this.database = database;
        this.deleteVehicleByPlateNumberValidator = deleteVehicleByPlateNumberValidator;
    }

    public DeleteVehicleByPlateNumberResponse execute(DeleteVehicleByPlateNumberRequest request) {

        List<CoreError> errors = deleteVehicleByPlateNumberValidator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteVehicleByPlateNumberResponse(errors);
        }
        database.deleteVehicleByPlateNumber(request.getPlateNumber());
        return new DeleteVehicleByPlateNumberResponse("Your vehicle was removed from list.");
    }

    public void showAllVehiclesPlateNumbers() {
        System.out.println("Available are:");
        database.getAllVehicles().stream()
                .map(Vehicle::getPlateNumber)
                .forEach(System.out::println);
        System.out.println();
    }
}

