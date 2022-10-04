package myApp.core.services;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import myApp.core.database.DataBase;
import myApp.core.domain.Roles;
import myApp.core.matcher.BankAccountMatcher;
import myApp.core.requests.AddBankAccountRequest;
import myApp.core.responses.AddBankAccountResponse;
import myApp.core.responses.CoreError;
import myApp.core.services.validators.AddBankAccountValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AddBankAccountServiceTest {

    @Mock
    private DataBase dataBase;
    @Mock
    private AddBankAccountValidator validator;
    @InjectMocks
    private AddBankAccountService service;

    @Test
    public void testExecuteWithoutErrors() {
        AddBankAccountRequest request = new AddBankAccountRequest("Example", "Example"
                , "password",
                "000-001");
        when(validator.validate(request)).thenReturn(List.of());
        AddBankAccountResponse response = service.execute(request);
        assertFalse(response.hasErrors());
    }

    @Test
    public void testShouldReturnErrorAboutName() {
        AddBankAccountRequest request = new AddBankAccountRequest(null, "Example",
                "000-001", "password");
        when(validator.validate(request)).thenReturn(List.of(new CoreError("Field: Name",
                "Name can only contain letters and must not be empty")));
        AddBankAccountResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals("Field: Name", response.getErrors().get(0).getField());
        assertNull(response.getBankAccount());
    }

    @Test
    public void testShouldReturnErrorAboutSurname() {
        AddBankAccountRequest request = new AddBankAccountRequest("Example", null,
                "000-001", "password");
        when(validator.validate(request)).thenReturn(List.of(new CoreError("Field: Surname",
                "Surname can only contain letters and must not be empty")));
        AddBankAccountResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals("Field: Surname", response.getErrors().get(0).getField());
        assertNull(response.getBankAccount());
    }
    @Test
    public void shouldAddBankAccountToDatabase() {
         AddBankAccountRequest request = new AddBankAccountRequest ("Example", "Example",
                 "000-001", "password");
        when(validator.validate(request)).thenReturn(List.of());
        service.execute(request);
        verify(dataBase).addBankAccount(argThat(new BankAccountMatcher("Example", "Example",
                "password", Roles.Regular_user, "000-001")));
    }
}