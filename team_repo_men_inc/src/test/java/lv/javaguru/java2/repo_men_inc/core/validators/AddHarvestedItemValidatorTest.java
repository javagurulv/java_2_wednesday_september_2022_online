package lv.javaguru.java2.repo_men_inc.core.validators;

import lv.javaguru.java2.repo_men_inc.domain.Debtor;
import lv.javaguru.java2.repo_men_inc.core.requests.AddHarvestedItemRequest;
import lv.javaguru.java2.repo_men_inc.core.responses.CoreError;
import lv.javaguru.java2.repo_men_inc.database.Database;
import lv.javaguru.java2.repo_men_inc.database.DatabaseImpl;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AddHarvestedItemValidatorTest {

    Database database = new DatabaseImpl();
    AddHarvestedItemValidator addHarvestedItemValidator = new AddHarvestedItemValidator(database);

    @Test
    public void shouldReturnErrorsIfDebtorIsNotInTheDatabase() {
        AddHarvestedItemRequest addHarvestedItemRequest = new AddHarvestedItemRequest(1L, "item");
        List<CoreError> errors = addHarvestedItemValidator.validate(addHarvestedItemRequest);
        assertEquals(1, errors.size());
        assertEquals("Debtor ID", errors.get(0).getField());
        assertEquals("Debtor not found!", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorsIfItemIsEmpty() {
        database.save(new Debtor("name"));
        AddHarvestedItemRequest addHarvestedItemRequest = new AddHarvestedItemRequest(1L, "");
        List<CoreError> errors = addHarvestedItemValidator.validate(addHarvestedItemRequest);
        assertEquals(1, errors.size());
        assertEquals("Item", errors.get(0).getField());
        assertEquals("Cannot be empty!", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorsIfItemIsNull() {
        database.save(new Debtor("name"));
        AddHarvestedItemRequest addHarvestedItemRequest = new AddHarvestedItemRequest(1L, null);
        List<CoreError> errors = addHarvestedItemValidator.validate(addHarvestedItemRequest);
        assertEquals(1, errors.size());
        assertEquals("Item", errors.get(0).getField());
        assertEquals("Cannot be empty!", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorsIfItemIsAlreadyInTheList() {
        Debtor debtor = new Debtor("name");
        debtor.getList().add("item in the list");
        database.save(debtor);
        AddHarvestedItemRequest addHarvestedItemRequest = new AddHarvestedItemRequest(1L, "item in the list");
        List<CoreError> errors = addHarvestedItemValidator.validate(addHarvestedItemRequest);
        assertEquals(1, errors.size());
        assertEquals("Item", errors.get(0).getField());
        assertEquals("Already in the List!", errors.get(0).getMessage());
    }

    @Test
    public void shouldNotReturnErrorsIfDebtorIsInTheDatabase() {
        database.save(new Debtor("name"));
        AddHarvestedItemRequest addHarvestedItemRequest = new AddHarvestedItemRequest(1L, "item");
        List<CoreError> errors = addHarvestedItemValidator.validate(addHarvestedItemRequest);
        assertEquals(0, errors.size());
    }
}