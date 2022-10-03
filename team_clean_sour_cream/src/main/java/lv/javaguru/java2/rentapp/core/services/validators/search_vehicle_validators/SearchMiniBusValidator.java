package lv.javaguru.java2.rentapp.core.services.validators.search_vehicle_validators;

import lv.javaguru.java2.rentapp.core.requests.SearchVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static lv.javaguru.java2.rentapp.core.services.validators.add_vehicle_validators.AddMiniBusValidator.*;

public class SearchMiniBusValidator extends SearchVehicleValidator {

    @Override
    public List<CoreError> validate(SearchVehicleRequest request) {
        List<CoreError> errors = new ArrayList<>();

        validateVehicleType(request).ifPresent(errors::add);
        validateTransmissionType(request).ifPresent(errors::add);
        validateIsAirConditionerAvailable(request).ifPresent(errors::add);

        validatePassengerAmount(request).ifPresent(errors::add);
        validateDoorsAmount(request).ifPresent(errors::add);
        validateBaggageAmount(request).ifPresent(errors::add);

        return errors;
    }

    protected Optional<CoreError> validatePassengerAmount(SearchVehicleRequest request) {
        Integer passengerAmount = request.getPassengerAmount();
        if (passengerAmount == null || passengerAmount <= 0) {
            return Optional.of(new CoreError("Passenger amount", "cannot be empty, negative or 0"));
        } else if (passengerAmount > MAX_PASSENGER_AMOUNT) {
            return Optional.of(new CoreError("Passenger amount", "cannot be more than " + MAX_PASSENGER_AMOUNT));
        } else {
            return Optional.empty();
        }
    }

    protected Optional<CoreError> validateDoorsAmount(SearchVehicleRequest request) {
        Integer doorsAmount = request.getDoorsAmount();
        if (doorsAmount == null || doorsAmount <= 0) {
            return Optional.of(new CoreError("Doors amount", "cannot be empty, negative or 0"));
        } else if (doorsAmount > MAX_DOORS_AMOUNT) {
            return Optional.of(new CoreError("Doors amount", "cannot be more than " + MAX_DOORS_AMOUNT));
        } else {
            return Optional.empty();
        }
    }

    protected Optional<CoreError> validateBaggageAmount(SearchVehicleRequest request) {
        Integer baggageAmount = request.getBaggageAmount();
        if (baggageAmount == null || baggageAmount < 0) {
            return Optional.of(new CoreError("Baggage amount", "cannot be empty or negative"));
        } else if (baggageAmount > MAX_BAGGAGE_AMOUNT) {
            return Optional.of(new CoreError("Baggage amount", "cannot be more than " + MAX_BAGGAGE_AMOUNT));
        } else {
            return Optional.empty();
        }
    }
}
