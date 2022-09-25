package lv.javaguru.java2.rentapp.core.services.validators.add_new_vehicle_validators;

import lv.javaguru.java2.rentapp.core.requests.AddVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddMotorcycleValidator extends AddVehicleValidator {

    protected static final int MAX_PASSENGER_AMOUNT = 4;

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

}
