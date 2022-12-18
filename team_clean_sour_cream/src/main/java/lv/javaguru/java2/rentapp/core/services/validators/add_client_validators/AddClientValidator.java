package lv.javaguru.java2.rentapp.core.services.validators.add_client_validators;

import lv.javaguru.java2.rentapp.core.database.ClientDatabase;
import lv.javaguru.java2.rentapp.core.requests.AddClientRequest;
import lv.javaguru.java2.rentapp.core.responses.CoreError;
import lv.javaguru.java2.rentapp.domain.Client;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class AddClientValidator {

    @Autowired
    private ClientDatabase clientDatabase;

    public List<CoreError> validate(AddClientRequest request) {
        List<CoreError> errors = new ArrayList<>();

        validatePersonalIdNotEmpty(request).ifPresent(errors::add);
        if (validatePersonalIdNotEmpty(request).isEmpty()) {
            validatePersonalIdFormat(request).ifPresent(errors::add);
        }

        validateFirstNameNotEmpty(request).ifPresent(errors::add);
        if (validateFirstNameNotEmpty(request).isEmpty()) {
            validateFirstNameFormat(request).ifPresent(errors::add);
        }

        validateLastNameNotEmpty(request).ifPresent(errors::add);
        if (validateLastNameNotEmpty(request).isEmpty()) {
            validateLastNameFormat(request).ifPresent(errors::add);
        }

        validateEmailNotEmpty(request).ifPresent(errors::add);
        if (validateEmailNotEmpty(request).isEmpty()) {
            validateEmailFormat(request).ifPresent(errors::add);
        }

        validatePhoneNumberNotEmpty(request).ifPresent(errors::add);
        if (validatePhoneNumberNotEmpty(request).isEmpty()) {
            validatePhoneNumberFormat(request).ifPresent(errors::add);
        }

        return errors;
    }

    private Optional<CoreError> validateEmailNotEmpty(AddClientRequest request) {
        return validateStringNotEmpty("Email", request.getEmail());
    }

    private Optional<CoreError> validateEmailFormat(AddClientRequest request) {
        EmailValidator emailValidator = EmailValidator.getInstance();
        return emailValidator.isValid(request.getEmail())
                ? Optional.empty()
                : Optional.of(new CoreError("Email", "not valid format (username@domain.com)"));
    }

    private Optional<CoreError> validatePersonalIdNotEmpty(AddClientRequest request) {
        return validateStringNotEmpty("Personal ID", request.getPersonalId());
    }

    private Optional<CoreError> validatePersonalIdFormat(AddClientRequest request) {
        Pattern pattern = Pattern.compile("[0-9]{6}-[0-9]{5}");
        Matcher matcher = pattern.matcher(request.getPersonalId());
        return !matcher.matches()
                ? Optional.of(new CoreError("Personal ID", "Has to be in format 000000-00000"))
                : Optional.empty();
    }

    private Optional<CoreError> validateFirstNameNotEmpty(AddClientRequest request) {
        return validateStringNotEmpty("First name", request.getFirstName());
    }

    private Optional<CoreError> validateFirstNameFormat(AddClientRequest request) {
        String firstName = request.getFirstName().replaceAll("[a-zA-Z-\s]", "");
        return !firstName.isBlank()
                ? Optional.of(new CoreError("First name", "cannot contain digits or special characters"))
                : Optional.empty();
    }

    private Optional<CoreError> validateLastNameNotEmpty(AddClientRequest request) {
        return validateStringNotEmpty("Last name", request.getLastName());
    }

    private Optional<CoreError> validateLastNameFormat(AddClientRequest request) {
        String lastName = request.getLastName().replaceAll("[a-zA-Z-]", "");
        return !lastName.isBlank()
                ? Optional.of(new CoreError("Last name", "cannot contain digits or special characters"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePhoneNumberNotEmpty(AddClientRequest request) {
        return validateStringNotEmpty("Phone number", request.getPhoneNumber());
    }

    private Optional<CoreError> validatePhoneNumberFormat(AddClientRequest request) {
        String phoneNumber = request.getPhoneNumber().replaceAll("[+0-9]", "");
        return !phoneNumber.isBlank()
                ? Optional.of(new CoreError("Phone number", "cannot contain letters or special characters"))
                : Optional.empty();
    }

    private Optional<CoreError> validateStringNotEmpty(String field, String value) {
        return (value == null || value.isBlank())
                ? Optional.of(new CoreError(field, "cannot be empty"))
                : Optional.empty();
    }

    private Optional<CoreError> validateDuplicateClientFirstName(AddClientRequest request) {
        Client existedClient = getExistedClientByPersonalIdIfPresent(request);
        if (existedClient != null) {
            if (!existedClient.getFirstName().equals(request.getFirstName())) {
                return Optional.of(new CoreError("First Name", "different from client with same personal ID"));
            }
        }
        return Optional.empty();
    }

    private Client getExistedClientByPersonalIdIfPresent(AddClientRequest request) {
        return clientDatabase.findByPersonalId(request.getPersonalId()).isPresent()
                ? clientDatabase.findByPersonalId(request.getPersonalId()).get()
                : null;
    }

    private Client getExistedClientByEmailIfPresent(AddClientRequest request) {
        return clientDatabase.findByEmail(request.getEmail()).isPresent()
                ? clientDatabase.findByEmail(request.getEmail()).get()
                : null;
    }
}

