package lv.javaguru.java2.repo_men_inc.core.validators;

import lv.javaguru.java2.repo_men_inc.core.requests.Paging;
import lv.javaguru.java2.repo_men_inc.core.responses.CoreError;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class PagingValidatorTest {

    PagingValidator pagingValidator = new PagingValidator();

    @Test
    public void shouldReturnErrorsIfPagingPageNumberIsEqualToZero() {
        Paging paging = new Paging(0, 10);
        List<CoreError> errors = pagingValidator.validate(paging);
        assertEquals(1, errors.size());
        assertEquals(errors.get(0).getField(), "PageNumber");
        assertEquals(errors.get(0).getMessage(), "Must be greater than zero!");
    }

    @Test
    public void shouldReturnErrorsIfPagingPageNumberIsLessThanZero() {
        Paging paging = new Paging(-1, 10);
        List<CoreError> errors = pagingValidator.validate(paging);
        assertEquals(1, errors.size());
        assertEquals(errors.get(0).getField(), "PageNumber");
        assertEquals(errors.get(0).getMessage(), "Must be greater than zero!");
    }

    @Test
    public void shouldReturnErrorsIfPagingPageSizeIsEqualToZero() {
        Paging paging = new Paging(1, 0);
        List<CoreError> errors = pagingValidator.validate(paging);
        assertEquals(1, errors.size());
        assertEquals(errors.get(0).getField(), "PageSize");
        assertEquals(errors.get(0).getMessage(), "Must be greater than zero!");
    }

    @Test
    public void shouldReturnErrorsIfPagingPageSizeIsLessThanZero() {
        Paging paging = new Paging(1, -1);
        List<CoreError> errors = pagingValidator.validate(paging);
        assertEquals(1, errors.size());
        assertEquals(errors.get(0).getField(), "PageSize");
        assertEquals(errors.get(0).getMessage(), "Must be greater than zero!");
    }

    @Test
    public void shouldReturnErrorsIfPagingPageNumberIsMissing() {
        Paging paging = new Paging(null, 10);
        List<CoreError> errors = pagingValidator.validate(paging);
        assertEquals(1, errors.size());
        assertEquals(errors.get(0).getField(), "PageSize");
        assertEquals(errors.get(0).getMessage(), "Cannot be empty if PageNumber is provided!");
    }

    @Test
    public void shouldReturnErrorsIfPagingPageSizeIsMissing() {
        Paging paging = new Paging(1, null);
        List<CoreError> errors = pagingValidator.validate(paging);
        assertEquals(1, errors.size());
        assertEquals(errors.get(0).getField(), "PageNumber");
        assertEquals(errors.get(0).getMessage(), "Cannot be empty if PageSize is provided!");
    }

}