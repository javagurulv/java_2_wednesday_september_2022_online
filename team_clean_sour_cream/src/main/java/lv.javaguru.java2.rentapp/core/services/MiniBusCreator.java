package lv.javaguru.java2.rentapp.core.services;

import lv.javaguru.java2.rentapp.core.database.Database;
import lv.javaguru.java2.rentapp.core.requests.AddVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.AddVehicleResponse;
import lv.javaguru.java2.rentapp.domain.MiniBus;
import lv.javaguru.java2.rentapp.domain.PassengerCar;
import lv.javaguru.java2.rentapp.domain.Vehicle;

class MiniBusCreator implements VehicleTypeCreator {

	private Database database;

	public MiniBusCreator(Database database) {
		this.database = database;
	}

	@Override
	public AddVehicleResponse create(AddVehicleRequest request) {
		Vehicle miniBus = new MiniBus(request.getBrand(), request.getModel(), request.isAvailableForRent(),
									  request.getYearOfProduction(), request.getColour(), request.getRentPricePerDay(), request.getEngineType(),
									  request.getPlateNumber(), request.getTransmissionType(), request.getPassengerAmount(), request.getBaggageAmount(),
									  request.getDoorsAmount(), request.isAirConditioningAvailable());
		database.addNewVehicle(miniBus);
		return new AddVehicleResponse(miniBus);
	}

}
