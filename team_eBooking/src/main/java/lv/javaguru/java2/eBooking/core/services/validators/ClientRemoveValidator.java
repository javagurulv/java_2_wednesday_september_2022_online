package lv.javaguru.java2.eBooking.core.services.validators;

import lv.javaguru.java2.eBooking.core.requests.client_request.ClientRemoveRequest;
import lv.javaguru.java2.eBooking.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Component
public class ClientRemoveValidator {

    public List<CoreError> validate(ClientRemoveRequest request){
        List<CoreError> errors = new ArrayList<>();
        validateClientId(request).ifPresent(errors::add);
        return errors;
    }

    public Optional<CoreError> validateClientId(ClientRemoveRequest request){
        return request.getRemoveClientId() == null
                ? Optional.of(new CoreError("Client Id",
                    ClientValidationResult.CLIENT_ID_MUST_NOT_BE_EMPTY))
                : Optional.empty();
    }
}
