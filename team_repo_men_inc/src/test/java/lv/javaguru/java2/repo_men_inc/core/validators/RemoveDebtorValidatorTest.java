package lv.javaguru.java2.repo_men_inc.core.validators;

import lv.javaguru.java2.repo_men_inc.domain.Debtor;
import lv.javaguru.java2.repo_men_inc.core.requests.RemoveDebtorRequest;
import lv.javaguru.java2.repo_men_inc.core.responses.CoreError;
import lv.javaguru.java2.repo_men_inc.database.Database;
import lv.javaguru.java2.repo_men_inc.database.DatabaseImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class RemoveDebtorValidatorTest {

    Database database = new DatabaseImpl();
    RemoveDebtorValidator removeDebtorValidator = new RemoveDebtorValidator(database);

    @Test
    public void shouldReturnErrorsIfIdIsEmpty() {
        RemoveDebtorRequest removeDebtorRequest = new RemoveDebtorRequest(null);
        List<CoreError> errors = removeDebtorValidator.validate(removeDebtorRequest);
        assertEquals(2, errors.size());
        assertEquals("ID", errors.get(0).getField());
        assertEquals("Cannot be empty!", errors.get(0).getMessage());
        assertEquals("ID", errors.get(1).getField());
        assertEquals("Debtor not found!", errors.get(1).getMessage());
    }

    @Test
    public void shouldReturnErrorsIfIDebtorIsNotInTheDatabase() {
        RemoveDebtorRequest removeDebtorRequest = new RemoveDebtorRequest(1L);
        List<CoreError> errors = removeDebtorValidator.validate(removeDebtorRequest);
        assertEquals(1, errors.size());
        assertEquals("ID", errors.get(0).getField());
        assertEquals("Debtor not found!", errors.get(0).getMessage());
    }

    @Test
    public void shouldNotReturnErrors() {
        database.save(new Debtor("name"));
        RemoveDebtorRequest removeDebtorRequest = new RemoveDebtorRequest(1L);
        List<CoreError> errors = removeDebtorValidator.validate(removeDebtorRequest);
        assertEquals(0, errors.size());
    }
}