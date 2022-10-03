package lv.javaguru.java2.rentapp.core.services.new_vehicle_creators;

import lv.javaguru.java2.rentapp.core.requests.AddVehicleRequest;
import lv.javaguru.java2.rentapp.domain.Vehicle;

public interface VehicleCreator {

    Vehicle createVehicle(AddVehicleRequest request);

}
