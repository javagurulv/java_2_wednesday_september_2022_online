package myApp.core.services;

import myApp.core.database.DataBase;
import myApp.core.database.InMemoryDatabaseImpl;
import myApp.core.domain.BankAccount;
import myApp.core.domain.Roles;
import myApp.core.requests.LogInRequest;
import myApp.core.responses.CoreError;
import myApp.core.responses.LogInResponse;
import myApp.core.services.validators.LogInValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LogInServiceTest {

    private DataBase dataBase;
    private UserService userService;
    private LogInService service;
    private LogInValidator validator;

    @BeforeEach
    void setUp() {
        validator = new LogInValidator();
        dataBase = new InMemoryDatabaseImpl();
        userService = new UserService(dataBase);
        service = new LogInService(dataBase, userService, validator);
        dataBase.addBankAccount(new BankAccount("Example", "Example", "password"
                , Roles.Regular_user, "111-111"));
    }

    @Test
    void testExecute() {
        LogInRequest request = new LogInRequest("111-111", "password");
        List<CoreError> errors = validator.validate(request);
        LogInResponse response = service.execute(request);
        assertEquals("111-111", response.getPersonalCode());
        LogInRequest requestTwo = new LogInRequest(" ", " ");
        LogInResponse responseTwo = service.execute(requestTwo);
        assertEquals("Error", responseTwo.getPersonalCode());
        assertTrue(errors.isEmpty());
    }
}