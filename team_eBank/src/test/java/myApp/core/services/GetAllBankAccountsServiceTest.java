package myApp.core.services;

import myApp.core.database.DataBase;
import myApp.core.database.InMemoryDatabaseImpl;
import myApp.core.requests.GetAllBankAccountsRequest;
import myApp.core.responses.GetAllBankAccountsResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GetAllBankAccountsServiceTest {

    private DataBase dataBase;
    private GetAllBankAccountsRequest request;
    private GetAllBankAccountsService service;
    @BeforeEach
    void setUp() {
        dataBase = new InMemoryDatabaseImpl();
        request = new GetAllBankAccountsRequest();
        service = new GetAllBankAccountsService(dataBase);
    }

    @Test
    void execute() {
        GetAllBankAccountsResponse response = service.execute(request);
        assertNotNull(response.getBankAccounts());
    }
}