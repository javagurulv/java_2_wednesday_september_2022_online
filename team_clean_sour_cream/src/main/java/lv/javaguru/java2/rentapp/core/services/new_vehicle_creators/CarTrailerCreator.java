package lv.javaguru.java2.rentapp.core.services.new_vehicle_creators;


import lv.javaguru.java2.rentapp.core.requests.AddVehicleRequest;
import lv.javaguru.java2.rentapp.domain.CarTrailer;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import lv.javaguru.java2.rentapp.enums.Colour;
import lv.javaguru.java2.rentapp.enums.EngineType;
import lv.javaguru.java2.rentapp.enums.TransmissionType;
import org.springframework.stereotype.Component;

@Component
public class CarTrailerCreator implements VehicleCreator {

    @Override
    public Vehicle createVehicle(AddVehicleRequest request) {
        CarTrailer carTrailer = new CarTrailer(request.getBrand(), request.getModel(), request.isAvailableForRent(),
                request.getYearOfProduction(),
                Colour.valueOf(request.getColour().toUpperCase().replaceAll("[^a-zA-Z]", "")),
                request.getRentPricePerDay(),
                EngineType.valueOf(request.getEngineType().toUpperCase().replaceAll("[^a-zA-Z]", "")),
                request.getPlateNumber(),
                TransmissionType.valueOf(request.getTransmissionType().toUpperCase().replaceAll("[^a-zA-Z]", "")),
                request.getDeckWidthInCm(),
                request.getDeckLengthInCm(), request.getDeckHeightInCm(), request.getEmptyWeightInKg(), request.getMaxLoadWeightInKg());
        carTrailer.setVehicleType(request.getVehicleType());
        return carTrailer;
    }
}
