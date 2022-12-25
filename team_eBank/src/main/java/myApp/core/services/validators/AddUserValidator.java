package myApp.core.services.validators;

import myApp.core.database.jpa.JpaUserRepository;
import myApp.core.domain.User;
import myApp.core.requests.AddUserRequest;
import myApp.core.responses.CoreError;
import myApp.web_ui.security.EncoderAndDecoderPassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class AddUserValidator {

    @Autowired
    private JpaUserRepository userRepository;
    @Autowired
    private EncoderAndDecoderPassword encoder;

    public List<CoreError> validate(AddUserRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validatePersonalCode(request).ifPresent(errors::add);
        validatePassword(request).ifPresent(errors::add);
        duplicateCheckResult(request).ifPresent(errors::add);
        return errors;

    }

    private Optional<CoreError> validatePersonalCode(AddUserRequest request) {
        return request.getPersonalCode() != null
                && !request.getPersonalCode().isBlank()
                ? Optional.empty()
                : Optional.of(new CoreError("Field: Personal code ",
                "Personal code can only contain letters and must not be empty"));
    }

    private Optional<CoreError> validatePassword(AddUserRequest request) {
        return request.getPassword() != null
                && !request.getPassword().isBlank()
                ? Optional.empty()
                : Optional.of(new CoreError("Field: Password ",
                "Password can only contain letters and must not be empty"));
    }

    private Optional<CoreError> duplicateCheckResult(AddUserRequest request) {
        return !duplicateCheck(request)
                ? Optional.empty()
                : Optional.of(new CoreError("Duplicate,",
                "this user already exists"));
    }

    private boolean duplicateCheck(AddUserRequest request) {
        String encodedPersonalCode = encoder.executeEncode(request.getPersonalCode());
        return userRepository.findAll().stream()
                .anyMatch(u -> u.getPersonalCode().equals(encodedPersonalCode));
    }
}