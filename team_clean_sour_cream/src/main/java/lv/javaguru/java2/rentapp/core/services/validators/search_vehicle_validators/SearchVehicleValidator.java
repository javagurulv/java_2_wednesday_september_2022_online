package lv.javaguru.java2.rentapp.core.services.validators.search_vehicle_validators;

import lv.javaguru.java2.rentapp.core.requests.SearchVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.core.services.validators.search_vehicle_validators.search_vehicle_fields_validators.SearchVehicleFieldsValidator;
import lv.javaguru.java2.rentapp.core.services.validators.search_vehicle_validators.search_vehicle_fields_validators.SearchVehicleFieldsValidatorMap;
import lv.javaguru.java2.rentapp.enums.VehicleType;

import java.util.ArrayList;
import java.util.List;

public class SearchVehicleValidator {

    private SearchVehicleFieldsValidatorMap searchVehicleFieldsValidatorMap;
    private SearchVehicleRequestOrderingValidator searchVehicleRequestOrderingValidator;
    private SearchVehicleRequestPagingValidator searchVehicleRequestPagingValidator;

    public SearchVehicleValidator(SearchVehicleFieldsValidatorMap searchVehicleFieldsValidatorMap,
                                  SearchVehicleRequestOrderingValidator searchVehicleRequestOrderingValidator,
                                  SearchVehicleRequestPagingValidator searchVehicleRequestPagingValidator) {
        this.searchVehicleFieldsValidatorMap = searchVehicleFieldsValidatorMap;
        this.searchVehicleRequestOrderingValidator = searchVehicleRequestOrderingValidator;
        this.searchVehicleRequestPagingValidator = searchVehicleRequestPagingValidator;
    }

    public List<CoreError> validate(SearchVehicleRequest request) {

        List<CoreError> errors = new ArrayList<>();
        VehicleType vehicleType = request.getVehicleType();

        if (vehicleType == null) {
            errors.add(new CoreError("Vehicle Type", "can`t be null (should be provided)"));
        } else {
            SearchVehicleFieldsValidator searchVehicleFieldsValidator = searchVehicleFieldsValidatorMap.getVehicleValidatorByCarType(vehicleType);
            errors.addAll(searchVehicleFieldsValidator.validate(request));
        }
        if (request.getOrdering() != null) {
            errors.addAll(searchVehicleRequestOrderingValidator.validate(request.getOrdering()));
        }
        if (request.getPaging() != null) {
            errors.addAll(searchVehicleRequestPagingValidator.validate(request.getPaging()));
        }
        return errors;
    }
}
