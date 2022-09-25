package lv.javaguru.java2.rentapp.core.services.new_vehicle_creators;

import lv.javaguru.java2.rentapp.core.requests.AddVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.AddVehicleResponse;

public interface VehicleCreator {

    AddVehicleResponse createVehicle(AddVehicleRequest request);

}
