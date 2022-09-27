package myApp.core.services;

import myApp.core.database.DataBase;
import myApp.core.database.InMemoryDatabaseImpl;
import myApp.core.domain.BankAccount;
import myApp.core.domain.Roles;
import myApp.core.requests.SeeYourAccountRequest;
import myApp.core.responses.SeeYourAccountResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SeeYourAccountServiceTest {

    private DataBase dataBase;
    private SeeYourAccountService service;

    @BeforeEach
    void setUp() {
        dataBase = new InMemoryDatabaseImpl();
        dataBase.addBankAccount(new BankAccount("Example", "Example"
                , Roles.Regular_user, "000-001"));
        service = new SeeYourAccountService(dataBase);
    }

    @Test
    void testExecuteWithoutErrors() {
        SeeYourAccountRequest request = new SeeYourAccountRequest("000-001");
        SeeYourAccountResponse response = service.execute(request);
        assertNotNull(response.getBankAccount());
    }
}