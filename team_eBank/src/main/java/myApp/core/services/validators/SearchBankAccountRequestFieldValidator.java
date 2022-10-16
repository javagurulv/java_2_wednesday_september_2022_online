package myApp.core.services.validators;

import myApp.core.requests.SearchBankAccountRequest;
import myApp.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
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
