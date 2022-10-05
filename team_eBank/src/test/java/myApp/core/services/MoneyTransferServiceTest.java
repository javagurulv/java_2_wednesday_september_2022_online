package myApp.core.services;
import myApp.core.database.DataBase;
import myApp.core.requests.MoneyTransferRequest;
import myApp.core.responses.CoreError;
import myApp.core.responses.MoneyTransferResponse;
import myApp.core.services.validators.MoneyTransferValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static junit.framework.TestCase.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MoneyTransferServiceTest {

    @Mock
    private DataBase dataBase;
    @Mock
    private MoneyTransferValidator validator;
    @InjectMocks
    private MoneyTransferService service;

    @Test
    public void testExecuteWithoutErrors() {
        MoneyTransferRequest request = new MoneyTransferRequest("000-001",
                "000-002", 100);
        when(validator.validate(request)).thenReturn(List.of());
        MoneyTransferResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        verify(dataBase).bankTransfer("000-001",
                "000-002", 100);
    }

    @Test
    public void testExecuteWitErrors() {
        MoneyTransferRequest request = new MoneyTransferRequest("",
                "",  0);
        when(validator.validate(request)).thenReturn(List.of(new CoreError("Field: Personal code",
                "Your personal code must not be empty"), new CoreError("Field: Another personal code",
                "another personal code must not be empty")));
        MoneyTransferResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals(2, response.getErrors().size());
    }
}
