package myApp.core.services;

import myApp.core.database.DataBase;
import myApp.core.database.InMemoryDatabaseImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class SwitchUserServiceTest {

    DataBase dataBase;
    UserService service;
    SwitchUserService switchUserService;

    @BeforeEach
    void setUp() {
        dataBase = new InMemoryDatabaseImpl();
        service = new UserService(dataBase);
        switchUserService = new SwitchUserService(service);
    }

    @Test
    void testSwitchUser() {
        String personalCode = service.logIn("111-317");
        String actualResult = switchUserService.execute("01");
        assertNotEquals(personalCode, actualResult);
    }
}