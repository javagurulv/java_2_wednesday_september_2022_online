package lv.javaguru.java2.rentapp.core.services.validators.search_vehicle_validators;

import lv.javaguru.java2.rentapp.core.requests.SearchVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static lv.javaguru.java2.rentapp.core.services.validators.add_vehicle_validators.AddMotorcycleValidator.MAX_PASSENGER_AMOUNT;

public class SearchMotorcycleValidator extends SearchVehicleValidator {

    @Override
    public List<CoreError> validate(SearchVehicleRequest request) {
        List<CoreError> errors = new ArrayList<>();

        validateVehicleType(request).ifPresent(errors::add);
        validateTransmissionType(request).ifPresent(errors::add);

        validatePassengerAmount(request).ifPresent(errors::add);

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
}
