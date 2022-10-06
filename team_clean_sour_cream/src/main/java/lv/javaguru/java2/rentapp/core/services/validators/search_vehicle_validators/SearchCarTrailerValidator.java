package lv.javaguru.java2.rentapp.core.services.validators.search_vehicle_validators;

import lv.javaguru.java2.rentapp.core.requests.SearchVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.domain.CarTrailer;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static lv.javaguru.java2.rentapp.domain.CarTrailer.*;

public class SearchCarTrailerValidator extends SearchVehicleValidator {
    @Override
    public List<CoreError> validate(SearchVehicleRequest request) {
        List<CoreError> errors = new ArrayList<>();

        validateVehicleType(request).ifPresent(errors::add);

        validateDeckWidthInCm(request).ifPresent(errors::add);
        validateDeckLengthInCm(request).ifPresent(errors::add);
        validateDeckHeightInCm(request).ifPresent(errors::add);
        validateEmptyWeightInKg(request).ifPresent(errors::add);
        validateMaxLoadWeightInKg(request).ifPresent(errors::add);

        return errors;
    }

    protected Optional<CoreError> validateDeckWidthInCm(SearchVehicleRequest request) {
        Integer deckWidthInCm = request.getDeckWidthInCm();
        if (deckWidthInCm == null || deckWidthInCm <= 0) {
            return Optional.of(new CoreError("Deck Width in cm", "cannot be empty, negative or 0"));
        } else if (deckWidthInCm > TRAIL_MAX_DECK_WIDTH_IN_CM) {
            return Optional.of(new CoreError("Deck Width in cm", "cannot be more than " + CarTrailer.TRAIL_MAX_DECK_WIDTH_IN_CM));
        } else {
            return Optional.empty();
        }
    }

    protected Optional<CoreError> validateDeckLengthInCm(SearchVehicleRequest request) {
        Integer deckLengthInCm = request.getDeckLengthInCm();
        if (deckLengthInCm == null || deckLengthInCm <= 0) {
            return Optional.of(new CoreError("Deck Length in cm", "cannot be empty, negative or 0"));
        } else if (deckLengthInCm > TRAIL_MAX_DECK_LENGTH_IN_CM) {
            return Optional.of(new CoreError("Deck Length in cm", "cannot be more than " + TRAIL_MAX_DECK_LENGTH_IN_CM));
        } else {
            return Optional.empty();
        }
    }

    protected Optional<CoreError> validateDeckHeightInCm(SearchVehicleRequest request) {
        Integer deckHeightInCm = request.getDeckHeightInCm();
        if (deckHeightInCm == null || deckHeightInCm <= 0) {
            return Optional.of(new CoreError("Deck Height in cm", "cannot be empty, negative or 0"));
        } else if (deckHeightInCm > TRAIL_MAX_DECK_HEIGHT_IN_CM) {
            return Optional.of(new CoreError("Deck Height in cm", "cannot be more than " + TRAIL_MAX_DECK_HEIGHT_IN_CM));
        } else {
            return Optional.empty();
        }
    }

    protected Optional<CoreError> validateEmptyWeightInKg(SearchVehicleRequest request) {
        Integer emptyWeightInKg = request.getEmptyWeightInKg();
        if (emptyWeightInKg == null || emptyWeightInKg <= 0) {
            return Optional.of(new CoreError("Empty Weight in KG", "cannot be empty, negative or 0"));
        } else if (emptyWeightInKg > TRAIL_MAX_EMPTY_WEIGHT_IN_KG) {
            return Optional.of(new CoreError("Empty Weight in KG", "cannot be more than " + TRAIL_MAX_EMPTY_WEIGHT_IN_KG));
        } else {
            return Optional.empty();
        }
    }

    protected Optional<CoreError> validateMaxLoadWeightInKg(SearchVehicleRequest request) {
        Integer maxLoadWeightInKg = request.getMaxLoadWeightInKg();
        if (maxLoadWeightInKg == null || maxLoadWeightInKg <= 0) {
            return Optional.of(new CoreError("Max Load Weight in KG", "cannot be empty, negative or 0"));
        } else if (maxLoadWeightInKg > TRAIL_MAX_LOAD_WEIGHT_IN_KG) {
            return Optional.of(new CoreError("Max Load Weight in KG", "cannot be more than " + TRAIL_MAX_LOAD_WEIGHT_IN_KG));
        } else {
            return Optional.empty();
        }
    }
}
