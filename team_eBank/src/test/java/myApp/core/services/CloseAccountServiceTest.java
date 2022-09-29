package myApp.core.services;

import myApp.core.database.DataBase;
import myApp.core.database.InMemoryDatabaseImpl;
import myApp.core.domain.Account;
import myApp.core.domain.BankAccount;
import myApp.core.domain.Roles;
import myApp.core.requests.CloseAccountRequest;
import myApp.core.responses.CloseAccountResponse;
import myApp.core.responses.CoreError;
import myApp.core.services.validators.CloseAccountValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CloseAccountServiceTest {

    private DataBase dataBase;
    private CloseAccountService service;
    private CloseAccountValidator validator;

    @BeforeEach
    void setUp() {
        dataBase = new InMemoryDatabaseImpl();
        validator = new CloseAccountValidator();
        service = new CloseAccountService(dataBase, validator);
        BankAccount bankAccount = new BankAccount("Example1", "Example2","password",
                Roles.Regular_user, "000-001");
        dataBase.addBankAccount(bankAccount);
        bankAccount.setAccount(new Account(2L, 0));

    }

    @Test
    void testCloseAccountWithoutErrors() {
        CloseAccountRequest request = new CloseAccountRequest("000-001");
        List<CoreError> errors = validator.validate(request);
        CloseAccountResponse response = service.execute(request);
        assertTrue(response.isDeleted());
        assertTrue(errors.isEmpty());
    }

    @Test
    void testCloseAccountWithErrors() {
        CloseAccountRequest request = new CloseAccountRequest(null);
        List<CoreError> errors = validator.validate(request);
        CloseAccountResponse response = service.execute(request);
        assertFalse(response.isDeleted());
        assertFalse(errors.isEmpty());
    }
}