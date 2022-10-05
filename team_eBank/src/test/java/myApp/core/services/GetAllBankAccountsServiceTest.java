package myApp.core.services;
import myApp.core.database.DataBase;
import myApp.core.database.InMemoryDatabaseImpl;
import myApp.core.requests.GetAllBankAccountsRequest;
import myApp.core.responses.GetAllBankAccountsResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class GetAllBankAccountsServiceTest {

    @Mock
    private DataBase dataBase;
    @InjectMocks
    private GetAllBankAccountsService service;

    @Test
   public void execute() {
        GetAllBankAccountsRequest request = new GetAllBankAccountsRequest();
        GetAllBankAccountsResponse response = service.execute(request);
        assertNotNull(response);
    }
}

