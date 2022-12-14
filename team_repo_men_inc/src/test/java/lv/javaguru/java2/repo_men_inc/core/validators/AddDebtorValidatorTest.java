package lv.javaguru.java2.repo_men_inc.core.validators;

import lv.javaguru.java2.repo_men_inc.DatabaseCleaner;
import lv.javaguru.java2.repo_men_inc.config.RepoMenIncConfiguration;
import lv.javaguru.java2.repo_men_inc.core.database.Database;
import lv.javaguru.java2.repo_men_inc.core.database.OrmDatabaseImpl;
import lv.javaguru.java2.repo_men_inc.core.domain.Debtor;
import lv.javaguru.java2.repo_men_inc.core.requests.AddDebtorRequest;
import lv.javaguru.java2.repo_men_inc.core.responses.CoreError;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepoMenIncConfiguration.class})
public class AddDebtorValidatorTest {

    protected ApplicationContext appContext =
            new AnnotationConfigApplicationContext(RepoMenIncConfiguration.class);

    Database database = appContext.getBean(OrmDatabaseImpl.class);
    DatabaseCleaner databaseCleaner = appContext.getBean(DatabaseCleaner.class);
    AddDebtorValidator addDebtorValidator = appContext.getBean(AddDebtorValidator.class);

    @Before
    public void setup() {
        databaseCleaner.clean();
    }

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