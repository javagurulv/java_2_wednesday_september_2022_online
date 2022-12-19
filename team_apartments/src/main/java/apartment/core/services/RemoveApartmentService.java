package apartment.core.services;

import apartment.core.database.Database;
import apartment.core.request.RemoveApartmentRequest;
import apartment.core.response.CoreError;
import apartment.core.response.RemoveApartmentResponse;

import java.util.ArrayList;
import java.util.List;

public class RemoveApartmentService {

    private Database database;

    public RemoveApartmentService (Database database){
        this.database = database;
    }

    public RemoveApartmentResponse execute (RemoveApartmentRequest request){
        if (request.getApartmentIdToRemove() == null){
            CoreError error = new CoreError("Apartment's ID", "Must not be empty!");
            List<CoreError> errors = new ArrayList<>();
            errors.add(error);
            return new RemoveApartmentResponse(errors);
        }

        boolean isApartmentRemoved = database.deleteById(request.getApartmentIdToRemove());
        return new RemoveApartmentResponse(isApartmentRemoved);
    }

}
