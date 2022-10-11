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

        List<String> enumVehicleTypeValues = VehicleType.getAllEnumValues();

        VehicleType vehicleType = request.getVehicleType();
        if (vehicleType == null || vehicleType.getNameVehicleType().isBlank()) {
            return Optional.of(new CoreError("Vehicle Type", "can`t be empty"));
        } else if (areEnumValuesValid(enumVehicleTypeValues, vehicleType.getNameVehicleType())) {
            return Optional.empty();
        } else {
            return Optional.of(new CoreError("Vehicle Type", "must be one of the provided options (" + enumVehicleTypeValues + ")"));
        }
    }

    protected Optional<CoreError> validateTransmissionType(SearchVehicleRequest request) {

        List<String> enumTransmissionTypeValues = TransmissionType.getAllEnumValues();

        Optional<String> transmissionTypeOpt = Optional.ofNullable(request.getTransmissionType());
        if (transmissionTypeOpt.isEmpty()) {
            return Optional.empty();
        }

        String transmissionType = transmissionTypeOpt.get();
        if (transmissionType.isBlank()) {
            return Optional.of(new CoreError("Transmission Type", "cannot be empty"));
        } else if (areEnumValuesValid(enumTransmissionTypeValues, transmissionType)) {
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
            return Optional.of(new CoreError("IsAirConditionerAvailable", "cannot be empty"));
        } else if (isAirConditioningAvailable.equalsIgnoreCase("true")
                || isAirConditioningAvailable.equalsIgnoreCase("false")) {
            return Optional.empty();
        } else {
            return Optional.of(new CoreError("IsAirConditionerAvailable", "must be either true or false"));
        }
    }

    protected boolean areEnumValuesValid(List<String> enumVehicleTypeValues, String vehicleType) {
        return enumVehicleTypeValues.stream()
                .anyMatch(enumVehicleTypeValue -> enumVehicleTypeValue.equalsIgnoreCase(vehicleType.replaceAll("[^a-zA-Z\s]", "")));
    }
}
