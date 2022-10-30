package lv.javaguru.java2.rentapp.core.services.validators.search_vehicle_validators.search_vehicle_fields_validators;

import lv.javaguru.java2.rentapp.core.requests.SearchVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static lv.javaguru.java2.rentapp.domain.CarTrailer.*;
import static lv.javaguru.java2.rentapp.domain.CarTrailer.TRAIL_MIN_LOAD_WEIGHT_IN_KG;

@Component
public class SearchCarTrailerValidator extends SearchVehicleFieldsValidator {
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

    private Optional<CoreError> validateDeckWidthInCm(SearchVehicleRequest request) {

        Optional<Integer> deckWidthInCmOpt = Optional.ofNullable(request.getDeckWidthInCm());
        if (deckWidthInCmOpt.isEmpty()) {
            return Optional.empty();
        }

        Integer deckWidthInCm = deckWidthInCmOpt.get();
        if (deckWidthInCm <= 0 || deckWidthInCm < TRAIL_MIN_DECK_WIDTH_IN_CM) {
            return Optional.of(new CoreError("Deck Width in cm", "can`t be negative, zero or less than " + TRAIL_MIN_DECK_WIDTH_IN_CM));
        } else if (deckWidthInCm > TRAIL_MAX_DECK_WIDTH_IN_CM) {
            return Optional.of(new CoreError("Deck Width in cm", "can`t be more than " + TRAIL_MAX_DECK_WIDTH_IN_CM));
        } else {
            return Optional.empty();
        }
    }

    private Optional<CoreError> validateDeckLengthInCm(SearchVehicleRequest request) {

        Optional<Integer> deckLengthInCmOpt = Optional.ofNullable(request.getDeckLengthInCm());
        if (deckLengthInCmOpt.isEmpty()) {
            return Optional.empty();
        }

        Integer deckLengthInCm = deckLengthInCmOpt.get();
        if (deckLengthInCm <= 0 || deckLengthInCm < TRAIL_MIN_DECK_LENGTH_IN_CM) {
            return Optional.of(new CoreError("Deck Length in cm", "can`t be negative, zero or less than " + TRAIL_MIN_DECK_LENGTH_IN_CM));
        } else if (deckLengthInCm > TRAIL_MAX_DECK_LENGTH_IN_CM) {
            return Optional.of(new CoreError("Deck Length in cm", "can`t be more than " + TRAIL_MAX_DECK_LENGTH_IN_CM));
        } else {
            return Optional.empty();
        }
    }

    private Optional<CoreError> validateDeckHeightInCm(SearchVehicleRequest request) {

        Optional<Integer> deckHeightInCmOpt = Optional.ofNullable(request.getDeckHeightInCm());
        if (deckHeightInCmOpt.isEmpty()) {
            return Optional.empty();
        }

        Integer deckHeightInCm = deckHeightInCmOpt.get();
        if (deckHeightInCm <= 0 || deckHeightInCm < TRAIL_MIN_DECK_HEIGHT_IN_CM) {
            return Optional.of(new CoreError("Deck Height in cm", "can`t be negative, zero or less than " + TRAIL_MIN_DECK_HEIGHT_IN_CM));
        } else if (deckHeightInCm > TRAIL_MAX_DECK_HEIGHT_IN_CM) {
            return Optional.of(new CoreError("Deck Height in cm", "can`t be more than " + TRAIL_MAX_DECK_HEIGHT_IN_CM));
        } else {
            return Optional.empty();
        }
    }

    private Optional<CoreError> validateEmptyWeightInKg(SearchVehicleRequest request) {

        Optional<Integer> emptyWeightInKgOpt = Optional.ofNullable(request.getEmptyWeightInKg());
        if (emptyWeightInKgOpt.isEmpty()) {
            return Optional.empty();
        }

        Integer emptyWeightInKg = emptyWeightInKgOpt.get();
        if (emptyWeightInKg <= 0 || emptyWeightInKg < TRAIL_MIN_EMPTY_WEIGHT_IN_KG) {
            return Optional.of(new CoreError("Empty Weight in KG", "can`t be negative, zero or less than " + TRAIL_MIN_EMPTY_WEIGHT_IN_KG));
        } else if (emptyWeightInKg > TRAIL_MAX_EMPTY_WEIGHT_IN_KG) {
            return Optional.of(new CoreError("Empty Weight in KG", "can`t be more than " + TRAIL_MAX_EMPTY_WEIGHT_IN_KG));
        } else {
            return Optional.empty();
        }
    }

    private Optional<CoreError> validateMaxLoadWeightInKg(SearchVehicleRequest request) {

        Optional<Integer> maxLoadWeightInKgOpt = Optional.ofNullable(request.getMaxLoadWeightInKg());
        if (maxLoadWeightInKgOpt.isEmpty()) {
            return Optional.empty();
        }

        Integer maxLoadWeightInKg = maxLoadWeightInKgOpt.get();
        if (maxLoadWeightInKg <= 0 || maxLoadWeightInKg < TRAIL_MIN_LOAD_WEIGHT_IN_KG) {
            return Optional.of(new CoreError("Max Load Weight in KG", "can`t be negative, zero or less than " + TRAIL_MIN_LOAD_WEIGHT_IN_KG));
        } else if (maxLoadWeightInKg > TRAIL_MAX_LOAD_WEIGHT_IN_KG) {
            return Optional.of(new CoreError("Max Load Weight in KG", "can`t be more than " + TRAIL_MAX_LOAD_WEIGHT_IN_KG));
        } else {
            return Optional.empty();
        }
    }
}
