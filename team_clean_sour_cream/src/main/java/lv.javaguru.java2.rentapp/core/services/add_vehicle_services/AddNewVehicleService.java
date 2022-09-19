package lv.javaguru.java2.rentapp.core.services.add_vehicle_services;

import lv.javaguru.java2.rentapp.core.requests.AddVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.AddVehicleResponse;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import lv.javaguru.java2.rentapp.core.database.Database;

public abstract class AddNewVehicleService {

    Database database;

    public AddNewVehicleService(Database database) {
        this.database = database;
    }

    abstract public AddVehicleResponse execute(AddVehicleRequest request);
}
