package lv.javaguru.java2.rentapp.core.services.validators.search_vehicle_validators.search_vehicle_fields_validators;

import lv.javaguru.java2.rentapp.core.requests.SearchVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static lv.javaguru.java2.rentapp.domain.Motorcycle.MOTO_MAX_PASSENGER_AMOUNT;
import static lv.javaguru.java2.rentapp.domain.Motorcycle.MOTO_MIN_PASSENGER_AMOUNT;

public class SearchMotorcycleValidator extends SearchVehicleFieldsValidator {

    @Override
    public List<CoreError> validate(SearchVehicleRequest request) {
        List<CoreError> errors = new ArrayList<>();

        validateVehicleType(request).ifPresent(errors::add);
        validateTransmissionType(request).ifPresent(errors::add);

        validatePassengerAmount(request).ifPresent(errors::add);

        return errors;
    }

    private Optional<CoreError> validatePassengerAmount(SearchVehicleRequest request) {

        Optional<Integer> passengerAmountOpt = Optional.ofNullable(request.getPassengerAmount());
        if (passengerAmountOpt.isEmpty()) {
            return Optional.empty();
        }

        Integer passengerAmount = passengerAmountOpt.get();
        if (passengerAmount <= 0 || passengerAmount < MOTO_MIN_PASSENGER_AMOUNT) {
            return Optional.of(new CoreError("Passenger amount", "can`t be negative, zero or less than " + MOTO_MIN_PASSENGER_AMOUNT));
        } else if (passengerAmount > MOTO_MAX_PASSENGER_AMOUNT) {
            return Optional.of(new CoreError("Passenger amount", "can`t be more than " + MOTO_MAX_PASSENGER_AMOUNT));
        } else {
            return Optional.empty();
        }
    }
}
