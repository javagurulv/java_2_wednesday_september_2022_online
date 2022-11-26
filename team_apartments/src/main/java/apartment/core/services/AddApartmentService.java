package apartment.core.services;

import apartment.Apartment;
import apartment.core.request.AddApartmentRequest;
import apartment.core.database.Database;
import apartment.core.response.AddApartmentResponse;
import apartment.core.response.CoreError;

import java.util.List;

public class AddApartmentService {

    private Database database;
    private AddApartmentValidator validator;

    public AddApartmentService(Database database,
                               AddApartmentValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public AddApartmentResponse execute(AddApartmentRequest request){
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddApartmentResponse(errors);
        }

        Apartment apartment = new Apartment(request.getAddress(),request.getRoomsAmount(), request.getFloor(), request.getArea());
        database.save(apartment);

        return new AddApartmentResponse(apartment);
    }





}
