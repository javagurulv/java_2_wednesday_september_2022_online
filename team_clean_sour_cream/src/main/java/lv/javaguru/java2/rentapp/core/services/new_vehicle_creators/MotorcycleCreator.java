package lv.javaguru.java2.rentapp.core.services.new_vehicle_creators;

import lv.javaguru.java2.rentapp.core.database.Database;
import lv.javaguru.java2.rentapp.core.requests.AddVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.AddVehicleResponse;
import lv.javaguru.java2.rentapp.domain.Motorcycle;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import lv.javaguru.java2.rentapp.enums.Colour;
import lv.javaguru.java2.rentapp.enums.EngineType;
import lv.javaguru.java2.rentapp.enums.TransmissionType;

public class MotorcycleCreator implements VehicleCreator {

    @Override
    public Vehicle createVehicle(AddVehicleRequest request) {
        Vehicle motorcycle = new Motorcycle(request.getBrand(), request.getModel(), request.isAvailableForRent(),
                request.getYearOfProduction(),
                Colour.valueOf(request.getColour().toUpperCase().replaceAll("[^a-zA-Z]", "")),
                request.getRentPricePerDay(),
                EngineType.valueOf(request.getEngineType().toUpperCase().replaceAll("[^a-zA-Z]", "")),
                request.getPlateNumber(),
                TransmissionType.valueOf(request.getTransmissionType().toUpperCase().replaceAll("[^a-zA-Z]", "")),
                request.getPassengerAmount());
        return motorcycle;
    }

}
