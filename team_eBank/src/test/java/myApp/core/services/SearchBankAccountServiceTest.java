package myApp.core.services;

import myApp.core.database.DataBase;
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
    public void testFindByNameWithoutErrors() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Example", " ", " ");
        when(validator.validate(request)).thenReturn(List.of());
        SearchBankAccountResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        verify(dataBase).findByName("Example");
    }

    @Test
    public void testFindBySurnameWithoutErrors() {
        SearchBankAccountRequest request = new SearchBankAccountRequest(" ", "Example", " ");
        when(validator.validate(request)).thenReturn(List.of());
        SearchBankAccountResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        verify(dataBase).findBySurname("Example");
    }

    @Test
    public void testFindByPersonalCodeWithoutErrors() {
        SearchBankAccountRequest request = new SearchBankAccountRequest(" ", "", "000-111");
        when(validator.validate(request)).thenReturn(List.of());
        SearchBankAccountResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        verify(dataBase).findByPersonalCode("000-111");
    }

    @Test
    public void testFindByNameAndSurnameWithout() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Example", "ExampleTwo", " ");
        when(validator.validate(request)).thenReturn(List.of());
        SearchBankAccountResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        verify(dataBase).findByNameAndSurname("Example", "ExampleTwo");
    }

    @Test
    public void testFindByNameAndPersonalCodeWithout() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Example", " ", "000-111");
        when(validator.validate(request)).thenReturn(List.of());
        SearchBankAccountResponse response = service.execute(request);
        verify(dataBase).findByNameAndPersonalCode("Example", "000-111");

    }

    @Test
    public void testFindBySurnameAndPersonalCode() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("", "ExampleTwo", "000-112");
        when(validator.validate(request)).thenReturn(List.of());
        SearchBankAccountResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        verify(dataBase).findBySurnameAndPersonalCode("ExampleTwo", "000-112");
    }

    @Test
    public void testFindByNameAndSurnameAndPersonalCode() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Example", "ExampleTwo", "000-111");
        when(validator.validate(request)).thenReturn(List.of());
        SearchBankAccountResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        verify(dataBase).findByNameAndSurnameAndPersonalCode("Example", "ExampleTwo", "000-111");

    }

    @Test
    public void testFindByNameAndOrderByNameWithoutErrors() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Example", " ", " ",
                new Ordering("name", "ASCENDING"));
        when(validator.validate(request)).thenReturn(List.of());
        SearchBankAccountResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        verify(dataBase).findByName("Example");
    }

    @Test
    public void testFindByNameAndOrderBySurnameWithoutErrors() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Example", " ", " ",
                new Ordering("surname", "DESCENDING"));
        when(validator.validate(request)).thenReturn(List.of());
        SearchBankAccountResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        verify(dataBase).findByName("Example");
    }

    @Test
    public void testFindByNameAndOrderByPersonalCodeWithoutErrors() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Example", " ", " ",
                new Ordering("personal code", "DESCENDING"));
        when(validator.validate(request)).thenReturn(List.of());
        SearchBankAccountResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        verify(dataBase).findByName("Example");
    }

    @Test
    public void testShouldReturnTwoBankAccountPaging() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Example", " ", " ",
                new Ordering("personal code", "ASCENDING"), new Paging(2, 4));
        when(validator.validate(request)).thenReturn(List.of());
        SearchBankAccountResponse response = service.execute(request);
        assertFalse(response.hasErrors());
        verify(dataBase).findByName("Example");
    }
}