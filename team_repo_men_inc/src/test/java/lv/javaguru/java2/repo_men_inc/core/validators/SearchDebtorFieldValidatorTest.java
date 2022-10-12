package lv.javaguru.java2.repo_men_inc.core.validators;

import lv.javaguru.java2.repo_men_inc.core.requests.SearchDebtorRequest;
import lv.javaguru.java2.repo_men_inc.core.responses.CoreError;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class SearchDebtorFieldValidatorTest {

    SearchDebtorFieldValidator searchDebtorFieldValidator = new SearchDebtorFieldValidator();

    @Test
    public void shouldReturnErrorIfNAmeAndItemListAreMissing() {
        SearchDebtorRequest searchDebtorRequest = new SearchDebtorRequest(null, null, null, null);
        List<CoreError> errors = searchDebtorFieldValidator.validate(searchDebtorRequest);
        assertEquals(1, errors.size());
        assertEquals(errors.get(0).getField(), "Debtor name and List item");
        assertEquals(errors.get(0).getMessage(), "cannot both be empty!");
    }

    @Test
    public void shouldNotReturnErrorsWhenNameIsProvided() {
        SearchDebtorRequest searchDebtorRequest = new SearchDebtorRequest("name", null, null, null);
        List<CoreError> errors = searchDebtorFieldValidator.validate(searchDebtorRequest);
        assertEquals(0, errors.size());
    }

    @Test
    public void shouldNotReturnErrorsWhenListItemIsProvided() {
        SearchDebtorRequest searchDebtorRequest = new SearchDebtorRequest(null, "item", null, null);
        List<CoreError> errors = searchDebtorFieldValidator.validate(searchDebtorRequest);
        assertEquals(0, errors.size());
    }

    @Test
    public void shouldNotReturnErrorsWhenNameAndListItemAreProvided() {
        SearchDebtorRequest searchDebtorRequest = new SearchDebtorRequest("name", "item", null, null);
        List<CoreError> errors = searchDebtorFieldValidator.validate(searchDebtorRequest);
        assertEquals(0, errors.size());
    }

}