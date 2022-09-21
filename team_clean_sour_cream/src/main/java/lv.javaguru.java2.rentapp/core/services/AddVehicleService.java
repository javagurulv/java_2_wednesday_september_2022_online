package lv.javaguru.java2.rentapp.core.services;

import lombok.AllArgsConstructor;
import lv.javaguru.java2.rentapp.core.database.Database;
import lv.javaguru.java2.rentapp.core.requests.AddVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.AddVehicleResponse;
import lv.javaguru.java2.rentapp.domain.*;
import lv.javaguru.java2.rentapp.enums.VehicleType;

@AllArgsConstructor
public class AddVehicleService {

    private Database database;

    public AddVehicleResponse execute(AddVehicleRequest request) {
        VehicleType vehicleType = request.getVehicleType();
        switch (vehicleType) {
            case PASSENGER_CAR -> {
                return addPassengerCar(request);
            }
            case MINIBUS -> {
                return addMiniBus(request);
            }
            case MOTORCYCLE -> {
                return addMotorcycle(request);
            }
            case CAR_TRAILER -> {
                return addCarTrailer(request);
            }
        }
        return null;
    }

    private AddVehicleResponse addPassengerCar(AddVehicleRequest request) {
        Vehicle passengerCar = new PassengerCar(request.getBrand(), request.getModel(), request.isAvailableForRent(),
                request.getYearOfProduction(), request.getColour(), request.getRentPricePerDay(), request.getEngineType(),
                request.getPlateNumber(), request.getTransmissionType(), request.getPassengerAmount(), request.getBaggageAmount(),
                request.getDoorsAmount(), request.isAirConditioningAvailable());
        database.addNewVehicle(passengerCar);
        return new AddVehicleResponse(passengerCar);
    }

    private AddVehicleResponse addMiniBus(AddVehicleRequest request) {
        Vehicle miniBus = new MiniBus(request.getBrand(), request.getModel(), request.isAvailableForRent(),
                request.getYearOfProduction(), request.getColour(), request.getRentPricePerDay(), request.getEngineType(),
                request.getPlateNumber(), request.getTransmissionType(), request.getPassengerAmount(), request.getBaggageAmount(),
                request.getDoorsAmount(), request.isAirConditioningAvailable());
        database.addNewVehicle(miniBus);
        return new AddVehicleResponse(miniBus);
    }

    private AddVehicleResponse addMotorcycle(AddVehicleRequest request) {
        Vehicle motorcycle = new Motorcycle(request.getBrand(), request.getModel(), request.isAvailableForRent(),
                request.getYearOfProduction(), request.getColour(), request.getRentPricePerDay(), request.getEngineType(),
                request.getPlateNumber(), request.getTransmissionType(), request.getPassengerAmount());
        database.addNewVehicle(motorcycle);
        return new AddVehicleResponse(motorcycle);
    }

    private AddVehicleResponse addCarTrailer(AddVehicleRequest request) {
        Vehicle carTrailer = new CarTrailer(request.getBrand(), request.getModel(), request.isAvailableForRent(),
                request.getYearOfProduction(), request.getColour(), request.getRentPricePerDay(), request.getEngineType(),
                request.getPlateNumber(), request.getTransmissionType(), request.getDeckWidthInCm(),
                request.getDeckLengthInCm(), request.getDeckHeightInCm(), request.getEmptyWeightInKg(), request.getMaxLoadWeightInKg());
        database.addNewVehicle(carTrailer);
        return new AddVehicleResponse(carTrailer);
    }

}
