package lv.javaguru.java2.rentapp.core.services.new_vehicle_creators;

import lv.javaguru.java2.rentapp.core.requests.AddVehicleRequest;
import lv.javaguru.java2.rentapp.domain.MiniBus;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import lv.javaguru.java2.rentapp.enums.Colour;
import lv.javaguru.java2.rentapp.enums.EngineType;
import lv.javaguru.java2.rentapp.enums.TransmissionType;
import org.springframework.stereotype.Component;

@Component
public class MiniBusCreator implements VehicleCreator {

    @Override
    public Vehicle createVehicle(AddVehicleRequest request) {
        return new MiniBus(request.getBrand(), request.getModel(), request.isAvailableForRent(),
                request.getYearOfProduction(),
                Colour.valueOf(request.getColour().toUpperCase().replaceAll("[^a-zA-Z]", "")),
                request.getRentPricePerDay(),
                EngineType.valueOf(request.getEngineType().toUpperCase().replaceAll("[^a-zA-Z]", "")),
                request.getPlateNumber(),
                TransmissionType.valueOf(request.getTransmissionType().toUpperCase().replaceAll("[^a-zA-Z]", "")),
                request.getPassengerAmount(), request.getBaggageAmount(),
                request.getDoorsAmount(), Boolean.parseBoolean(request.getIsAirConditioningAvailable()));
    }
}
