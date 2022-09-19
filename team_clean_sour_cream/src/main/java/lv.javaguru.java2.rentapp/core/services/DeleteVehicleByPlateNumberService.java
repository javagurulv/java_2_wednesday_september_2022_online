package lv.javaguru.java2.rentapp.core.services;

import lv.javaguru.java2.rentapp.core.database.Database;
import lv.javaguru.java2.rentapp.core.requests.DeleteVehicleByPlateNumberRequest;
import lv.javaguru.java2.rentapp.core.responses.DeleteVehicleByPlateNumberResponse;

public class DeleteVehicleByPlateNumberService {

    Database database;

    public DeleteVehicleByPlateNumberService(Database database) {
        this.database = database;
    }

    public DeleteVehicleByPlateNumberResponse execute(DeleteVehicleByPlateNumberRequest request) {
        boolean isVehicleDeleted = database.deleteVehicleByPlateNumber(request.getPlateNumber());
        return new DeleteVehicleByPlateNumberResponse(isVehicleDeleted);
    }

    public Database getDatabase() {
        return database;
    }
}
