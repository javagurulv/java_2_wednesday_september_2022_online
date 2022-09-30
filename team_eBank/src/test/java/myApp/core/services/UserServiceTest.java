package myApp.core.services;

import myApp.core.database.DataBase;
import myApp.core.database.InMemoryDatabaseImpl;
import myApp.core.domain.BankAccount;
import myApp.core.domain.Roles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest {

    private DataBase dataBase;
    private UserService service;
    @BeforeEach
    void setUp() {
        dataBase = new InMemoryDatabaseImpl();
        service = new UserService(dataBase);
        dataBase.addBankAccount(new BankAccount("Example", "Example","password",
                Roles.Regular_user, "000-001"));
    }

    @Test
    void testGetBankAccountCode() {
       String result = service.logIn("000-001", "password");
       assertEquals("000-001", result);
       assertEquals(service.getPersonalCode(), result);
    }
}