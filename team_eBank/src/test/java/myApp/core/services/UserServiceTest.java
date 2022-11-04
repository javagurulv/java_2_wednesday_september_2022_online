package myApp.core.services;

import myApp.core.database.BankAccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertFalse;



@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private BankAccountRepository bankAccountRepository;
    @InjectMocks
    private UserService service;

    @Test
    public void testGetBankAccountCode() {
       boolean result = service.logIn("000-001", "password");
       assertFalse(result);
    }
}

