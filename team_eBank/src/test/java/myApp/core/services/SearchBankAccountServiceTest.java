package myApp.core.services;

import myApp.core.database.DataBase;
import myApp.core.database.InMemoryDatabaseImpl;
import myApp.core.domain.BankAccount;
import myApp.core.domain.Roles;
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
        validator = new SearchBankAccountValidator();
        service = new SearchBankAccountService(dataBase, validator);
    }

    @Test
    void testFindByName() {
        BankAccount bankAccount = new BankAccount("Example", "Example", "password",
                Roles.Regular_user, "000-111");
        dataBase.addBankAccount(bankAccount);
        SearchBankAccountRequest request = new SearchBankAccountRequest("Example", " ", " ");
        List<CoreError> errors = validator.validate(request);
        SearchBankAccountResponse response = service.execute(request);
        assertFalse(response.getBankAccounts().isEmpty());
        assertTrue(errors.isEmpty());
    }

    @Test
    void testFindBySurname() {
        BankAccount bankAccount = new BankAccount("Example", "Example", "password",
                Roles.Regular_user, "000-111");
        dataBase.addBankAccount(bankAccount);
        SearchBankAccountRequest request = new SearchBankAccountRequest(" ", "Example", " ");
        List<CoreError> errors = validator.validate(request);
        SearchBankAccountResponse response = service.execute(request);
        assertFalse(response.getBankAccounts().isEmpty());
        assertTrue(errors.isEmpty());
    }
    @Test
    void testFindByPersonalCode() {
        BankAccount bankAccount = new BankAccount("Example", "Example", "password",
                Roles.Regular_user, "000-111");
        dataBase.addBankAccount(bankAccount);
        SearchBankAccountRequest request = new SearchBankAccountRequest(" ", "", "000-111");
        List<CoreError> errors = validator.validate(request);
        SearchBankAccountResponse response = service.execute(request);
        assertFalse(response.getBankAccounts().isEmpty());
        assertTrue(errors.isEmpty());
    }
    @Test
    void testFindByNameAndSurname() {
        BankAccount bankAccount = new BankAccount("Example", "Example", "password",
                Roles.Regular_user, "000-111");
        dataBase.addBankAccount(bankAccount);
        SearchBankAccountRequest request = new SearchBankAccountRequest("Example", "Example", " ");
        List<CoreError> errors = validator.validate(request);
        SearchBankAccountResponse response = service.execute(request);
        assertFalse(response.getBankAccounts().isEmpty());
        assertTrue(errors.isEmpty());
    }
    @Test
    void testFindByNameAndPersonalCode() {
        BankAccount bankAccount = new BankAccount("Example", "Example", "password",
                Roles.Regular_user, "000-111");
        dataBase.addBankAccount(bankAccount);
        SearchBankAccountRequest request = new SearchBankAccountRequest("Example", " ", "000-111");
        List<CoreError> errors = validator.validate(request);
        SearchBankAccountResponse response = service.execute(request);
        assertFalse(response.getBankAccounts().isEmpty());
        assertTrue(errors.isEmpty());
    }
    @Test
    void testFindBySurnameAndPersonalCode() {
        BankAccount bankAccount = new BankAccount("Example", "Example2", "password",
                Roles.Regular_user, "000-111");
        dataBase.addBankAccount(bankAccount);
        SearchBankAccountRequest request = new SearchBankAccountRequest("", "Example2", "000-111");
        List<CoreError> errors = validator.validate(request);
        SearchBankAccountResponse response = service.execute(request);
        assertFalse(response.getBankAccounts().isEmpty());
        assertTrue(errors.isEmpty());
    }
    @Test
    void testFindByNameAndSurnameAndPersonalCode() {
        BankAccount bankAccount = new BankAccount("Example", "Example", "password",
                Roles.Regular_user, "000-111");
        dataBase.addBankAccount(bankAccount);
        SearchBankAccountRequest request = new SearchBankAccountRequest("Example", "Example", "000-111");
        List<CoreError> errors = validator.validate(request);
        SearchBankAccountResponse response = service.execute(request);
        assertFalse(response.getBankAccounts().isEmpty());
        assertTrue(errors.isEmpty());
    }

}