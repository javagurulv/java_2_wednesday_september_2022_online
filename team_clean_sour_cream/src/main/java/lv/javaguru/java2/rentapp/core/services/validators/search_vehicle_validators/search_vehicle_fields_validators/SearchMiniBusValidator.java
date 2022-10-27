package lv.javaguru.java2.rentapp.core.services.validators.search_vehicle_validators.search_vehicle_fields_validators;

import lv.javaguru.java2.rentapp.core.requests.SearchVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static lv.javaguru.java2.rentapp.domain.MiniBus.*;

public class SearchMiniBusValidator extends SearchVehicleFieldsValidator {

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

    private Optional<CoreError> validatePassengerAmount(SearchVehicleRequest request) {

        Optional<Integer> passengerAmountOpt = Optional.ofNullable(request.getPassengerAmount());
        if (passengerAmountOpt.isEmpty()) {
            return Optional.empty();
        }

        Integer passengerAmount = passengerAmountOpt.get();
        if (passengerAmount <= 0 || passengerAmount < BUS_MIN_PASSENGER_AMOUNT) {
            return Optional.of(new CoreError("Passenger amount", "can`t be negative, zero or less than " + BUS_MIN_PASSENGER_AMOUNT));
        } else if (passengerAmount > BUS_MAX_PASSENGER_AMOUNT) {
            return Optional.of(new CoreError("Passenger amount", "can`t be more than " + BUS_MAX_PASSENGER_AMOUNT));
        } else {
            return Optional.empty();
        }
    }

    private Optional<CoreError> validateDoorsAmount(SearchVehicleRequest request) {

        Optional<Integer> doorsAmountOpt = Optional.ofNullable(request.getDoorsAmount());
        if (doorsAmountOpt.isEmpty()) {
            return Optional.empty();
        }

        Integer doorsAmount = doorsAmountOpt.get();
        if (doorsAmount <= 0 || doorsAmount < BUS_MIN_DOORS_AMOUNT) {
            return Optional.of(new CoreError("Doors amount", "can`t be negative, zero or less than " + BUS_MIN_DOORS_AMOUNT));
        } else if (doorsAmount > BUS_MAX_DOORS_AMOUNT) {
            return Optional.of(new CoreError("Doors amount", "can`t be more than " + BUS_MAX_DOORS_AMOUNT));
        } else {
            return Optional.empty();
        }
    }

    private Optional<CoreError> validateBaggageAmount(SearchVehicleRequest request) {

        Optional<Integer> baggageAmountOpt = Optional.ofNullable(request.getBaggageAmount());
        if (baggageAmountOpt.isEmpty()) {
            return Optional.empty();
        }

        Integer baggageAmount = baggageAmountOpt.get();
        if (baggageAmount < 0) {
            return Optional.of(new CoreError("Baggage amount", "can`t be negative"));
        } else if (baggageAmount > BUS_MAX_BAGGAGE_AMOUNT) {
            return Optional.of(new CoreError("Baggage amount", "can`t be more than " + BUS_MAX_BAGGAGE_AMOUNT));
        } else {
            return Optional.empty();
        }
    }
}
