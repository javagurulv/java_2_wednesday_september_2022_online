package lv.javaguru.java2.rentapp.core.services.vehicle_creators;

import lv.javaguru.java2.rentapp.core.requests.AddNewVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.AddNewVehicleResponse;

public interface VehicleTypeCreator {

    AddNewVehicleResponse createVehicle(AddNewVehicleRequest request);

}
