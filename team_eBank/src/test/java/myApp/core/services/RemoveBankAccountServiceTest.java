package myApp.core.services;

import myApp.core.database.DataBase;
import myApp.core.database.InMemoryDatabaseImpl;
import myApp.core.domain.BankAccount;
import myApp.core.domain.Roles;
import myApp.core.requests.RemoveBankAccountRequest;
import myApp.core.responses.CoreError;
import myApp.core.responses.RemoveBankAccountResponse;
import myApp.core.services.validators.AddBankAccountValidator;
import myApp.core.services.validators.RemoveBankAccountValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RemoveBankAccountServiceTest {

    DataBase dataBase;
    RemoveBankAccountValidator validator;
    RemoveBankAccountService service;

    @BeforeEach
    void setUp() {
        dataBase = new InMemoryDatabaseImpl();
        validator = new RemoveBankAccountValidator();
        service = new RemoveBankAccountService(dataBase,validator);
        dataBase.addBankAccount(new BankAccount("Example", "Example", Roles.Regular_user,
                "000-001"));
    }

    @Test
    void testExecuteWithoutErrors() {
        RemoveBankAccountRequest request = new RemoveBankAccountRequest("000-001");
        List<CoreError> errors = validator.validate(request);
        RemoveBankAccountResponse response = service.execute(request);
        assertTrue(response.isDeleted());
        assertTrue(errors.isEmpty());
    }

    @Test
    void testExecuteWithErrors() {
        RemoveBankAccountRequest request = new RemoveBankAccountRequest(null);
        List<CoreError> errors = validator.validate(request);
        RemoveBankAccountResponse response = service.execute(request);
        assertFalse(response.isDeleted());
        assertFalse(errors.isEmpty());
    }
}