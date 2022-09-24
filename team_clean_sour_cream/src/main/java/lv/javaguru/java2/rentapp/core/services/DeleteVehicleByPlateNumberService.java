package lv.javaguru.java2.rentapp.core.services;

import lv.javaguru.java2.rentapp.core.database.Database;
import lv.javaguru.java2.rentapp.core.requests.DeleteVehicleByPlateNumberRequest;
import lv.javaguru.java2.rentapp.core.responses.DeleteVehicleByPlateNumberResponse;
import lv.javaguru.java2.rentapp.domain.Vehicle;

public class DeleteVehicleByPlateNumberService {

    Database database;

    public DeleteVehicleByPlateNumberService(Database database) {
        this.database = database;
    }

    public DeleteVehicleByPlateNumberResponse execute(DeleteVehicleByPlateNumberRequest request) {
        boolean isVehicleDeleted = database.deleteVehicleByPlateNumber(request.getPlateNumber());
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
