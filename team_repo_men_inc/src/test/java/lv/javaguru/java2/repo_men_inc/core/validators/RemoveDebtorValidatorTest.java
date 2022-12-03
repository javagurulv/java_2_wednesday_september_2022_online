package lv.javaguru.java2.repo_men_inc.core.validators;

import lv.javaguru.java2.repo_men_inc.config.RepoMenIncConfiguration;
import lv.javaguru.java2.repo_men_inc.core.database.Database;
import lv.javaguru.java2.repo_men_inc.core.database.JdbcDatabaseImpl;
import lv.javaguru.java2.repo_men_inc.core.requests.RemoveDebtorRequest;
import lv.javaguru.java2.repo_men_inc.core.responses.CoreError;
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
public class RemoveDebtorValidatorTest {

    protected ApplicationContext appContext =
            new AnnotationConfigApplicationContext(RepoMenIncConfiguration.class);

    Database database = appContext.getBean(JdbcDatabaseImpl.class);
    RemoveDebtorValidator removeDebtorValidator = appContext.getBean(RemoveDebtorValidator.class);

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
        database.saveDebtorAndReturnId(1L, "name");
        RemoveDebtorRequest removeDebtorRequest = new RemoveDebtorRequest(1L);
        List<CoreError> errors = removeDebtorValidator.validate(removeDebtorRequest);
        System.out.println(errors);
        assertEquals(0, errors.size());
    }
}