package myApp.core.services;

import myApp.core.database.DataBase;
import myApp.core.database.InMemoryDatabaseImpl;
import myApp.core.domain.BankAccount;
import myApp.core.domain.Roles;
import myApp.core.requests.Ordering;
import myApp.core.requests.Paging;
import myApp.core.requests.SearchBankAccountRequest;
import myApp.core.responses.CoreError;
import myApp.core.responses.SearchBankAccountResponse;
import myApp.core.services.validators.SearchBankAccountValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SearchBankAccountServiceTest {

    private DataBase dataBase;
    private SearchBankAccountValidator validator;
    private SearchBankAccountService service;

    @BeforeEach
    void setUp() {
        dataBase = new InMemoryDatabaseImpl();
        dataBase.addBankAccount( new BankAccount("Example", "Example", "password",
                Roles.Regular_user, "000-111"));
        dataBase.addBankAccount(new BankAccount("Example", "Example2", "password",
                Roles.Regular_user, "000-112"));
        dataBase.addBankAccount( new BankAccount("Example", "Example", "password",
                Roles.Regular_user, "000-113"));
        dataBase.addBankAccount( new BankAccount("Example", "Example", "password",
                Roles.Regular_user, "000-114"));
        dataBase.addBankAccount( new BankAccount("Example", "Example", "password",
                Roles.Regular_user, "000-115"));
        dataBase.addBankAccount( new BankAccount("Example", "Example", "password",
                Roles.Regular_user, "000-116"));
        validator = new SearchBankAccountValidator();
        service = new SearchBankAccountService(dataBase, validator);
    }

    @Test
    void testFindByName() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Example", " ", " ");
        List<CoreError> errors = validator.validate(request);
        SearchBankAccountResponse response = service.execute(request);
        assertFalse(response.getBankAccounts().isEmpty());
        assertTrue(errors.isEmpty());
    }

    @Test
    void testFindBySurname() {
        SearchBankAccountRequest request = new SearchBankAccountRequest(" ", "Example", " ");
        List<CoreError> errors = validator.validate(request);
        SearchBankAccountResponse response = service.execute(request);
        assertFalse(response.getBankAccounts().isEmpty());
        assertTrue(errors.isEmpty());
    }
    @Test
    void testFindByPersonalCode() {
        SearchBankAccountRequest request = new SearchBankAccountRequest(" ", "", "000-111");
        List<CoreError> errors = validator.validate(request);
        SearchBankAccountResponse response = service.execute(request);
        assertFalse(response.getBankAccounts().isEmpty());
        assertTrue(errors.isEmpty());
    }
    @Test
    void testFindByNameAndSurname() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Example", "Example", " ");
        List<CoreError> errors = validator.validate(request);
        SearchBankAccountResponse response = service.execute(request);
        assertFalse(response.getBankAccounts().isEmpty());
        assertTrue(errors.isEmpty());
    }
    @Test
    void testFindByNameAndPersonalCode() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Example", " ", "000-111");
        List<CoreError> errors = validator.validate(request);
        SearchBankAccountResponse response = service.execute(request);
        assertFalse(response.getBankAccounts().isEmpty());
        assertTrue(errors.isEmpty());
    }
    @Test
    void testFindBySurnameAndPersonalCode() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("", "Example2", "000-112");
        List<CoreError> errors = validator.validate(request);
        SearchBankAccountResponse response = service.execute(request);
        assertFalse(response.getBankAccounts().isEmpty());
        assertTrue(errors.isEmpty());
    }
    @Test
    void testFindByNameAndSurnameAndPersonalCode() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Example", "Example", "000-111");
        List<CoreError> errors = validator.validate(request);
        SearchBankAccountResponse response = service.execute(request);
        assertFalse(response.getBankAccounts().isEmpty());
        assertTrue(errors.isEmpty());
    }

    @Test
    void testFindByNameAndOrderByNameWithoutErrors() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Example", " ", " ",
                new Ordering("name", "ASCENDING"));
        List<CoreError> errors = validator.validate(request);
        SearchBankAccountResponse response = service.execute(request);
        assertFalse(response.getBankAccounts().isEmpty());
        assertTrue(errors.isEmpty());
    }

    @Test
    void testFindByNameAndOrderBySurnameWithoutErrors() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Example", " ", " ",
                new Ordering("surname", "DESCENDING"));
        List<CoreError> errors = validator.validate(request);
        SearchBankAccountResponse response = service.execute(request);
        assertFalse(response.getBankAccounts().isEmpty());
        assertTrue(errors.isEmpty());
    }

    @Test
    void testFindByNameAndOrderByPersonalCodeWithoutErrors() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Example", " ", " ",
                new Ordering("personal code", "DESCENDING"));
        List<CoreError> errors = validator.validate(request);
        SearchBankAccountResponse response = service.execute(request);
        assertFalse(response.getBankAccounts().isEmpty());
        assertTrue(errors.isEmpty());
    }

    @Test
    void testShouldReturnTwoBankAccountPaging() {
        SearchBankAccountRequest request = new SearchBankAccountRequest("Example", " ", " ",
                new Ordering("personal code", "ASCENDING"), new Paging(2, 4));
        List<CoreError> errors = validator.validate(request);
        SearchBankAccountResponse response = service.execute(request);
        assertEquals(2, response.getBankAccounts().size());
        assertTrue(errors.isEmpty());
    }

}