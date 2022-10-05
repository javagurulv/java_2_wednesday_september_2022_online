package lv.javaguru.java2.rentapp.core.services.validators.search_vehicle_validators;

import lv.javaguru.java2.rentapp.core.requests.SearchVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.domain.PassengerCar;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SearchPassengerCarValidator extends SearchVehicleValidator {

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

        Optional<Integer> passengerAmountOpt = Optional.ofNullable(request.getPassengerAmount());
        if (passengerAmountOpt.isEmpty()){
            return Optional.empty();
        }

        Integer passengerAmount = passengerAmountOpt.get();
        if (passengerAmount <= 0) {
            return Optional.of(new CoreError("Passenger amount", "cannot be empty, negative or 0"));
        } else if (passengerAmount > PassengerCar.CAR_MAX_PASSENGER_AMOUNT) {
            return Optional.of(new CoreError("Passenger amount", "cannot be more than " + PassengerCar.CAR_MAX_PASSENGER_AMOUNT));
        } else {
            return Optional.empty();
        }
    }

    protected Optional<CoreError> validateDoorsAmount(SearchVehicleRequest request) {
        Integer doorsAmount = request.getDoorsAmount();
        if (doorsAmount == null || doorsAmount <= 0) {
            return Optional.of(new CoreError("Doors amount", "cannot be empty, negative or 0"));
        } else if (doorsAmount > PassengerCar.CAR_MAX_DOORS_AMOUNT) {
            return Optional.of(new CoreError("Doors amount", "cannot be more than " + PassengerCar.CAR_MAX_DOORS_AMOUNT));
        } else {
            return Optional.empty();
        }
    }

    protected Optional<CoreError> validateBaggageAmount(SearchVehicleRequest request) {
        Integer baggageAmount = request.getBaggageAmount();
        if (baggageAmount == null || baggageAmount < 0) {
            return Optional.of(new CoreError("Baggage amount", "cannot be empty or negative"));
        } else if (baggageAmount > PassengerCar.CAR_MAX_BAGGAGE_AMOUNT) {
            return Optional.of(new CoreError("Baggage amount", "cannot be more than " + PassengerCar.CAR_MAX_BAGGAGE_AMOUNT));
        } else {
            return Optional.empty();
        }
    }
}
