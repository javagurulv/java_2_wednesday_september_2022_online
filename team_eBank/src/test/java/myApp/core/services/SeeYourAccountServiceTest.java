package myApp.core.services;

import myApp.core.database.DataBase;
import myApp.core.requests.SeeYourAccountRequest;
import myApp.core.responses.SeeYourAccountResponse;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class SeeYourAccountServiceTest {

    @Mock
    private DataBase dataBase;
    @InjectMocks
    private SeeYourAccountService service;

    @Test
    public void testSeeAccount() {
        SeeYourAccountRequest request = new SeeYourAccountRequest("000-001");
        SeeYourAccountResponse response = service.execute(request);
        verify(dataBase).seeYourAccount("000-001");
    }
}
