package lv.javaguru.java2.rentapp.core.services.new_vehicle_creators;

import lv.javaguru.java2.rentapp.core.database.Database;
import lv.javaguru.java2.rentapp.core.requests.AddNewVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.AddNewVehicleResponse;
import lv.javaguru.java2.rentapp.domain.MiniBus;
import lv.javaguru.java2.rentapp.domain.Vehicle;

public class MiniBusCreator implements VehicleTypeCreator {

	private Database database;

	public MiniBusCreator(Database database) {
		this.database = database;
	}

	@Override
	public AddNewVehicleResponse createVehicle(AddNewVehicleRequest request) {
		Vehicle miniBus = new MiniBus(request.getBrand(), request.getModel(), request.isAvailableForRent(),
									  request.getYearOfProduction(), request.getColour(), request.getRentPricePerDay(), request.getEngineType(),
									  request.getPlateNumber(), request.getTransmissionType(), request.getPassengerAmount(), request.getBaggageAmount(),
									  request.getDoorsAmount(), request.isAirConditioningAvailable());
		database.addNewVehicle(miniBus);
		return new AddNewVehicleResponse(miniBus);
	}

}
