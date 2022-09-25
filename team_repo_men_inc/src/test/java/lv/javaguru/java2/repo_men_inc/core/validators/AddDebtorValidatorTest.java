package lv.javaguru.java2.repo_men_inc.core.validators;

import lv.javaguru.java2.repo_men_inc.domain.Debtor;
import lv.javaguru.java2.repo_men_inc.core.requests.AddDebtorRequest;
import lv.javaguru.java2.repo_men_inc.core.responses.CoreError;
import lv.javaguru.java2.repo_men_inc.database.Database;
import lv.javaguru.java2.repo_men_inc.database.DatabaseImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AddDebtorValidatorTest {

    Database database = new DatabaseImpl();
    AddDebtorValidator addDebtorValidator = new AddDebtorValidator(database);

    @Test
    public void shouldReturnErrorsIfNameIsEmpty() {
        AddDebtorRequest addDebtorRequest = new AddDebtorRequest("");
        List<CoreError> errors = addDebtorValidator.validate(addDebtorRequest);
        assertEquals(1, errors.size());
        assertEquals(errors.get(0).getField(), "Name");
        assertEquals(errors.get(0).getMessage(), "Cannot be empty!");
    }

    @Test
    public void shouldReturnErrorsIfNameIsNull() {
        AddDebtorRequest addDebtorRequest = new AddDebtorRequest(null);
        List<CoreError> errors = addDebtorValidator.validate(addDebtorRequest);
        assertEquals(1, errors.size());
        assertEquals("Name", errors.get(0).getField());
        assertEquals("Cannot be empty!", errors.get(0).getMessage());
    }

    @Test
    public void shouldNotReturnErrorsIfNameIsNotEmptyOrNull() {
        AddDebtorRequest addDebtorRequest = new AddDebtorRequest("name");
        List<CoreError> errors = addDebtorValidator.validate(addDebtorRequest);
        assertEquals(0, errors.size());
    }

    @Test
    public void shouldReturnErrorsIfNameAlreadyInTheDatabase() {
        database.save(new Debtor("existing name"));
        AddDebtorRequest addDebtorRequest = new AddDebtorRequest("existing name");
        List<CoreError> errors = addDebtorValidator.validate(addDebtorRequest);
        assertEquals(1, errors.size());
        assertEquals("Name", errors.get(0).getField());
        assertEquals("Already exist", errors.get(0).getMessage());
    }

    @Test
    public void shouldNotReturnErrorsIfNameIsNotInTheDatabase() {
        AddDebtorRequest addDebtorRequest = new AddDebtorRequest("name");
        List<CoreError> errors = addDebtorValidator.validate(addDebtorRequest);
        assertEquals(0, errors.size());
    }
}