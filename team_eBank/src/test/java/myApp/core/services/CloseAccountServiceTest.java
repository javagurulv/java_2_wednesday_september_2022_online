package myApp.core.services;

import myApp.core.database.DataBase;
import myApp.core.database.InMemoryDatabaseImpl;
import myApp.core.domain.Account;
import myApp.core.domain.BankAccount;
import myApp.core.domain.Roles;
import myApp.core.requests.CloseAccountRequest;
import myApp.core.responses.CloseAccountResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CloseAccountServiceTest {

    private DataBase dataBase;
    private CloseAccountService service;

    @BeforeEach
    void setUp() {
        dataBase = new InMemoryDatabaseImpl();
        service = new CloseAccountService(dataBase);
        BankAccount bankAccount = new BankAccount("Example1", "Example2",
                Roles.Regular_user, "000-001");
        dataBase.addBankAccount(bankAccount);
        bankAccount.setAccount(new Account(2L, 0));

    }

    @Test
    void testCloseAccount() {
        CloseAccountRequest request = new CloseAccountRequest("000-001");
        CloseAccountResponse response = service.execute(request);
        assertTrue(response.isDeleted());
    }
}