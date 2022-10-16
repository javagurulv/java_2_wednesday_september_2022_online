package lv.javaguru.java2.repo_men_inc.core.validators;

import lv.javaguru.java2.repo_men_inc.core.requests.SearchDebtorRequest;
import lv.javaguru.java2.repo_men_inc.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class SearchDebtorValidator {

    private final PagingValidator pagingValidator;
    private final OrderingValidator orderingValidator;
    private final SearchDebtorFieldValidator searchDebtorFieldValidator;

    public SearchDebtorValidator(PagingValidator pagingValidator, OrderingValidator orderingValidator, SearchDebtorFieldValidator searchDebtorFieldValidator) {
        this.pagingValidator = pagingValidator;
        this.orderingValidator = orderingValidator;
        this.searchDebtorFieldValidator = searchDebtorFieldValidator;
    }

    public List<CoreError> validate(SearchDebtorRequest searchDebtorRequest) {

        List<CoreError> errors = new ArrayList<>(searchDebtorFieldValidator.validate(searchDebtorRequest));

        if (searchDebtorRequest.getOrdering() != null) {
            errors.addAll(orderingValidator.validate(searchDebtorRequest.getOrdering()));
        }

        if (searchDebtorRequest.getPaging() != null) {
            errors.addAll(pagingValidator.validate(searchDebtorRequest.getPaging()));
        }

        return errors;
    }

}
