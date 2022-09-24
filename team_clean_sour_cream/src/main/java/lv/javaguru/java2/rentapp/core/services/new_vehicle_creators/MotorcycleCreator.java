package lv.javaguru.java2.rentapp.core.services.new_vehicle_creators;

import lv.javaguru.java2.rentapp.core.database.Database;
import lv.javaguru.java2.rentapp.core.requests.AddNewVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.AddNewVehicleResponse;
import lv.javaguru.java2.rentapp.domain.Motorcycle;
import lv.javaguru.java2.rentapp.domain.Vehicle;

public class MotorcycleCreator implements VehicleTypeCreator {

	private Database database;

	public MotorcycleCreator(Database database) {
		this.database = database;
	}

	@Override
	public AddNewVehicleResponse createVehicle(AddNewVehicleRequest request) {
		Vehicle motorcycle = new Motorcycle(request.getBrand(), request.getModel(), request.isAvailableForRent(),
											request.getYearOfProduction(), request.getColour(), request.getRentPricePerDay(), request.getEngineType(),
											request.getPlateNumber(), request.getTransmissionType(), request.getPassengerAmount());
		database.addNewVehicle(motorcycle);
		return new AddNewVehicleResponse(motorcycle);
	}

}
