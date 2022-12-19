package apartment.core.services;

import apartment.core.request.SearchApartmentRequest;
import apartment.core.response.CoreError;

import java.util.ArrayList;
import java.util.List;

public class SearchApartmentRequestValidator {



    public List<CoreError> validate (SearchApartmentRequest request){
        List<CoreError> errors = new ArrayList<>();
        errors.addAll(validateSearchFields(request));
        return errors;
    }

    private List<CoreError> validateSearchFields(SearchApartmentRequest request){
        List<CoreError> errors = new ArrayList<>();
        if (isEmpty(request.getAddress()) && isEmptyForNumbers(request.getRoomsAmount()) && isEmptyForNumbers(request.getFloor()) && isEmptyForNumbers(request.getArea())){
            errors.add(new CoreError("Address", "Must not be empty"));
            errors.add(new CoreError("Room amount", "Must not be empty"));
            errors.add(new CoreError("Floor", "Must not be empty"));
            errors.add(new CoreError("Area", "Must not be empty"));
        }
        return errors;
    }

    private boolean isEmpty(String str1) {
        return str1 == null || str1.isEmpty();
    }


    private boolean isEmptyForNumbers(Long str2) {
        return str2 == null;
    }
}
