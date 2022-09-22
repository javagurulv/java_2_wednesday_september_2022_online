package lv.javaguru.java2.rentapp.core.services;

import lv.javaguru.java2.rentapp.core.database.Database;
import lv.javaguru.java2.rentapp.core.requests.AddVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.AddVehicleResponse;
import lv.javaguru.java2.rentapp.domain.CarTrailer;
import lv.javaguru.java2.rentapp.domain.Motorcycle;
import lv.javaguru.java2.rentapp.domain.Vehicle;

class CarTrailerCreator implements VehicleTypeCreator {

	private Database database;

	public CarTrailerCreator(Database database) {
		this.database = database;
	}

	@Override
	public AddVehicleResponse create(AddVehicleRequest request) {
		Vehicle carTrailer = new CarTrailer(request.getBrand(), request.getModel(), request.isAvailableForRent(),
											request.getYearOfProduction(), request.getColour(), request.getRentPricePerDay(), request.getEngineType(),
											request.getPlateNumber(), request.getTransmissionType(), request.getDeckWidthInCm(),
											request.getDeckLengthInCm(), request.getDeckHeightInCm(), request.getEmptyWeightInKg(), request.getMaxLoadWeightInKg());
		database.addNewVehicle(carTrailer);
		return new AddVehicleResponse(carTrailer);
	}

}
