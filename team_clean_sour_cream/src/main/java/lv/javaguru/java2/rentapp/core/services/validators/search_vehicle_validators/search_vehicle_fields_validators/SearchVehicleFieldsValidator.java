package lv.javaguru.java2.rentapp.core.services.validators.search_vehicle_validators.search_vehicle_fields_validators;

import lv.javaguru.java2.rentapp.core.requests.SearchVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.enums.TransmissionType;
import lv.javaguru.java2.rentapp.enums.VehicleType;

import java.util.List;
import java.util.Optional;

public abstract class SearchVehicleFieldsValidator {

    public abstract List<CoreError> validate(SearchVehicleRequest request);

    protected Optional<CoreError> validateVehicleType(SearchVehicleRequest request) {

        VehicleType vehicleType = request.getVehicleType();
        if (vehicleType == null) {
            return Optional.of(new CoreError("Vehicle Type", "can`t be null (should be provided)"));
        } else {
            return Optional.empty();
        }
    }

    protected Optional<CoreError> validateTransmissionType(SearchVehicleRequest request) {

        List<String> enumTransmissionTypeValues = TransmissionType.getAllEnumValuesExceptNone();

        Optional<String> transmissionTypeOpt = Optional.ofNullable(request.getTransmissionType());
        if (transmissionTypeOpt.isEmpty()) {
            return Optional.empty();
        }

        String transmissionType = transmissionTypeOpt.get();
        if (transmissionType.isBlank()) {
            return Optional.of(new CoreError("Transmission Type", "can`t be empty or blank"));
        } else if (isEnumVehicleValueValid(enumTransmissionTypeValues, transmissionType)) {
            return Optional.empty();
        } else {
            return Optional.of(new CoreError("Transmission Type", "must be one of the provided options (" + enumTransmissionTypeValues + ")"));
        }
    }

    protected Optional<CoreError> validateIsAirConditionerAvailable(SearchVehicleRequest request) {

        Optional<String> isAirConditioningAvailableOpt = Optional.ofNullable(request.getHasConditioner());

        if (isAirConditioningAvailableOpt.isEmpty()) {
            return Optional.empty();
        }

        String isAirConditioningAvailable = isAirConditioningAvailableOpt.get();
        if (isAirConditioningAvailable.isBlank()) {
            return Optional.of(new CoreError("IsAirConditionerAvailable", "can`t be empty or blank"));
        } else if (isAirConditioningAvailable.replaceAll("[^a-zA-Z]", "").equalsIgnoreCase("true")
                || isAirConditioningAvailable.replaceAll("[^a-zA-Z]", "").equalsIgnoreCase("false")) {
            return Optional.empty();
        } else {
            return Optional.of(new CoreError("IsAirConditionerAvailable", "must be either true or false"));
        }
    }

    protected boolean isEnumVehicleValueValid(List<String> enumVehicleValues, String value) {
        return enumVehicleValues.stream()
                .anyMatch(enumVehicleTypeValue -> enumVehicleTypeValue.equalsIgnoreCase(value.replaceAll("[^a-zA-Z]", "")));
    }
}
