package myApp.core.services;
/*
import myApp.core.database.DataBase;
import myApp.core.domain.BankAccount;
import myApp.core.domain.Roles;
import myApp.core.requests.Ordering;
import myApp.core.requests.Paging;
import myApp.core.requests.SearchBankAccountRequest;
import myApp.core.responses.CoreError;
import myApp.core.responses.SearchBankAccountResponse;
import myApp.core.services.validators.SearchBankAccountValidator;
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
public class SearchBankAccountServiceTest {

    @Mock
    private DataBase dataBase;
    @Mock
    private SearchBankAccountValidator validator;
    @InjectMocks
    private SearchBankAccountService service;

    @Test
    public void testShouldSuccessFindByName() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Example", " ", " ");
        when(validator.validate(request)).thenReturn(List.of());
        when(dataBase.findByName("Example")).thenReturn(List.of(new BankAccount("Example",
                "ExampleTwo", "password", Roles.Regular_user, "000-111")));
        SearchBankAccountResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals("Example", response.getBankAccounts().get(0).getName());
        verify(dataBase).findByName("Example");
    }

    @Test
    public void testShouldSuccessFindBySurname() {
        SearchBankAccountRequest request = new SearchBankAccountRequest(" ", "ExampleTwo", " ");
        when(validator.validate(request)).thenReturn(List.of());
        when(dataBase.findBySurname("ExampleTwo")).thenReturn(List.of(new BankAccount("Example",
                "ExampleTwo", "password", Roles.Regular_user, "000-111")));
        SearchBankAccountResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals("ExampleTwo", response.getBankAccounts().get(0).getSurname());
        verify(dataBase).findBySurname("ExampleTwo");
    }

    @Test
    public void testShouldSuccessFindByPersonalCode() {
        SearchBankAccountRequest request = new SearchBankAccountRequest(" ", " ", "000-111");
        when(validator.validate(request)).thenReturn(List.of());
        when(dataBase.findByPersonalCode("000-111")).thenReturn(List.of(new BankAccount("Example",
                "ExampleTwo", "password", Roles.Regular_user, "000-111")));
        SearchBankAccountResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals("000-111",response.getBankAccounts().get(0).getPersonalCode());
        verify(dataBase).findByPersonalCode("000-111");
    }

    @Test
    public void testShouldSuccessFindByNameAndSurname() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Example", "ExampleTwo",
                " ");
        when(validator.validate(request)).thenReturn(List.of());
        when(dataBase.findByNameAndSurname("Example", "ExampleTwo"))
                .thenReturn(List.of(new BankAccount("Example",
                "ExampleTwo", "password", Roles.Regular_user, "000-111")));
        SearchBankAccountResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals("Example",response.getBankAccounts().get(0).getName());
        assertEquals("ExampleTwo",response.getBankAccounts().get(0).getSurname());
        verify(dataBase).findByNameAndSurname("Example", "ExampleTwo");
    }

    @Test
    public void testShouldSuccessFindByNameAndPersonalCode() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Example", " ", "000-111");
        when(validator.validate(request)).thenReturn(List.of());
        when(dataBase.findByNameAndPersonalCode("Example", "000-111"))
                .thenReturn(List.of(new BankAccount("Example",
                        "ExampleTwo", "password", Roles.Regular_user, "000-111")));
        SearchBankAccountResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals("Example", response.getBankAccounts().get(0).getName());
        assertEquals("000-111", response.getBankAccounts().get(0).getPersonalCode());
        verify(dataBase).findByNameAndPersonalCode("Example", "000-111");

    }

    @Test
    public void testFindBySurnameAndPersonalCode() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("", "ExampleTwo", "000-111");
        when(validator.validate(request)).thenReturn(List.of());
        when(dataBase.findBySurnameAndPersonalCode("ExampleTwo", "000-111"))
                .thenReturn(List.of(new BankAccount("Example",
                        "ExampleTwo", "password", Roles.Regular_user, "000-111")));
        SearchBankAccountResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals("ExampleTwo", response.getBankAccounts().get(0).getSurname());
        assertEquals("000-111", response.getBankAccounts().get(0).getPersonalCode());
        verify(dataBase).findBySurnameAndPersonalCode("ExampleTwo", "000-111");
    }

    @Test
    public void testFindByNameAndSurnameAndPersonalCode() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Example", "ExampleTwo", "000-111");
        when(validator.validate(request)).thenReturn(List.of());
        when(dataBase.findByNameAndSurnameAndPersonalCode("Example","ExampleTwo", "000-111"))
                .thenReturn(List.of(new BankAccount("Example",
                        "ExampleTwo", "password", Roles.Regular_user, "000-111")));
        SearchBankAccountResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals("Example", response.getBankAccounts().get(0).getName());
        assertEquals("ExampleTwo", response.getBankAccounts().get(0).getSurname());
        assertEquals("000-111", response.getBankAccounts().get(0).getPersonalCode());
        verify(dataBase).findByNameAndSurnameAndPersonalCode("Example", "ExampleTwo", "000-111");

    }

    @Test
    public void testFindByNameAndOrderByNameWithoutErrors() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Example", " ", " ",
                new Ordering("name", "ASCENDING"));
        when(validator.validate(request)).thenReturn(List.of());
        when(dataBase.findByName("Example"))
                .thenReturn(List.of(new BankAccount("Example",
                        "ExampleTwo", "password", Roles.Regular_user, "000-111"),
                        new BankAccount("Example",
                                "ExampleTwo", "password", Roles.Regular_user, "000-112")));
        SearchBankAccountResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals("Example",response.getBankAccounts().get(0).getName());
        verify(dataBase).findByName("Example");
    }

    @Test
    public void testFindByNameAndOrderBySurnameWithoutErrors() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Example", "", " ",
                new Ordering("surname", "DESCENDING"));
        when(validator.validate(request)).thenReturn(List.of());
        when(dataBase.findByName("Example")).thenReturn(List.of(new BankAccount("Example",
                        "A", "password", Roles.Regular_user, "000-111"),
                new BankAccount("Example",
                        "B", "password", Roles.Regular_user, "000-112")));
        SearchBankAccountResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals("B", response.getBankAccounts().get(0).getSurname());
        verify(dataBase).findByName("Example");
    }

    @Test
    public void testFindByNameAndOrderByPersonalCodeWithoutErrors() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Example", " ", " ",
                new Ordering("personal code", "DESCENDING"));
        when(validator.validate(request)).thenReturn(List.of());
        when(dataBase.findByName("Example")).thenReturn(List.of(new BankAccount("Example",
                        "ExampleTwo", "password", Roles.Regular_user, "000-111"),
                new BankAccount("Example",
                        "ExampleTwo", "password", Roles.Regular_user, "000-112")));
        SearchBankAccountResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals("000-112",response.getBankAccounts().get(0).getPersonalCode());
        verify(dataBase).findByName("Example");
    }

    @Test
    public void testShouldReturnTwoBankAccountPaging() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Example", " ", " ",
                new Ordering("personal code", "ASCENDING"), new Paging(1, 1));
        when(validator.validate(request)).thenReturn(List.of());
        when(dataBase.findByName("Example")).thenReturn(List.of(new BankAccount("Example",
                        "ExampleTwo", "password", Roles.Regular_user, "000-111"),
                new BankAccount("Example",
                        "ExampleTwo", "password", Roles.Regular_user, "000-112")));
        SearchBankAccountResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals("000-111",response.getBankAccounts().get(0).getPersonalCode());
        verify(dataBase).findByName("Example");
    }
}

 */