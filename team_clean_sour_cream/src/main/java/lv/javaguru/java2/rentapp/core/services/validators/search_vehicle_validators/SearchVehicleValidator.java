package lv.javaguru.java2.rentapp.core.services.validators.search_vehicle_validators;

import lv.javaguru.java2.rentapp.core.requests.SearchVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.core.services.validators.search_vehicle_validators.search_vehicle_fields_validators.SearchVehicleFieldsValidatorMap;
import lv.javaguru.java2.rentapp.enums.VehicleType;

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
        VehicleType vehicleType = request.getVehicleType();
        List<CoreError> errors = searchVehicleFieldsValidatorMap.getVehicleValidatorByCarType(vehicleType).validate(request);
        if (request.getOrdering() != null) {
            errors.addAll(searchVehicleRequestOrderingValidator.validate(request.getOrdering()));
        }
        if (request.getPaging() != null) {
            errors.addAll(searchVehicleRequestPagingValidator.validate(request.getPaging()));
        }
        return errors;
    }
}
