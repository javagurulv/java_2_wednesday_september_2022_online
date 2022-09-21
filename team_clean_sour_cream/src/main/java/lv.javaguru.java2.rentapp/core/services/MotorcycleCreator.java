package lv.javaguru.java2.rentapp.core.services;

import lv.javaguru.java2.rentapp.core.database.Database;
import lv.javaguru.java2.rentapp.core.requests.AddVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.AddVehicleResponse;
import lv.javaguru.java2.rentapp.domain.Motorcycle;
import lv.javaguru.java2.rentapp.domain.Vehicle;

class MotorcycleCreator implements VehicleTypeCreator {

	private Database database;

	public MotorcycleCreator(Database database) {
		this.database = database;
	}

	@Override
	public AddVehicleResponse create(AddVehicleRequest request) {
		Vehicle motorcycle = new Motorcycle(request.getBrand(), request.getModel(), request.isAvailableForRent(),
											request.getYearOfProduction(), request.getColour(), request.getRentPricePerDay(), request.getEngineType(),
											request.getPlateNumber(), request.getTransmissionType(), request.getPassengerAmount());
		database.addNewVehicle(motorcycle);
		return new AddVehicleResponse(motorcycle);
	}

}
