package lv.javaguru.java2.rentapp.core.services.validators.add_vehicle_validators;

import lv.javaguru.java2.rentapp.core.database.Database;
import lv.javaguru.java2.rentapp.core.requests.AddVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.core.services.new_vehicle_creators.PassengerCarCreator;
import lv.javaguru.java2.rentapp.domain.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddPassengerCarValidator extends AddVehicleValidator {

    protected static final int MAX_PASSENGER_AMOUNT = 100;
    protected static final int MAX_BAGGAGE_AMOUNT = 100;
    protected static final int MAX_DOORS_AMOUNT = 10;
    private Database database;

    public AddPassengerCarValidator(Database database) {
        this.database = database;
    }

    public List<CoreError> validate(AddVehicleRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateBrand(request).ifPresent(errors::add);
        validateModel(request).ifPresent(errors::add);
        validateYearOfProduction(request).ifPresent(errors::add);
        validateColour(request).ifPresent(errors::add);
        validateRentPrice(request).ifPresent(errors::add);
        validateEngineType(request).ifPresent(errors::add);
        validatePlateNumber(request).ifPresent(errors::add);
        validateTransmissionType(request).ifPresent(errors::add);
        validatePassengerAmount(request).ifPresent(errors::add);
        validateBaggageAmount(request).ifPresent(errors::add);
        validateDoorsAmount(request).ifPresent(errors::add);
        validateIsAirConditionerAvailable(request).ifPresent(errors::add);
        return errors;
    }

    protected Optional<CoreError> validatePassengerAmount(AddVehicleRequest request) {
        Integer passengerAmount = request.getPassengerAmount();
        if (passengerAmount == null || passengerAmount <= 0) {
            return Optional.of(new CoreError("Passenger amount", "cannot be empty, negative or 0"));
        } else if (passengerAmount > MAX_PASSENGER_AMOUNT) {
            return Optional.of(new CoreError("Passenger amount", "cannot be more than " + MAX_PASSENGER_AMOUNT));
        } else {
            return Optional.empty();
        }
    }

    protected Optional<CoreError> validateBaggageAmount(AddVehicleRequest request) {
        Integer baggageAmount = request.getBaggageAmount();
        if (baggageAmount == null || baggageAmount < 0) {
            return Optional.of(new CoreError("Baggage amount", "cannot be empty or negative"));
        } else if (baggageAmount > MAX_BAGGAGE_AMOUNT) {
            return Optional.of(new CoreError("Baggage amount", "cannot be more than " + MAX_BAGGAGE_AMOUNT));
        } else {
            return Optional.empty();
        }
    }

    protected Optional<CoreError> validateDoorsAmount(AddVehicleRequest request) {
        Integer doorsAmount = request.getDoorsAmount();
        if (doorsAmount == null || doorsAmount <= 0) {
            return Optional.of(new CoreError("Doors amount", "cannot be empty, negative or 0"));
        } else if (doorsAmount > MAX_DOORS_AMOUNT) {
            return Optional.of(new CoreError("Doors amount", "cannot be more than " + MAX_DOORS_AMOUNT));
        } else {
            return Optional.empty();
        }
    }

    protected Optional<CoreError> validateIsAirConditionerAvailable(AddVehicleRequest request) {
        String isAirConditioningAvailable = request.getIsAirConditioningAvailable();
        if (isAirConditioningAvailable == null || isAirConditioningAvailable.isBlank()) {
            return Optional.of(new CoreError("IsAirConditionerAvailable", "cannot be empty"));
        } else if (isAirConditioningAvailable.equalsIgnoreCase("true")
                || isAirConditioningAvailable.equalsIgnoreCase("false")) {
            return Optional.empty();
        } else {
            return Optional.of(new CoreError("IsAirConditionerAvailable", "must be either true or false"));
        }
    }

    protected Optional<CoreError> validateVehicleIsNotDuplicate(AddVehicleRequest request) {
        Vehicle passengerCar = new PassengerCarCreator().createVehicle(request);
        return database.getAllVehicles().stream().anyMatch(vehicle -> vehicle.equals(passengerCar))
                ? Optional.of(new CoreError("Vehicle", "is already in the database"))
                : Optional.empty();
    }
}