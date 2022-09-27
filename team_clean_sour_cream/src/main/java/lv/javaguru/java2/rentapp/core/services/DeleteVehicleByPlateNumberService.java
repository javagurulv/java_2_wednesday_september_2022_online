package lv.javaguru.java2.rentapp.core.services;

import lv.javaguru.java2.rentapp.core.database.Database;
import lv.javaguru.java2.rentapp.core.requests.DeleteVehicleByPlateNumberRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.core.responses.DeleteVehicleByPlateNumberResponse;
import lv.javaguru.java2.rentapp.core.services.validators.DeleteVehicleByPlateNumberValidator;
import lv.javaguru.java2.rentapp.domain.Vehicle;

import java.util.List;

public class DeleteVehicleByPlateNumberService {

    Database database;
    private DeleteVehicleByPlateNumberValidator deleteVehicleByPlateNumberValidator;

    public DeleteVehicleByPlateNumberService(Database database) {
        this.database = database;
        this.deleteVehicleByPlateNumberValidator = new DeleteVehicleByPlateNumberValidator();
    }

    public DeleteVehicleByPlateNumberResponse execute(DeleteVehicleByPlateNumberRequest request) {
        boolean isVehicleDeleted = database.deleteVehicleByPlateNumber(request.getPlateNumber());
        List<CoreError> errors = deleteVehicleByPlateNumberValidator.validate(request);
        if (!errors.isEmpty()) {
            return new DeleteVehicleByPlateNumberResponse(errors);
        }
        return new DeleteVehicleByPlateNumberResponse(isVehicleDeleted);
    }

    public void showAllVehiclesPlateNumbers() {
        System.out.println("Available are:");
        database.getAllVehicles().stream()
                .map(Vehicle::getPlateNumber)
                .forEach(System.out::println);
        System.out.println();
    }
}

