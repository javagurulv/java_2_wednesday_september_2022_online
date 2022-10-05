package myApp.core.services;

import myApp.core.database.DataBase;
import myApp.core.database.InMemoryDatabaseImpl;
import myApp.core.domain.BankAccount;
import myApp.core.domain.Roles;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private DataBase dataBase;
    @InjectMocks
    private UserService service;

    @Test
    public void testGetBankAccountCode() {
       String result = service.logIn("000-001", "password");
       assertEquals("000-001", result);
    }
}

