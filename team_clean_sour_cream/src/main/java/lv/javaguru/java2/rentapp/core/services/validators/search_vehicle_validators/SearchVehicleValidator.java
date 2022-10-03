package lv.javaguru.java2.rentapp.core.services.validators.search_vehicle_validators;

import lv.javaguru.java2.rentapp.core.requests.SearchVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.enums.TransmissionType;
import lv.javaguru.java2.rentapp.enums.VehicleType;

import java.util.List;
import java.util.Optional;

public abstract class SearchVehicleValidator {

    public abstract List<CoreError> validate(SearchVehicleRequest request);

    protected Optional<CoreError> validateVehicleType(SearchVehicleRequest request) {
        List<String> enumVehicleTypeValues = VehicleType.getAllEnumValues();
        String vehicleType = request.getVehicleType();
        if (vehicleType == null || vehicleType.isBlank()) {
            return Optional.of(new CoreError("Vehicle Type", "can`t be empty"));
        } else if (areEnumValuesValid(enumVehicleTypeValues, vehicleType)) {
            return Optional.empty();
        } else {
            return Optional.of(new CoreError("Vehicle Type", "must be one of the provided options (" + enumVehicleTypeValues + ")"));
        }
    }

    protected Optional<CoreError> validateTransmissionType(SearchVehicleRequest request) {
        List<String> enumTransmissionTypeValues = TransmissionType.getAllEnumValues();
        String transmissionType = request.getTransmissionType();
        if (transmissionType == null || transmissionType.isBlank()) {
            return Optional.of(new CoreError("Transmission Type", "cannot be empty"));
        } else if (areEnumValuesValid(enumTransmissionTypeValues, transmissionType)) {
            return Optional.empty();
        } else {
            return Optional.of(new CoreError("Transmission Type", "must be one of the provided options (" + enumTransmissionTypeValues + ")"));
        }
    }

    protected boolean areEnumValuesValid(List<String> enumVehicleTypeValues, String vehicleType) {
        return enumVehicleTypeValues.stream()
                .anyMatch(enumVehicleTypeValue -> enumVehicleTypeValue.equalsIgnoreCase(vehicleType.replaceAll("[^a-zA-Z]", "")));
    }
}
