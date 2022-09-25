package lv.javaguru.java2.rentapp.core.services.new_vehicle_creators;

import lv.javaguru.java2.rentapp.core.database.Database;
import lv.javaguru.java2.rentapp.core.requests.AddVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.AddVehicleResponse;
import lv.javaguru.java2.rentapp.domain.MiniBus;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import lv.javaguru.java2.rentapp.enums.Colour;
import lv.javaguru.java2.rentapp.enums.EngineType;
import lv.javaguru.java2.rentapp.enums.TransmissionType;

public class MiniBusCreator implements VehicleCreator {

    private Database database;

    public MiniBusCreator(Database database) {
        this.database = database;
    }

    @Override
    public AddVehicleResponse createVehicle(AddVehicleRequest request) {
        Vehicle miniBus = new MiniBus(request.getBrand(), request.getModel(), request.isAvailableForRent(),
                request.getYearOfProduction(),
                Colour.valueOf(request.getColour().toUpperCase().replaceAll("[^a-zA-Z]", "")),
                request.getRentPricePerDay(),
                EngineType.valueOf(request.getEngineType().toUpperCase().replaceAll("[^a-zA-Z]", "")),
                request.getPlateNumber(),
                TransmissionType.valueOf(request.getTransmissionType().toUpperCase().replaceAll("[^a-zA-Z]", "")),
                request.getPassengerAmount(), request.getBaggageAmount(),
                request.getDoorsAmount(), Boolean.parseBoolean(request.getIsAirConditioningAvailable()));
        database.addNewVehicle(miniBus);
        return new AddVehicleResponse(miniBus);
    }

}
