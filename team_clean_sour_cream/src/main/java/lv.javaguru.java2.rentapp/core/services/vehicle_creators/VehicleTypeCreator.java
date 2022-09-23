package lv.javaguru.java2.rentapp.core.services.vehicle_creators;

import lv.javaguru.java2.rentapp.core.requests.AddVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.AddVehicleResponse;

public interface VehicleTypeCreator {

	AddVehicleResponse create(AddVehicleRequest request);

}
