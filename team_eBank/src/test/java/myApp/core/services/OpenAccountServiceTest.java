package myApp.core.services;
import myApp.core.database.DataBase;
import myApp.core.database.InMemoryDatabaseImpl;
import myApp.core.domain.BankAccount;
import myApp.core.domain.Roles;
import myApp.core.requests.OpenAccountRequest;
import myApp.core.responses.CoreError;
import myApp.core.responses.OpenAccountResponse;
import myApp.core.services.validators.OpenAccountValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OpenAccountServiceTest {

    @Mock
    private DataBase dataBase;
    @Mock
    private OpenAccountValidator validator;
    @InjectMocks
    private OpenAccountService service;

    @Test
    public void testExecuteWithoutErrors() {
        OpenAccountRequest request = new OpenAccountRequest("000-001");
        when(validator.validate(request)).thenReturn(List.of());
        OpenAccountResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        verify(dataBase).openAccount("000-001");
    }

    @Test
    public void testExecuteWithErrors() {
        OpenAccountRequest request = new OpenAccountRequest(null);
        when(validator.validate(request)).thenReturn(List.of(new CoreError("Field: Personal code",
                "personal code must not be empty")));
        OpenAccountResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertFalse(response.isCompleted());
    }

}