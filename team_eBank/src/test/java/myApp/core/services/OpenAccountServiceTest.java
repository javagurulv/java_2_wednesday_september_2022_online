package myApp.core.services;

import myApp.core.database.DataBase;
import myApp.core.database.InMemoryDatabaseImpl;
import myApp.core.domain.BankAccount;
import myApp.core.domain.Roles;
import myApp.core.requests.OpenAccountRequest;
import myApp.core.responses.CoreError;
import myApp.core.responses.OpenAccountResponse;
import myApp.core.services.validators.MoneyTransferValidator;
import myApp.core.services.validators.OpenAccountValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OpenAccountServiceTest {

    private DataBase dataBase;
    private OpenAccountValidator validator;
    private OpenAccountService service;

    @BeforeEach
    void setUp() {
        dataBase = new InMemoryDatabaseImpl();
        validator = new OpenAccountValidator();
        service = new OpenAccountService(dataBase, validator);
        BankAccount bankAccountOne = new BankAccount("Example1", "Example2",
                Roles.Regular_user, "000-001");
        BankAccount bankAccountTwo = new BankAccount("Example2", "Example3",
                Roles.Regular_user, "000-002");
        dataBase.addBankAccount(bankAccountOne);
        dataBase.addBankAccount(bankAccountTwo);
    }

    @Test
    void testExecuteWithoutErrors() {
        OpenAccountRequest request = new OpenAccountRequest("000-001");
        List<CoreError> errors = validator.validate(request);
        OpenAccountResponse response = service.execute(request);
        assertTrue(errors.isEmpty());
        assertTrue(response.isCompleted());
    }

    @Test
    void testExecuteWithErrors() {
        OpenAccountRequest request = new OpenAccountRequest(null);
        List<CoreError> errors = validator.validate(request);
        OpenAccountResponse response = service.execute(request);
        assertFalse(errors.isEmpty());
        assertFalse(response.isCompleted());
    }
}