package lv.javaguru.java2.rentapp.core.services.validators.add_vehicle_validators;

import lv.javaguru.java2.rentapp.core.database.VehicleDatabase;
import lv.javaguru.java2.rentapp.core.requests.AddVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.core.services.new_vehicle_creators.MiniBusCreator;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static lv.javaguru.java2.rentapp.domain.MiniBus.*;

@Component
public class AddMiniBusValidator extends AddVehicleValidator {


    public VehicleDatabase vehicleDatabase;

    @Autowired
    public AddMiniBusValidator(VehicleDatabase vehicleDatabase) {
        this.vehicleDatabase = vehicleDatabase;
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
        if (errors.isEmpty()) {
            validateVehicleIsNotDuplicate(request).ifPresent(errors::add);
        }
        return errors;
    }

    private Optional<CoreError> validatePassengerAmount(AddVehicleRequest request) {
        Integer passengerAmount = request.getPassengerAmount();
        if (passengerAmount == null || passengerAmount < BUS_MIN_PASSENGER_AMOUNT || passengerAmount <= 0) {
            return Optional.of(new CoreError("Passenger amount", "cannot be empty, negative, zero or less than " + BUS_MIN_PASSENGER_AMOUNT));
        } else if (passengerAmount > BUS_MAX_PASSENGER_AMOUNT) {
            return Optional.of(new CoreError("Passenger amount", "cannot be more than " + BUS_MAX_PASSENGER_AMOUNT));
        } else {
            return Optional.empty();
        }
    }

    private Optional<CoreError> validateBaggageAmount(AddVehicleRequest request) {
        Integer baggageAmount = request.getBaggageAmount();
        if (baggageAmount == null || baggageAmount < 0) {
            return Optional.of(new CoreError("Baggage amount", "cannot be empty or negative"));
        } else if (baggageAmount > BUS_MAX_BAGGAGE_AMOUNT) {
            return Optional.of(new CoreError("Baggage amount", "cannot be more than " + BUS_MAX_BAGGAGE_AMOUNT));
        } else {
            return Optional.empty();
        }
    }

    private Optional<CoreError> validateDoorsAmount(AddVehicleRequest request) {
        Integer doorsAmount = request.getDoorsAmount();
        if (doorsAmount == null || doorsAmount < BUS_MIN_DOORS_AMOUNT || doorsAmount <= 0) {
            return Optional.of(new CoreError("Doors amount", "cannot be empty, negative, zero or less than " + BUS_MIN_DOORS_AMOUNT));
        } else if (doorsAmount > BUS_MAX_DOORS_AMOUNT) {
            return Optional.of(new CoreError("Doors amount", "cannot be more than " + BUS_MAX_DOORS_AMOUNT));
        } else {
            return Optional.empty();
        }
    }

    private Optional<CoreError> validateIsAirConditionerAvailable(AddVehicleRequest request) {
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

    private Optional<CoreError> validateVehicleIsNotDuplicate(AddVehicleRequest request) {
        Vehicle miniBus = new MiniBusCreator().createVehicle(request);
        return vehicleDatabase.getAllVehicles().stream().anyMatch(vehicle -> vehicle.equals(miniBus))
                ? Optional.of(new CoreError("Vehicle", "is already in the database"))
                : Optional.empty();
    }
}
