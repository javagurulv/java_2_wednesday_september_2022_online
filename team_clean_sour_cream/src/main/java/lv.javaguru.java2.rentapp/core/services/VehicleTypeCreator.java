package lv.javaguru.java2.rentapp.core.services;

import lv.javaguru.java2.rentapp.core.requests.AddVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.AddVehicleResponse;

interface VehicleTypeCreator {

	AddVehicleResponse create(AddVehicleRequest request);

}
