package lv.javaguru.java2.repo_men_inc.core.validators;

import lv.javaguru.java2.repo_men_inc.core.requests.SearchDebtorRequest;
import lv.javaguru.java2.repo_men_inc.core.responses.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchDebtorValidator {

    @Autowired
    private PagingValidator pagingValidator;
    @Autowired
    private OrderingValidator orderingValidator;
    @Autowired
    private SearchDebtorFieldValidator searchDebtorFieldValidator;

    public SearchDebtorValidator() {
    }

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
