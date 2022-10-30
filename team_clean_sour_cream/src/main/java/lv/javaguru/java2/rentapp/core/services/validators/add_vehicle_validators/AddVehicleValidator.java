package lv.javaguru.java2.rentapp.core.services.validators.add_vehicle_validators;

import lv.javaguru.java2.rentapp.core.requests.AddVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.enums.Colour;
import lv.javaguru.java2.rentapp.enums.EngineType;
import lv.javaguru.java2.rentapp.enums.TransmissionType;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static lv.javaguru.java2.rentapp.domain.Vehicle.MAX_ALLOWED_CURRENT_YEAR_BACKWARD_REDUCER;

public abstract class AddVehicleValidator {

    public abstract List<CoreError> validate(AddVehicleRequest request);

    protected Optional<CoreError> validateBrand(AddVehicleRequest request) {
        String brand = request.getBrand();
        return (brand == null || brand.isBlank())
                ? Optional.of(new CoreError("Brand", "cannot be empty"))
                : Optional.empty();
    }

    protected Optional<CoreError> validateModel(AddVehicleRequest request) {
        String model = request.getModel();
        return (model == null || model.isBlank())
                ? Optional.of(new CoreError("Model", "cannot be empty"))
                : Optional.empty();
    }

    protected Optional<CoreError> validateYearOfProduction(AddVehicleRequest request) {
        Integer yearOfProduction = request.getYearOfProduction();
        int minYear = LocalDate.now().getYear() - MAX_ALLOWED_CURRENT_YEAR_BACKWARD_REDUCER;
        int currentYear = LocalDate.now().getYear();
        if (yearOfProduction == null) {
            return Optional.of(new CoreError("YearOfProduction", "cannot be empty"));
        } else if (yearOfProduction <= 0) {
            return Optional.of(new CoreError("YearOfProduction", "cannot be negative or zero"));
        } else if (yearOfProduction < minYear) {
            return Optional.of(new CoreError("YearOfProduction", "cannot be lower than " + minYear));
        } else if (yearOfProduction > currentYear) {
            return Optional.of(new CoreError("YearOfProduction", "cannot be more than current year"));
        } else {
            return Optional.empty();
        }
    }

    protected Optional<CoreError> validateColour(AddVehicleRequest request) {
        List<String> enumColourValues = Colour.getAllEnumValues();
        String colour = request.getColour();
        if (colour == null || colour.isBlank()) {
            return Optional.of(new CoreError("Colour", "cannot be empty"));
        } else if (areEnumValuesValid(enumColourValues, colour)) {
            return Optional.empty();
        } else {
            return Optional.of(new CoreError("Colour", "must be one of the provided options (" + enumColourValues + ")"));
        }
    }

    protected Optional<CoreError> validateRentPrice(AddVehicleRequest request) {
        Double rentPrice = request.getRentPricePerDay();
        if (rentPrice == null || rentPrice <= 0) {
            return Optional.of(new CoreError("Rent Price", "cannot be empty, negative or 0"));
        } else {
            return Optional.empty();
        }
    }

    protected Optional<CoreError> validateEngineType(AddVehicleRequest request) {
        List<String> enumEngineTypeValuesExceptNone = getAllEngineTypeValuesExceptNone();
        String engineType = request.getEngineType();
        if (engineType == null || engineType.isBlank()) {
            return Optional.of(new CoreError("Engine Type", "cannot be empty"));
        } else if (areEnumValuesValid(enumEngineTypeValuesExceptNone, engineType)) {
            return Optional.empty();
        } else {
            return Optional.of(new CoreError("Engine Type", "must be one of the provided options (" + enumEngineTypeValuesExceptNone + ")"));
        }
    }

    protected Optional<CoreError> validatePlateNumber(AddVehicleRequest request) {
        String plateNumber = request.getPlateNumber();
        return (plateNumber == null || plateNumber.isBlank())
                ? Optional.of(new CoreError("Plate Number", "cannot be empty"))
                : Optional.empty();
    }

    protected Optional<CoreError> validateTransmissionType(AddVehicleRequest request) {
        List<String> enumTransmissionTypeValuesExceptNone = getAllTransmissionTypeValuesExceptNone();
        String transmissionType = request.getTransmissionType();
        if (transmissionType == null || transmissionType.isBlank()) {
            return Optional.of(new CoreError("Transmission Type", "cannot be empty"));
        } else if (areEnumValuesValid(enumTransmissionTypeValuesExceptNone, transmissionType)) {
            return Optional.empty();
        } else {
            return Optional.of(new CoreError("Transmission Type", "must be one of the provided options (" + enumTransmissionTypeValuesExceptNone + ")"));
        }
    }

    protected boolean areEnumValuesValid(List<String> enumValues, String value) {
        return enumValues.stream()
                .anyMatch(enumValue -> enumValue.equalsIgnoreCase(value.replaceAll("[^a-zA-Z]", "")));
    }

    public <E extends Enum<E>> List<String> getAllEnumsValuesExceptNone(E enumToGetValues) {
        return enumToGetValues.getAllEnumValues().stream()
                .filter(value -> !value.equalsIgnoreCase(TransmissionType.NONE.getNameTransmissionType()))
                .collect(Collectors.toList());
    }

    static List<String> getAllEngineTypeValuesExceptNone() {
        return EngineType.getAllEnumValues().stream()
                .filter(value -> !value.equalsIgnoreCase(TransmissionType.NONE.getNameTransmissionType()))
                .collect(Collectors.toList());
    }

    public <E extends Enum<E>> List<String> getAllEnumValues(E enumToGetValues) {
        return EnumSet.allOf(enumToGetValues.getClass()).stream()
                .map((enumToGetValues.getClass())enumToGetValues -> enumToGetValues.)
                .collect(Collectors.toList());
    }
}
