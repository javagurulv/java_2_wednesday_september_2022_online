package myApp.core.services;

import myApp.core.database.BankAccountRepository;
import myApp.core.requests.CloseAccountRequest;
import myApp.core.responses.CloseAccountResponse;
import myApp.core.responses.CoreError;
import myApp.core.services.validators.CloseAccountValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static junit.framework.TestCase.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class CloseAccountServiceTest {

    @Mock
    private BankAccountRepository bankAccountRepository;
    @Mock
    private CloseAccountValidator validator;
    @InjectMocks
    private CloseAccountService service;


    @Test
    public void testCloseAccountWithoutErrors() {
        CloseAccountRequest request = new CloseAccountRequest("000000-00001");
        when(validator.validate(request)).thenReturn(List.of());
        CloseAccountResponse response = service.execute(request);
        verify(bankAccountRepository,times(0)).closeAccount("000000-00001");
    }

    @Test
    public void testCloseAccountWithErrors() {
        CloseAccountRequest request = new CloseAccountRequest(null);
        when(validator.validate(request)).thenReturn(List.of(new CoreError("Field: Personal code",
                "Personal code must not be empty")));
        CloseAccountResponse response = service.execute(request);
        assertFalse(response.isDeleted());
        assertEquals("Field: Personal code", response.getErrors().get(0).getField());
        assertEquals("Personal code must not be empty", response.getErrors().get(0).getMessage());
        verify(bankAccountRepository, times(0)).closeAccount(null);
    }
}