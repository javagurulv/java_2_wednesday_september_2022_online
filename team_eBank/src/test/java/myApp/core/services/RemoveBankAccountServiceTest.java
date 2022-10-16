package myApp.core.services;
import myApp.core.database.DataBase;
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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RemoveBankAccountServiceTest {

    @Mock
    DataBase dataBase;
    @Mock
    RemoveBankAccountValidator validator;
    @InjectMocks
    RemoveBankAccountService service;

    @Test
    public void testSuccessRemoveBankAccount() {
        RemoveBankAccountRequest request = new RemoveBankAccountRequest("000-001");
        when(validator.validate(request)).thenReturn(List.of());
        when(dataBase.deleteBankAccount("000-001")).thenReturn(true);
        RemoveBankAccountResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertTrue(response.isDeleted());
        verify(dataBase).deleteBankAccount("000-001");
    }

    @Test
    public void testShouldReturnPersonalCodeError() {
        RemoveBankAccountRequest request = new RemoveBankAccountRequest(null);
        when(validator.validate(request)).thenReturn(List.of(new CoreError("Field: Id",
                "Id must not be empty")));
        RemoveBankAccountResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals("Field: Id", response.getErrors().get(0).getField());
        assertEquals("Id must not be empty", response.getErrors().get(0).getMessage());
    }
}
