package lv.javaguru.java2.rentapp.core.services.validators;

import lv.javaguru.java2.rentapp.core.requests.GeneralRentVehicleRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class RentVehicleValidator {

    public List<CoreError> validate(GeneralRentVehicleRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateEmail(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateEmail(GeneralRentVehicleRequest request) {
        EmailValidator emailValidator = EmailValidator.getInstance();
        return emailValidator.isValid(request.getEmail())
                ? Optional.empty()
                : Optional.of(new CoreError("E-mail", "not valid format (username@domain.com)"));
    }
}
