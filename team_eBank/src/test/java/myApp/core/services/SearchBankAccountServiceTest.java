package myApp.core.services;

import myApp.core.database.jpa.JpaBankAccountRepository;
import myApp.core.domain.BankAccount;
import myApp.core.requests.Ordering;
import myApp.core.requests.Paging;
import myApp.core.requests.SearchBankAccountRequest;
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
    private JpaBankAccountRepository bankAccountRepository;
    @Mock
    private SearchBankAccountValidator validator;
    @InjectMocks
    private SearchBankAccountService service;

    @Test
    public void testShouldSuccessFindByName() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Example", " ", " ");
        when(validator.validate(request)).thenReturn(List.of());
        when(bankAccountRepository.findByName("Example")).thenReturn(List.of(new BankAccount("Example",
                "ExampleTwo", "000000-00001", null )));
        SearchBankAccountResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals("Example", response.getBankAccounts().get(0).getName());
        verify(bankAccountRepository).findByName("Example");
    }

    @Test
    public void testShouldSuccessFindBySurname() {
        SearchBankAccountRequest request = new SearchBankAccountRequest(" ", "ExampleTwo", " ");
        when(validator.validate(request)).thenReturn(List.of());
        when(bankAccountRepository.findBySurname("ExampleTwo")).thenReturn(List.of(new BankAccount("Example",
                "ExampleTwo", "000000-00001", null)));
        SearchBankAccountResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals("ExampleTwo", response.getBankAccounts().get(0).getSurname());
        verify(bankAccountRepository).findBySurname("ExampleTwo");
    }

    @Test
    public void testShouldSuccessFindByPersonalCode() {
        SearchBankAccountRequest request = new SearchBankAccountRequest(" ", " ", "000000-00001");
        when(validator.validate(request)).thenReturn(List.of());
        when(bankAccountRepository.findByPersonalCode("000000-00001")).thenReturn(List.of(new BankAccount("Example",
                "ExampleTwo", "000000-00001", null)));
        SearchBankAccountResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals("000000-00001",response.getBankAccounts().get(0).getPersonalCode());
        verify(bankAccountRepository).findByPersonalCode("000000-00001");
    }

    @Test
    public void testShouldSuccessFindByNameAndSurname() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Example", "ExampleTwo",
                " ");
        when(validator.validate(request)).thenReturn(List.of());
        when(bankAccountRepository.findByNameAndSurname("Example", "ExampleTwo"))
                .thenReturn(List.of(new BankAccount("Example",
                "ExampleTwo", "000000-00001", null)));
        SearchBankAccountResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals("Example",response.getBankAccounts().get(0).getName());
        assertEquals("ExampleTwo",response.getBankAccounts().get(0).getSurname());
        verify(bankAccountRepository).findByNameAndSurname("Example", "ExampleTwo");
    }

    @Test
    public void testShouldSuccessFindByNameAndPersonalCode() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Example", " ", "000000-00001");
        when(validator.validate(request)).thenReturn(List.of());
        when(bankAccountRepository.findByNameAndPersonalCode("Example", "000000-00001"))
                .thenReturn(List.of(new BankAccount("Example",
                        "ExampleTwo", "000000-00001", null)));
        SearchBankAccountResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals("Example", response.getBankAccounts().get(0).getName());
        assertEquals("000000-00001", response.getBankAccounts().get(0).getPersonalCode());
        verify(bankAccountRepository).findByNameAndPersonalCode("Example", "000000-00001");

    }

    @Test
    public void testFindBySurnameAndPersonalCode() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("", "ExampleTwo", "000000-00001");
        when(validator.validate(request)).thenReturn(List.of());
        when(bankAccountRepository.findBySurnameAndPersonalCode("ExampleTwo", "000000-00001"))
                .thenReturn(List.of(new BankAccount("Example",
                        "ExampleTwo", "000000-00001", null)));
        SearchBankAccountResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals("ExampleTwo", response.getBankAccounts().get(0).getSurname());
        assertEquals("000000-00001", response.getBankAccounts().get(0).getPersonalCode());
        verify(bankAccountRepository).findBySurnameAndPersonalCode("ExampleTwo", "000000-00001");
    }

    @Test
    public void testFindByNameAndSurnameAndPersonalCode() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Example", "ExampleTwo", "000000-00001");
        when(validator.validate(request)).thenReturn(List.of());
        when(bankAccountRepository.findByNameAndSurnameAndPersonalCode("Example","ExampleTwo", "000000-00001"))
                .thenReturn(List.of(new BankAccount("Example",
                        "ExampleTwo", "000000-00001", null)));
        SearchBankAccountResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals("Example", response.getBankAccounts().get(0).getName());
        assertEquals("ExampleTwo", response.getBankAccounts().get(0).getSurname());
        assertEquals("000000-00001", response.getBankAccounts().get(0).getPersonalCode());
        verify(bankAccountRepository).findByNameAndSurnameAndPersonalCode("Example", "ExampleTwo", "000000-00001");

    }

    @Test
    public void testFindByNameAndOrderByNameWithoutErrors() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Example", " ", " ",
                new Ordering("name", "ASCENDING"));
        when(validator.validate(request)).thenReturn(List.of());
        when(bankAccountRepository.findByName("Example"))
                .thenReturn(List.of(new BankAccount("Example",
                        "ExampleTwo", "000000-00001", null),
                        new BankAccount("Example",
                                "ExampleTwo", "000000-00001", null)));
        SearchBankAccountResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals("Example",response.getBankAccounts().get(0).getName());
        verify(bankAccountRepository).findByName("Example");
    }

    @Test
    public void testFindByNameAndOrderBySurnameWithoutErrors() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Example", "", " ",
                new Ordering("surname", "DESCENDING"));
        when(validator.validate(request)).thenReturn(List.of());
        when(bankAccountRepository.findByName("Example")).thenReturn(List.of(new BankAccount("Example",
                        "A", "000000-00001", null),
                new BankAccount("Example",
                        "B", "000000-00001", null)));
        SearchBankAccountResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals("B", response.getBankAccounts().get(0).getSurname());
        verify(bankAccountRepository).findByName("Example");
    }

    @Test
    public void testFindByNameAndOrderByPersonalCodeWithoutErrors() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Example", " ", " ",
                new Ordering("personal code", "DESCENDING"));
        when(validator.validate(request)).thenReturn(List.of());
        when(bankAccountRepository.findByName("Example")).thenReturn(List.of(new BankAccount("Example",
                        "ExampleTwo", "000000-00001", null),
                new BankAccount("Example",
                        "ExampleTwo", "000000-00002", null)));
        SearchBankAccountResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals("000000-00002",response.getBankAccounts().get(0).getPersonalCode());
        verify(bankAccountRepository).findByName("Example");
    }

    @Test
    public void testShouldReturnTwoBankAccountPaging() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Example", " ", " ",
                new Ordering("personal code", "ASCENDING"), new Paging(1, 1));
        when(validator.validate(request)).thenReturn(List.of());
        when(bankAccountRepository.findByName("Example")).thenReturn(List.of(new BankAccount("Example",
                        "ExampleTwo", "000000-00001", null),
                new BankAccount("Example",
                        "ExampleTwo", "000000-00002", null)));
        SearchBankAccountResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        assertEquals("000000-00001",response.getBankAccounts().get(0).getPersonalCode());
        verify(bankAccountRepository).findByName("Example");
    }
}