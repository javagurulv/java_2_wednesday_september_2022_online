package lv.javaguru.java2.rentapp.core.services.validators.add_vehicle_validators;

import lv.javaguru.java2.rentapp.core.database.Database;
import lv.javaguru.java2.rentapp.core.requests.AddVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.core.services.new_vehicle_creators.MotorcycleCreator;
import lv.javaguru.java2.rentapp.domain.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static lv.javaguru.java2.rentapp.domain.Motorcycle.MOTO_MAX_PASSENGER_AMOUNT;
import static lv.javaguru.java2.rentapp.domain.Motorcycle.MOTO_MIN_PASSENGER_AMOUNT;

public class AddMotorcycleValidator extends AddVehicleValidator {

    private Database database;

    public AddMotorcycleValidator(Database database) {
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
        if (errors.isEmpty()) {
            validateVehicleIsNotDuplicate(request).ifPresent(errors::add);
        }
        return errors;
    }

    private Optional<CoreError> validatePassengerAmount(AddVehicleRequest request) {
        Integer passengerAmount = request.getPassengerAmount();
        if (passengerAmount == null || passengerAmount < MOTO_MIN_PASSENGER_AMOUNT || passengerAmount <= 0) {
            return Optional.of(new CoreError("Passenger amount", "cannot be empty, negative, zero or less than " + MOTO_MIN_PASSENGER_AMOUNT));
        } else if (passengerAmount > MOTO_MAX_PASSENGER_AMOUNT) {
            return Optional.of(new CoreError("Passenger amount", "cannot be more than " + MOTO_MAX_PASSENGER_AMOUNT));
        } else {
            return Optional.empty();
        }
    }

    private Optional<CoreError> validateVehicleIsNotDuplicate(AddVehicleRequest request) {
        Vehicle motorcycle = new MotorcycleCreator().createVehicle(request);
        return database.getAllVehicles().stream().anyMatch(vehicle -> vehicle.equals(motorcycle))
                ? Optional.of(new CoreError("Vehicle", "is already in the database"))
                : Optional.empty();
    }
}
