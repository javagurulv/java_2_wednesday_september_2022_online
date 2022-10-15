package myApp.core.services.validators;

import myApp.core.requests.SearchBankAccountRequest;
import myApp.core.responses.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchBankAccountValidator {
    
    @Autowired
    private SearchBankAccountRequestFieldValidator searchBankAccountRequestFieldValidator;
    @Autowired
    private OrderingValidator orderingValidator;
    @Autowired
    private PagingValidator pagingValidator;

    public List<CoreError> validate(SearchBankAccountRequest request) {
        List<CoreError> errors = searchBankAccountRequestFieldValidator.validate(request);
        validateOrdering(request, errors);
        validateOrdering(request, errors);
        validatePaging(request, errors);
        return errors;
    }
    private void validateOrdering(SearchBankAccountRequest request, List<CoreError> errors) {
        if (request.getOrder() != null) {
            errors.addAll(orderingValidator.validateOrdering(request));
        }
    }

    private void validatePaging(SearchBankAccountRequest request, List<CoreError> errors) {
        if (request.getPaging() != null) {
            errors.addAll(pagingValidator.validate(request.getPaging()));
        }
    }
}
