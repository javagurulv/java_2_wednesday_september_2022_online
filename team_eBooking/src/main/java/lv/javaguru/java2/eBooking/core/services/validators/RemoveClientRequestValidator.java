package lv.javaguru.java2.eBooking.core.services.validators;

import lv.javaguru.java2.eBooking.core.domain.Client;
import lv.javaguru.java2.eBooking.core.requests.client_request.RemoveClientRequest;
import lv.javaguru.java2.eBooking.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RemoveClientRequestValidator {

    public List<CoreError> validate(RemoveClientRequest request){
        List<CoreError> errors = new ArrayList<>();
        validateClientId(request).ifPresent(errors::add);
        return errors;
    }

    public Optional<CoreError> validateClientId(RemoveClientRequest request){
        return request.getRemoveClientId() == null
                ? Optional.of(new CoreError("Client Id",
                    ClientValidationResult.CLIENT_ID_MUST_NOT_BE_EMPTY))
                : Optional.empty();
    }
}
