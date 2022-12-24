package myApp.core.services.validators;

import myApp.core.database.jpa.JpaUserRepository;
import myApp.core.requests.DeleteUserRequest;
import myApp.core.responses.CoreError;
import myApp.web_ui.security.EncoderAndDecoderPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DeleteUserValidator {

    @Autowired
    private JpaUserRepository userRepository;
    @Autowired
    private EncoderAndDecoderPassword encoded;

    public List<CoreError> execute(DeleteUserRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validatePersonalCode(request).ifPresent(errors::add);
        isAccountExistCheck(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validatePersonalCode(DeleteUserRequest request) {
        return request.getPersonalCode() != null
                && !request.getPersonalCode().isBlank()
                ?Optional.empty()
                :Optional.of(new CoreError("Field: Personal code",
                "Personal code must not be empty"));
    }

    private Optional<CoreError> isAccountExistCheck(DeleteUserRequest request) {
        return isAccountExist(request)
                ? Optional.empty()
                : Optional.of(new CoreError(" Account"," does not exist"));
    }
    private boolean isAccountExist(DeleteUserRequest request) {
        String encodedPersonalCode = encoded.executeEncode(request.getPersonalCode());
        return userRepository.findAll().stream()
                .anyMatch(u -> u.getPersonalCode().equals(encodedPersonalCode));
    }
}