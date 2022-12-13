package lv.javaguru.java2.repo_men_inc.core.validators;

import lv.javaguru.java2.repo_men_inc.core.requests.SearchDebtorRequest;
import lv.javaguru.java2.repo_men_inc.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SearchDebtorFieldValidator {

    public List<CoreError> validate(SearchDebtorRequest searchDebtorRequest) {
        List<CoreError> errors = new ArrayList<>();
        validateSearchParameters(searchDebtorRequest).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateSearchParameters(SearchDebtorRequest searchDebtorRequest) {
        return isEmpty(searchDebtorRequest.getName()) && isEmpty(searchDebtorRequest.getListItem())
                ? Optional.of(new CoreError("Debtor name and List item", "cannot both be empty!"))
                : Optional.empty();
    }

    private boolean isEmpty(String string) {
        return string == null || string.isEmpty();
    }
}
