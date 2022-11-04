package myApp.core.services;

import myApp.core.database.BankAccountRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.*;

@RunWith(MockitoJUnitRunner.class)
public class UserAreAdminServiceTest {

    @Mock
    private BankAccountRepository bankAccountRepository;
    @Mock
    private UserAreAdminService service;

    @Test
    public void testUserAreAdmin() {

        boolean result = service.isUserAreAdmin("111-111");
        assertFalse(result);
    }

}

