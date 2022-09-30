package myApp.core.services;

import myApp.core.database.DataBase;
import myApp.core.database.InMemoryDatabaseImpl;
import myApp.core.domain.BankAccount;
import myApp.core.domain.Roles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserAreAdminServiceTest {

    private DataBase dataBase;
    private UserAreAdminService service;

    @BeforeEach
    void setUp() {
        dataBase = new InMemoryDatabaseImpl();
        service = new UserAreAdminService(dataBase);
        dataBase.addBankAccount(new BankAccount("Example", "Example","password"
                , Roles.Regular_user, "000-001"));
        dataBase.addBankAccount(new BankAccount("Example1", "Example2","password"
                , Roles.Admin, "000-002"));
    }

    @Test
    void testUserAreAdmin() {
        boolean result = service.isUserAreAdmin("000-002");
        assertTrue(result);
    }

    @Test
    void testUserAreNotAdmin() {
        boolean result = service.isUserAreAdmin("000-001");
        assertFalse(result);
    }
}