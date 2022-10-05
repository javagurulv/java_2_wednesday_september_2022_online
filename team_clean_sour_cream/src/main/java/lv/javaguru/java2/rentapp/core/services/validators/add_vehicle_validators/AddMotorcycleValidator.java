package lv.javaguru.java2.rentapp.core.services.validators.add_vehicle_validators;

import lv.javaguru.java2.rentapp.core.database.Database;
import lv.javaguru.java2.rentapp.core.requests.AddVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.core.services.new_vehicle_creators.MotorcycleCreator;
import lv.javaguru.java2.rentapp.domain.Motorcycle;
import lv.javaguru.java2.rentapp.domain.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        return errors;
    }

    protected Optional<CoreError> validatePassengerAmount(AddVehicleRequest request) {
        Integer passengerAmount = request.getPassengerAmount();
        if (passengerAmount == null || passengerAmount <= 0) {
            return Optional.of(new CoreError("Passenger amount", "cannot be empty, negative or 0"));
        } else if (passengerAmount > Motorcycle.MOTO_MAX_PASSENGER_AMOUNT) {
            return Optional.of(new CoreError("Passenger amount", "cannot be more than " + Motorcycle.MOTO_MAX_PASSENGER_AMOUNT));
        } else {
            return Optional.empty();
        }
    }

    protected Optional<CoreError> validateVehicleIsNotDuplicate(AddVehicleRequest request) {
        Vehicle motorcycle = new MotorcycleCreator().createVehicle(request);
        return database.getAllVehicles().stream().anyMatch(vehicle -> vehicle.equals(motorcycle))
                ? Optional.of(new CoreError("Vehicle", "is already in the database"))
                : Optional.empty();
    }
}
