package myApp.core.services.validators;

import myApp.core.requests.SearchBankAccountRequest;
import myApp.core.responses.CoreError;
import myApp.dependency_injection.DIComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@DIComponent
public class SearchBankAccountRequestFieldValidator {

    public List<CoreError> validate(SearchBankAccountRequest request) {
        List<CoreError> errors = new ArrayList<>();
        if (isEmpty(request.getName()) && isEmpty(request.getSurname()) && isEmpty(request.getPersonalCode())) {
            errors.add(new CoreError("Name", "Name may contains only letters " +
                    "and cannot be empty"));
            errors.add(new CoreError("Surname", "Surname may contains only letters " +
                    "and cannot be empty"));
            errors.add(new CoreError("Personal code", "Personal code may contains only numbers " +
                    "and cannot be empty"));
        }
        return errors;
    }
    private boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }
}
