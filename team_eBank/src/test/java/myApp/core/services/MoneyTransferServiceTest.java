package myApp.core.services;

import myApp.core.database.DataBase;
import myApp.core.database.InMemoryDatabaseImpl;
import myApp.core.domain.BankAccount;
import myApp.core.domain.Roles;
import myApp.core.requests.MoneyTransferRequest;
import myApp.core.responses.CoreError;
import myApp.core.responses.MoneyTransferResponse;
import myApp.core.services.validators.MoneyTransferValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MoneyTransferServiceTest {

    private DataBase dataBase;
    private MoneyTransferValidator validator;
    private MoneyTransferService service;

    @BeforeEach
    void setUp() {
        dataBase = new InMemoryDatabaseImpl();
        validator = new MoneyTransferValidator();
        service = new MoneyTransferService(dataBase, validator);
        BankAccount bankAccountOne = new BankAccount("Example1", "Example2","password",
                Roles.Regular_user, "000-001");
        BankAccount bankAccountTwo = new BankAccount("Example2", "Example3","password",
                Roles.Regular_user, "000-002");
        dataBase.addBankAccount(bankAccountOne);
        dataBase.addBankAccount(bankAccountTwo);
        dataBase.openAccount("000-001");
        dataBase.openAccount("000-002");
    }

    @Test
    void testExecuteWithoutErrors() {
        MoneyTransferRequest request = new MoneyTransferRequest("000-001",
                "000-002", 100);
        List<CoreError> errors = validator.validate(request);
        MoneyTransferResponse response = service.execute(request);
        assertTrue(errors.isEmpty());
        assertTrue(response.isTransactionSucceed());
    }

    @Test
    void testExecuteWitErrors() {
        MoneyTransferRequest request = new MoneyTransferRequest("",
                "",  0);
        List<CoreError> errors = validator.validate(request);
        MoneyTransferResponse response = service.execute(request);
        assertFalse(errors.isEmpty());
        assertFalse(response.isTransactionSucceed());
    }
}