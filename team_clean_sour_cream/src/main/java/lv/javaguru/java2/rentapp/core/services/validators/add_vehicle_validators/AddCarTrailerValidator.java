package lv.javaguru.java2.rentapp.core.services.validators.add_vehicle_validators;

import lv.javaguru.java2.rentapp.core.database.VehicleDatabase;
import lv.javaguru.java2.rentapp.core.requests.AddVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.core.services.new_vehicle_creators.CarTrailerCreator;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import lv.javaguru.java2.rentapp.enums.EngineType;
import lv.javaguru.java2.rentapp.enums.TransmissionType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static lv.javaguru.java2.rentapp.domain.CarTrailer.*;

public class AddCarTrailerValidator extends AddVehicleValidator {

    private VehicleDatabase vehicleDatabase;

    public AddCarTrailerValidator(VehicleDatabase vehicleDatabase) {
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
        validateDeckWidthInCm(request).ifPresent(errors::add);
        validateDeckLengthInCm(request).ifPresent(errors::add);
        validateDeckHeightInCm(request).ifPresent(errors::add);
        validateEmptyWeightInKg(request).ifPresent(errors::add);
        validateMaxLoadWeightInKg(request).ifPresent(errors::add);
        if (errors.isEmpty()) {
            validateVehicleIsNotDuplicate(request).ifPresent(errors::add);
        }
        return errors;
    }

    @Override
    protected Optional<CoreError> validateTransmissionType(AddVehicleRequest request) {
        String transmissionType = request.getTransmissionType();
        if (transmissionType == null || transmissionType.isBlank()) {
            return Optional.of(new CoreError("Transmission Type", "cannot be empty"));
        } else if (TransmissionType.NONE.getNameTransmissionType().equalsIgnoreCase(transmissionType.replaceAll("[^a-zA-Z]", ""))) {
            return Optional.empty();
        } else {
            return Optional.of(new CoreError("Transmission Type", "for Car Trailer must be \"" + TransmissionType.NONE.getNameTransmissionType() + "\""));
        }
    }

    @Override
    protected Optional<CoreError> validateEngineType(AddVehicleRequest request) {
        String engineType = request.getEngineType();
        if (engineType == null || engineType.isBlank()) {
            return Optional.of(new CoreError("Engine Type", "cannot be empty"));
        } else if (EngineType.NONE.getNameEngineType().equalsIgnoreCase(engineType.replaceAll("[^a-zA-Z]", ""))) {
            return Optional.empty();
        } else {
            return Optional.of(new CoreError("Engine Type", "for Car Trailer must be \"" + EngineType.NONE.getNameEngineType() + "\""));
        }
    }

    private Optional<CoreError> validateDeckWidthInCm(AddVehicleRequest request) {
        Integer deckWidthInCm = request.getDeckWidthInCm();
        if (deckWidthInCm == null || deckWidthInCm < TRAIL_MIN_DECK_WIDTH_IN_CM || deckWidthInCm <= 0) {
            return Optional.of(new CoreError("Deck Width in cm", "cannot be empty, negative, zero or less than " + TRAIL_MIN_DECK_WIDTH_IN_CM));
        } else if (deckWidthInCm > TRAIL_MAX_DECK_WIDTH_IN_CM) {
            return Optional.of(new CoreError("Deck Width in cm", "cannot be more than " + TRAIL_MAX_DECK_WIDTH_IN_CM));
        } else {
            return Optional.empty();
        }
    }

    private Optional<CoreError> validateDeckLengthInCm(AddVehicleRequest request) {
        Integer deckLengthInCm = request.getDeckLengthInCm();
        if (deckLengthInCm == null || deckLengthInCm < TRAIL_MIN_DECK_LENGTH_IN_CM || deckLengthInCm <= 0) {
            return Optional.of(new CoreError("Deck Length in cm", "cannot be empty, negative, zero or less than " + TRAIL_MIN_DECK_LENGTH_IN_CM));
        } else if (deckLengthInCm > TRAIL_MAX_DECK_LENGTH_IN_CM) {
            return Optional.of(new CoreError("Deck Length in cm", "cannot be more than " + TRAIL_MAX_DECK_LENGTH_IN_CM));
        } else {
            return Optional.empty();
        }
    }

    private Optional<CoreError> validateDeckHeightInCm(AddVehicleRequest request) {
        Integer deckHeightInCm = request.getDeckHeightInCm();
        if (deckHeightInCm == null || deckHeightInCm < TRAIL_MIN_DECK_HEIGHT_IN_CM || deckHeightInCm <= 0) {
            return Optional.of(new CoreError("Deck Height in cm", "cannot be empty, negative, zero or less than " + TRAIL_MIN_DECK_HEIGHT_IN_CM));
        } else if (deckHeightInCm > TRAIL_MAX_DECK_HEIGHT_IN_CM) {
            return Optional.of(new CoreError("Deck Height in cm", "cannot be more than " + TRAIL_MAX_DECK_HEIGHT_IN_CM));
        } else {
            return Optional.empty();
        }
    }

    private Optional<CoreError> validateEmptyWeightInKg(AddVehicleRequest request) {
        Integer emptyWeightInKg = request.getEmptyWeightInKg();
        if (emptyWeightInKg == null || emptyWeightInKg < TRAIL_MIN_EMPTY_WEIGHT_IN_KG || emptyWeightInKg <= 0) {
            return Optional.of(new CoreError("Empty Weight in KG", "cannot be empty, negative, zero or less than " + TRAIL_MIN_EMPTY_WEIGHT_IN_KG));
        } else if (emptyWeightInKg > TRAIL_MAX_EMPTY_WEIGHT_IN_KG) {
            return Optional.of(new CoreError("Empty Weight in KG", "cannot be more than " + TRAIL_MAX_EMPTY_WEIGHT_IN_KG));
        } else {
            return Optional.empty();
        }
    }

    private Optional<CoreError> validateMaxLoadWeightInKg(AddVehicleRequest request) {
        Integer maxLoadWeightInKg = request.getMaxLoadWeightInKg();
        if (maxLoadWeightInKg == null || maxLoadWeightInKg < TRAIL_MIN_LOAD_WEIGHT_IN_KG || maxLoadWeightInKg <= 0) {
            return Optional.of(new CoreError("Max Load Weight in KG", "cannot be empty, negative, zero or less than " + TRAIL_MIN_LOAD_WEIGHT_IN_KG));
        } else if (maxLoadWeightInKg > TRAIL_MAX_LOAD_WEIGHT_IN_KG) {
            return Optional.of(new CoreError("Max Load Weight in KG", "cannot be more than " + TRAIL_MAX_LOAD_WEIGHT_IN_KG));
        } else {
            return Optional.empty();
        }
    }

    private Optional<CoreError> validateVehicleIsNotDuplicate(AddVehicleRequest request) {
        Vehicle carTrailer = new CarTrailerCreator().createVehicle(request);
        return vehicleDatabase.getAllVehicles().stream().anyMatch(vehicle -> vehicle.equals(carTrailer))
                ? Optional.of(new CoreError("Vehicle", "is already in the database"))
                : Optional.empty();
    }
}
