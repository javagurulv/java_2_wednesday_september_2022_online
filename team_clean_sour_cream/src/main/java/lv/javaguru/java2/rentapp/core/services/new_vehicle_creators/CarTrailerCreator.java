package lv.javaguru.java2.rentapp.core.services.new_vehicle_creators;

import lv.javaguru.java2.rentapp.core.database.Database;
import lv.javaguru.java2.rentapp.core.requests.AddNewVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.AddNewVehicleResponse;
import lv.javaguru.java2.rentapp.domain.CarTrailer;
import lv.javaguru.java2.rentapp.domain.Vehicle;

public class CarTrailerCreator implements VehicleTypeCreator {

    private Database database;

    public CarTrailerCreator(Database database) {
        this.database = database;
    }

    @Override
    public AddNewVehicleResponse createVehicle(AddNewVehicleRequest request) {
        Vehicle carTrailer = new CarTrailer(request.getBrand(), request.getModel(), request.isAvailableForRent(),
                request.getYearOfProduction(), request.getColour(), request.getRentPricePerDay(), request.getEngineType(),
                request.getPlateNumber(), request.getTransmissionType(), request.getDeckWidthInCm(),
                request.getDeckLengthInCm(), request.getDeckHeightInCm(), request.getEmptyWeightInKg(), request.getMaxLoadWeightInKg());
        database.addNewVehicle(carTrailer);
        return new AddNewVehicleResponse(carTrailer);
    }

}
