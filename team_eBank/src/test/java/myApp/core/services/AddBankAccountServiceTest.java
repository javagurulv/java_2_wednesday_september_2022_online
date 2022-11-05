package myApp.core.services;

import myApp.core.database.BankRepository;
import myApp.core.domain.BankAccount;
import myApp.core.domain.User;
import myApp.core.requests.AddBankAccountRequest;
import myApp.core.requests.AddUserRequest;
import myApp.core.responses.AddBankAccountResponse;
import myApp.core.responses.CoreError;
import myApp.core.services.validators.AddBankAccountValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AddBankAccountServiceTest {

    @Mock
    private BankRepository bankRepository;
    @Mock
    private AddBankAccountValidator validator;
    @InjectMocks
    private AddBankAccountService service;


    @Test
    public void testShouldAddBankAccountToDataBase() {
        AddBankAccountRequest request = new AddBankAccountRequest("Example", "Example"
                , "000000-00003");
        when(validator.validate(request)).thenReturn(List.of());
        service.execute(request,new AddUserRequest("000000-00003", "password"));
        verify(bankRepository).addBankAccount(new BankAccount("Example", "Example", "Roles.Regular_user",
                "000000-00003", null),
                new User("000000-00003", "password"));
    }

    @Test
    public void testShouldReturnResponseWithBankAccount() {
        AddBankAccountRequest request = new AddBankAccountRequest("Example", "ExampleTwo"
                , "000000-00003");
        when(validator.validate(request)).thenReturn(List.of());
        AddBankAccountResponse response = service.execute(request,new AddUserRequest("000000-00003",
                "password"));
        assertEquals(response.getBankAccount().getName(), "Example");
        assertEquals(response.getBankAccount().getSurname(), "ExampleTwo");
        assertEquals(response.getBankAccount().getPersonalCode(), "000000-00003");
    }

    @Test
    public void testShouldReturnErrorAboutName() {
        AddBankAccountRequest request = new AddBankAccountRequest("Example", "ExampleTwo"
                , "000000-00003");
        when(validator.validate(request)).thenReturn(List.of(new CoreError("Field: Name",
                "Name can only contain letters and must not be empty")));
        AddBankAccountResponse response = service.execute(request,
                new AddUserRequest("000000-00003", "password"));
        assertTrue(response.hasErrors());
        assertEquals("Field: Name", response.getErrors().get(0).getField());
    }

    @Test
    public void testShouldReturnErrorAboutSurname() {
        AddBankAccountRequest request = new AddBankAccountRequest("Example", null
                , "000000-00003");
        when(validator.validate(request)).thenReturn(List.of(new CoreError("Field: Surname",
                "Surname can only contain letters and must not be empty")));
        AddBankAccountResponse response = service.execute(request,
                new AddUserRequest("000000-00003", "password"));
        assertTrue(response.hasErrors());
        assertEquals("Field: Surname", response.getErrors().get(0).getField());
    }

    @Test
    public void testShouldReturnErrorAboutNameAndSurname() {
        AddBankAccountRequest request = new AddBankAccountRequest(null, null,"000000-00003");
        when(validator.validate(request)).thenReturn(List.of(new CoreError("Field: Name",
                "Name can only contain letters and must not be empty"),
                new CoreError("Field: Surname",
                        "Surname can only contain letters and must not be empty")
        ));
        AddBankAccountResponse response = service.execute(request,
                new AddUserRequest("000000-00003", "password"));
        assertTrue(response.hasErrors());
        assertEquals("Field: Name", response.getErrors().get(0).getField());
        assertEquals("Name can only contain letters and must not be empty",
                response.getErrors().get(0).getMessage());
        assertEquals("Field: Surname", response.getErrors().get(1).getField());
        assertEquals("Surname can only contain letters and must not be empty",
                response.getErrors().get(1).getMessage());
    }
}