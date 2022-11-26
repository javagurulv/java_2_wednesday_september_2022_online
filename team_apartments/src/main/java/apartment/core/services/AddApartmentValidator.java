package apartment.core.services;

import apartment.core.request.AddApartmentRequest;
import apartment.core.response.CoreError;
import com.sun.jdi.LongValue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddApartmentValidator {

    public List<CoreError> validate (AddApartmentRequest request){
        List<CoreError> errors = new ArrayList<>();
        validateAddress(request).ifPresent(errors::add);
        validateApartmentRoomsAmount(request).ifPresent(errors::add);
        validateApartmentFloor(request).ifPresent(errors::add);
        validateApartmentArea(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateAddress(AddApartmentRequest request) {
        return (request.getAddress() == null || request.getAddress().isBlank())
                ? Optional.of(new CoreError("Address", "Must not be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateApartmentRoomsAmount (AddApartmentRequest request){
        return (request.getRoomsAmount() == null)
                ? Optional.of(new CoreError("Rooms Amount","Must not be empty or null!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateApartmentFloor (AddApartmentRequest request){
        return (request.getFloor() == null)
                ? Optional.of(new CoreError("Floor","Must not be empty or null!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateApartmentArea (AddApartmentRequest request){
        return (request.getArea() == null)
                ? Optional.of(new CoreError("Area","Must not be empty or null!"))
                : Optional.empty();
    }

}
