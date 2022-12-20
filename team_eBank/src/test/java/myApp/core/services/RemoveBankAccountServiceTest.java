package myApp.core.services;

import myApp.core.database.jpa.JpaBankAccountRepository;
import myApp.core.requests.RemoveBankAccountRequest;
import myApp.core.responses.CoreError;
import myApp.core.responses.RemoveBankAccountResponse;
import myApp.core.services.validators.RemoveBankAccountValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static junit.framework.TestCase.*;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class RemoveBankAccountServiceTest {

    @Mock
    JpaBankAccountRepository bankRepository;
    @Mock
    RemoveBankAccountValidator validator;
    @InjectMocks
    RemoveBankAccountService service;

    @Test
    public void testSuccessRemoveBankAccount() {
        RemoveBankAccountRequest request = new RemoveBankAccountRequest(1l);
        when(validator.validate(request)).thenReturn(List.of());
        RemoveBankAccountResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertTrue(response.isDeleted());
        verify(bankRepository).deleteById(1L);
    }

    @Test
    public void testShouldReturnPersonalCodeError() {
        RemoveBankAccountRequest request = new RemoveBankAccountRequest(null);
        when(validator.validate(request)).thenReturn(List.of(new CoreError("Field: Id",
                "Id must not be empty")));
        RemoveBankAccountResponse response = service.execute(request);
        verify(bankRepository,times(0)).deleteById(null);
    }
}