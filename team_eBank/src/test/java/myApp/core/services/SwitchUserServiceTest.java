package myApp.core.services;

import myApp.core.database.DataBase;
import myApp.core.database.InMemoryDatabaseImpl;
import myApp.core.services.validators.LogInValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SwitchUserServiceTest {

    DataBase dataBase;
    UserService service;
    SwitchUserService switchUserService;

    @BeforeEach
    void setUp() {
        dataBase = new InMemoryDatabaseImpl();
        service = new UserService(dataBase);
        switchUserService = new SwitchUserService(service, new LogInService(dataBase,service, new LogInValidator()));
    }

    @Test
    void testSwitchUser() {
        String personalCode = service.logIn("111-317", "password");
        String actualResult = switchUserService.execute("01", "password");
        assertNotEquals(personalCode, actualResult);
    }
}