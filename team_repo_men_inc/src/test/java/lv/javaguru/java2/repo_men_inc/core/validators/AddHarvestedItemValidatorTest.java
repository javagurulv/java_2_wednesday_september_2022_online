package lv.javaguru.java2.repo_men_inc.core.validators;

import lv.javaguru.java2.repo_men_inc.DatabaseCleaner;
import lv.javaguru.java2.repo_men_inc.config.RepoMenIncConfiguration;
import lv.javaguru.java2.repo_men_inc.core.database.Database;
import lv.javaguru.java2.repo_men_inc.core.database.JdbcDatabaseImpl;
import lv.javaguru.java2.repo_men_inc.core.domain.Debtor;
import lv.javaguru.java2.repo_men_inc.core.requests.AddHarvestedItemRequest;
import lv.javaguru.java2.repo_men_inc.core.responses.CoreError;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigInteger;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepoMenIncConfiguration.class})
public class AddHarvestedItemValidatorTest {

    protected ApplicationContext appContext =
            new AnnotationConfigApplicationContext(RepoMenIncConfiguration.class);

    Database database = appContext.getBean(JdbcDatabaseImpl.class);
    DatabaseCleaner databaseCleaner = appContext.getBean(DatabaseCleaner.class);
    AddHarvestedItemValidator addHarvestedItemValidator = new AddHarvestedItemValidator(database);

    @Before
    public void setup() {
        databaseCleaner.clean();
        database.saveDebtorAndReturnId(1L,"name");
        BigInteger itemInTheListId = database.saveItem("item in the list");
        database.updateList(itemInTheListId, 1L);
    }

    @Test
    public void shouldReturnErrorsIfDebtorIsNotInTheDatabase() {
        AddHarvestedItemRequest addHarvestedItemRequest = new AddHarvestedItemRequest(9999L, "item");
        List<CoreError> errors = addHarvestedItemValidator.validate(addHarvestedItemRequest);
        assertEquals(1, errors.size());
        assertEquals("Debtor ID", errors.get(0).getField());
        assertEquals("Debtor not found!", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorsIfItemIsEmpty() {
        AddHarvestedItemRequest addHarvestedItemRequest = new AddHarvestedItemRequest(1L, "");
        List<CoreError> errors = addHarvestedItemValidator.validate(addHarvestedItemRequest);
        assertEquals(1, errors.size());
        assertEquals("Item", errors.get(0).getField());
        assertEquals("Cannot be empty!", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorsIfItemIsNull() {
        AddHarvestedItemRequest addHarvestedItemRequest = new AddHarvestedItemRequest(1L, null);
        List<CoreError> errors = addHarvestedItemValidator.validate(addHarvestedItemRequest);
        assertEquals(1, errors.size());
        assertEquals("Item", errors.get(0).getField());
        assertEquals("Cannot be empty!", errors.get(0).getMessage());
    }

    @Test
    public void shouldReturnErrorsIfItemIsAlreadyInTheList() {
        AddHarvestedItemRequest addHarvestedItemRequest = new AddHarvestedItemRequest(1L, "item in the list");
        List<CoreError> errors = addHarvestedItemValidator.validate(addHarvestedItemRequest);
        assertEquals(1, errors.size());
        assertEquals("Item", errors.get(0).getField());
        assertEquals("Already in the List!", errors.get(0).getMessage());
    }

    @Test
    public void shouldNotReturnErrorsIfDebtorIsInTheDatabase() {
        AddHarvestedItemRequest addHarvestedItemRequest = new AddHarvestedItemRequest(1L, "item");
        List<CoreError> errors = addHarvestedItemValidator.validate(addHarvestedItemRequest);
        assertEquals(0, errors.size());
    }
}