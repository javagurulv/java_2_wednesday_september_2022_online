package myApp.core.services;

import myApp.core.database.BankRepository;
import myApp.core.services.authentication.UserService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertFalse;

//need to fix
@Ignore
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private BankRepository bankRepository;
    @InjectMocks
    private UserService service;

    @Test
    public void testGetBankAccountCode() {
       boolean result = service.logIn("000-001", "password");
       assertFalse(result);
    }
}

