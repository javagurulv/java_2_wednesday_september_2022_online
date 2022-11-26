package apartment.core.services;

import apartment.Apartment;
import apartment.core.database.Database;
import apartment.core.request.SearchApartmentRequest;
import apartment.core.response.CoreError;
import apartment.core.response.SearchApartmentResponse;

import java.util.List;

public class SearchApartmentService {

    private Database database;

    private SearchApartmentRequestValidator validator;

    public SearchApartmentService (Database database,
                                   SearchApartmentRequestValidator validator){
        this.database = database;
        this.validator = validator;
    }

    public SearchApartmentResponse execute (SearchApartmentRequest request){
        List<CoreError> errors = validator.validate(request);
        if(!errors.isEmpty()){
            return new SearchApartmentResponse(null, errors);
        }

        List<Apartment> apartments = null;
        if(request.isAddressProvided() && request.isRoomsAmountProvided() && request.isFloorProvided() && request.isAreaProvided()){
            apartments = database.findByAddress(request.getAddress());
        }
        if(request.isAddressProvided() && request.isRoomsAmountProvided() && request.isFloorProvided() && request.isAreaProvided()) {
            apartments = database.findByRoomAmount(request.getRoomsAmount());
        }
        if(request.isAddressProvided() && request.isRoomsAmountProvided() && request.isFloorProvided() && request.isAreaProvided()) {
            apartments = database.findByFloor(request.getFloor());
        }
        return new SearchApartmentResponse(apartments, null);
    }


}
