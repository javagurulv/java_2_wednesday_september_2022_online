package lv.javaguru.java2.rentapp.core.services.validators.search_vehicle_validators;

import lv.javaguru.java2.rentapp.core.requests.AddVehicleRequest;
import lv.javaguru.java2.rentapp.core.requests.SearchVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.enums.TransmissionType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SearchPassengerCarValidator extends SearchVehicleValidator {

    @Override
    public List<CoreError> validate(SearchVehicleRequest request) {
        List<CoreError> errors = new ArrayList<>();

        validateVehicleType(request).ifPresent(errors::add);


        return errors;
    }



}
