package myApp.core.services;

import myApp.core.database.DataBase;
import myApp.core.database.InMemoryDatabaseImpl;
import myApp.core.requests.AddBankAccountRequest;
import myApp.core.responses.AddBankAccountResponse;
import myApp.core.responses.CoreError;
import myApp.core.services.validators.AddBankAccountValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddBankAccountServiceTest {

    DataBase dataBase;
    AddBankAccountValidator validator;
    AddBankAccountService service;

    @BeforeEach
    void setUp() {
        dataBase = new InMemoryDatabaseImpl();
        validator = new AddBankAccountValidator();
        service = new AddBankAccountService(dataBase,validator);
    }

    @Test
    void testExecuteWithoutErrors() {
        AddBankAccountRequest request = new AddBankAccountRequest("Example", "Example",
                "000-001");
        List<CoreError> errors = validator.validate(request);
        AddBankAccountResponse response = service.execute(request);
        assertTrue(errors.isEmpty());
        assertNotNull(response.getBankAccount());
        assertFalse(response.hasErrors());
    }
    @Test
    void testExecuteWithErrors() {
        AddBankAccountRequest request = new AddBankAccountRequest(" ", " ",
                "000-001");
        List<CoreError> errors = validator.validate(request);
        AddBankAccountResponse response = service.execute(request);
        assertNull(response.getBankAccount());
        assertFalse(errors.isEmpty());
        assertTrue(response.hasErrors());
    }
}