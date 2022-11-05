package myApp.core.services;
//need to finish
/*
import myApp.core.database.BankAccountRepository;
import myApp.core.database.DataBase;
import myApp.core.database.InMemoryDatabaseImpl;
import myApp.core.roles.Roles;
import myApp.core.requests.OpenAccountRequest;
import myApp.core.responses.OpenAccountResponse;
import myApp.core.services.validators.OpenAccountValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static junit.framework.TestCase.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OpenAccountServiceTest {

    @Mock
    private BankAccountRepository bankAccountRepository;
    @Mock
    private OpenAccountValidator validator;
    @InjectMocks
    private OpenAccountService service;

    @Test
    public void testSuccessOpenAccount() {
        OpenAccountRequest request = new OpenAccountRequest("000000-00001");
        when(validator.validate(request)).thenReturn(List.of());
        when(bankAccountRepository.openAccount("000-001")).thenReturn(true);
        OpenAccountResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        verify(bankAccountRepository).openAccount("000-001");
        assertTrue(response.isCompleted());
    }
/*
    @Test
    public void testShouldReturnPersonalCodeError() {
        OpenAccountRequest request = new OpenAccountRequest(null);
        when(validator.validate(request)).thenReturn(List.of(new CoreError("Field: Personal code",
                "Personal code must not be empty")));
        OpenAccountResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertFalse(response.isCompleted());
        verify(dataBase,times(0)).openAccount(null);
        assertEquals("Field: Personal code",response.getErrors().get(0).getField());
        assertEquals("Personal code must not be empty", response.getErrors().get(0).getMessage());

    }

}
 */

