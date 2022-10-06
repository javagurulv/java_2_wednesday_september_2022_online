package myApp.core.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import java.util.List;
import myApp.core.database.DataBase;
import myApp.core.domain.BankAccount;
import myApp.core.domain.Roles;
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
    public void testShouldAddBankAccountToDataBase() {
        AddBankAccountRequest request = new AddBankAccountRequest("Example", "Example"
                , "000-001", "password");
        when(validator.validate(request)).thenReturn(List.of());
        service.execute(request);
        verify(dataBase).addBankAccount(new BankAccount("Example", "Example", "password",
                Roles.Regular_user, "000-001"));
    }

    @Test
    public void testShouldReturnResponseWithBankAccount() {
        AddBankAccountRequest request = new AddBankAccountRequest("Example", "ExampleTwo"
                , "000-011", "password");
        when(validator.validate(request)).thenReturn(List.of());
        AddBankAccountResponse response = service.execute(request);
        assertEquals(response.getBankAccount().getName(), "Example");
        assertEquals(response.getBankAccount().getSurname(), "ExampleTwo");
        assertEquals(response.getBankAccount().getPersonalCode(), "000-011");
        assertEquals(response.getBankAccount().getPassword(), "password");
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
    }

    @Test
    public void testShouldReturnErrorAboutNameAndSurname() {
        AddBankAccountRequest request = new AddBankAccountRequest(null, null,
                "000-001", "password");
        when(validator.validate(request)).thenReturn(List.of(new CoreError("Field: Name",
                "Name can only contain letters and must not be empty"),
                new CoreError("Field: Surname",
                        "Surname can only contain letters and must not be empty")
        ));
        AddBankAccountResponse response = service.execute(request);
        assertTrue(response.hasErrors());
        assertEquals("Field: Name", response.getErrors().get(0).getField());
        assertEquals("Name can only contain letters and must not be empty",
                response.getErrors().get(0).getMessage());
        assertEquals("Field: Surname", response.getErrors().get(1).getField());
        assertEquals("Surname can only contain letters and must not be empty",
                response.getErrors().get(1).getMessage());
    }
}