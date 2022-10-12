package lv.javaguru.java2.repo_men_inc.services;

import lv.javaguru.java2.repo_men_inc.core.requests.AddDebtorRequest;
import lv.javaguru.java2.repo_men_inc.core.responses.AddDebtorResponse;
import lv.javaguru.java2.repo_men_inc.core.responses.CoreError;
import lv.javaguru.java2.repo_men_inc.core.validators.AddDebtorValidator;
import lv.javaguru.java2.repo_men_inc.database.Database;
import lv.javaguru.java2.repo_men_inc.matchers.DebtorMatcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AddDebtorServiceTest {

    @Mock
    private Database database;
    @Mock
    private AddDebtorValidator addDebtorValidator;
    @InjectMocks
    private AddDebtorService addDebtorService;

    @Test
    public void shouldReturnResponseWithErrorsWhenValidationFails() {
        AddDebtorRequest invalidAddDebtorRequest = new AddDebtorRequest(null);
        when(addDebtorValidator.validate(invalidAddDebtorRequest)).thenReturn(List.of(new CoreError("name", "cannot be empty!")));
        AddDebtorResponse addDebtorResponse = addDebtorService.execute(invalidAddDebtorRequest);
        assertTrue(addDebtorResponse.hasErrors());
    }

    @Test
    public void shouldReturnResponseWithoutErrorsWhenValidationPasses() {
        AddDebtorRequest validAddDebtorRequest = new AddDebtorRequest("null");
        when(addDebtorValidator.validate(validAddDebtorRequest)).thenReturn(List.of());
        AddDebtorResponse addDebtorResponse = addDebtorService.execute(validAddDebtorRequest);
        assertFalse(addDebtorResponse.hasErrors());
    }

    @Test
    public void shouldReturnResponseWithErrorsFromValidatorWhenValidationFails() {
        AddDebtorRequest invalidAddDebtorRequest = new AddDebtorRequest(null);
        when(addDebtorValidator.validate(invalidAddDebtorRequest)).thenReturn(List.of(new CoreError("name", "cannot be empty!")));
        AddDebtorResponse addDebtorResponse = addDebtorService.execute(invalidAddDebtorRequest);
        assertEquals(1, addDebtorResponse.getErrors().size());
        assertEquals("name", addDebtorResponse.getErrors().get(0).getField());
        assertEquals("cannot be empty!", addDebtorResponse.getErrors().get(0).getMessage());
    }

    @Test
    public void shouldNotInvokeDatabaseWhenValidationFails() {
        AddDebtorRequest invalidAddDebtorRequest = new AddDebtorRequest(null);
        when(addDebtorValidator.validate(invalidAddDebtorRequest)).thenReturn(List.of(new CoreError("name", "cannot be empty!")));
        addDebtorService.execute(invalidAddDebtorRequest);
        verifyNoInteractions(database);
    }

    @Test
    public void shouldSaveDebtorToDatabaseIfRequestIsValid() {
        AddDebtorRequest validAddDebtorRequest = new AddDebtorRequest("name");
        when(addDebtorValidator.validate(validAddDebtorRequest)).thenReturn(List.of());
        addDebtorService.execute(validAddDebtorRequest);
        verify(database).save(argThat(new DebtorMatcher("name")));
    }
}
