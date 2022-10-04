package lv.javaguru.java2.rentapp.core.services.validators.add_vehicle_validators;

import lv.javaguru.java2.rentapp.core.database.Database;
import lv.javaguru.java2.rentapp.core.requests.AddVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.core.services.new_vehicle_creators.CarTrailerCreator;
import lv.javaguru.java2.rentapp.domain.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddCarTrailerValidator extends AddVehicleValidator {

    public static final int MAX_DECK_WIDTH_IN_CM = 500;
    public static final int MIN_DECK_WIDTH_IN_CM = 100;
    public static final int MAX_DECK_LENGTH_IN_CM = 1000;
    public static final int MIN_DECK_LENGTH_IN_CM = 100;
    public static final int MAX_DECK_HEIGHT_IN_CM = 300;
    public static final int MIN_DECK_HEIGHT_IN_CM = 50;
    public static final int MAX_EMPTY_WEIGHT_IN_KG = 2000;
    public static final int MIN_EMPTY_WEIGHT_IN_KG = 200;
    public static final int MAX_LOAD_WEIGHT_IN_KG = 5000;
    public static final int MIN_LOAD_WEIGHT_IN_KG = 200;

    private Database database;

    public AddCarTrailerValidator(Database database) {
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
        validateDeckWidthInCm(request).ifPresent(errors::add);
        validateDeckLengthInCm(request).ifPresent(errors::add);
        validateDeckHeightInCm(request).ifPresent(errors::add);
        validateEmptyWeightInKg(request).ifPresent(errors::add);
        validateMaxLoadWeightInKg(request).ifPresent(errors::add);
        return errors;
    }

    @Override
    protected Optional<CoreError> validateEngineType(AddVehicleRequest request) {
        String engineType = request.getEngineType();
        if (engineType == null || engineType.isBlank()) {
            return Optional.of(new CoreError("Engine Type", "cannot be empty"));
        } else if ("None".equalsIgnoreCase(engineType.replaceAll("[^a-zA-Z]", ""))) {
            return Optional.empty();
        } else {
            return Optional.of(new CoreError("Engine Type", "for Car Trailer must be \"None\""));
        }
    }

    @Override
    protected Optional<CoreError> validateTransmissionType(AddVehicleRequest request) {
        String transmissionType = request.getTransmissionType();
        if (transmissionType == null || transmissionType.isBlank()) {
            return Optional.of(new CoreError("Transmission Type", "cannot be empty"));
        } else if ("None".equalsIgnoreCase(transmissionType.replaceAll("[^a-zA-Z]", ""))) {
            return Optional.empty();
        } else {
            return Optional.of(new CoreError("Transmission Type", "for Car Trailer must be \"None\""));
        }
    }

    protected Optional<CoreError> validateDeckWidthInCm(AddVehicleRequest request) {
        Integer deckWidthInCm = request.getDeckWidthInCm();
        if (deckWidthInCm == null || deckWidthInCm <= 0) {
            return Optional.of(new CoreError("Deck Width in cm", "cannot be empty, negative or 0"));
        } else if (deckWidthInCm > MAX_DECK_WIDTH_IN_CM) {
            return Optional.of(new CoreError("Deck Width in cm", "cannot be more than " + MAX_DECK_WIDTH_IN_CM));
        } else {
            return Optional.empty();
        }
    }

    protected Optional<CoreError> validateDeckLengthInCm(AddVehicleRequest request) {
        Integer deckLengthInCm = request.getDeckLengthInCm();
        if (deckLengthInCm == null || deckLengthInCm <= 0) {
            return Optional.of(new CoreError("Deck Length in cm", "cannot be empty, negative or 0"));
        } else if (deckLengthInCm > MAX_DECK_LENGTH_IN_CM) {
            return Optional.of(new CoreError("Deck Length in cm", "cannot be more than " + MAX_DECK_LENGTH_IN_CM));
        } else {
            return Optional.empty();
        }
    }

    protected Optional<CoreError> validateDeckHeightInCm(AddVehicleRequest request) {
        Integer deckHeightInCm = request.getDeckHeightInCm();
        if (deckHeightInCm == null || deckHeightInCm <= 0) {
            return Optional.of(new CoreError("Deck Height in cm", "cannot be empty, negative or 0"));
        } else if (deckHeightInCm > MAX_DECK_HEIGHT_IN_CM) {
            return Optional.of(new CoreError("Deck Height in cm", "cannot be more than " + MAX_DECK_HEIGHT_IN_CM));
        } else {
            return Optional.empty();
        }
    }

    protected Optional<CoreError> validateEmptyWeightInKg(AddVehicleRequest request) {
        Integer emptyWeightInKg = request.getEmptyWeightInKg();
        if (emptyWeightInKg == null || emptyWeightInKg <= 0) {
            return Optional.of(new CoreError("Empty Weight in KG", "cannot be empty, negative or 0"));
        } else if (emptyWeightInKg > MAX_EMPTY_WEIGHT_IN_KG) {
            return Optional.of(new CoreError("Empty Weight in KG", "cannot be more than " + MAX_EMPTY_WEIGHT_IN_KG));
        } else {
            return Optional.empty();
        }
    }

    protected Optional<CoreError> validateMaxLoadWeightInKg(AddVehicleRequest request) {
        Integer maxLoadWeightInKg = request.getMaxLoadWeightInKg();
        if (maxLoadWeightInKg == null || maxLoadWeightInKg <= 0) {
            return Optional.of(new CoreError("Max Load Weight in KG", "cannot be empty, negative or 0"));
        } else if (maxLoadWeightInKg > MAX_LOAD_WEIGHT_IN_KG) {
            return Optional.of(new CoreError("Max Load Weight in KG", "cannot be more than " + MAX_LOAD_WEIGHT_IN_KG));
        } else {
            return Optional.empty();
        }
    }

    protected Optional<CoreError> validateVehicleIsNotDuplicate(AddVehicleRequest request) {
        Vehicle carTrailer = new CarTrailerCreator().createVehicle(request);
        return database.getAllVehicles().stream().anyMatch(vehicle -> vehicle.equals(carTrailer))
                ? Optional.of(new CoreError("Vehicle", "is already in the database"))
                : Optional.empty();
    }
}
