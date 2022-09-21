package lv.javaguru.java2.rentapp.core.services.add_vehicle_services;

import lv.javaguru.java2.rentapp.core.database.Database;
import lv.javaguru.java2.rentapp.core.requests.AddVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.AddVehicleResponse;
import lv.javaguru.java2.rentapp.domain.CarTrailer;
import lv.javaguru.java2.rentapp.domain.Vehicle;

public class AddCarTrailerService extends AddNewVehicleService{

    public AddCarTrailerService(Database database) {
        super(database);
    }

    //wqe
    @Override
    public AddVehicleResponse execute(AddVehicleRequest request) {
        Vehicle carTrailer = new CarTrailer(request.getBrand(), request.getModel(), request.isAvailableForRent(),
                request.getYearOfProduction(), request.getColour(), request.getRentPricePerDay(), request.getEngineType(),
                request.getPlateNumber(), request.getTransmissionType(), request.getDeckWidthInCm(),
                request.getDeckLengthInCm(), request.getDeckHeightInCm(), request.getEmptyWeightInKg(), request.getMaxLoadWeightInKg());
        database.addNewVehicle(carTrailer);
        return new AddVehicleResponse(carTrailer);
    }
}

