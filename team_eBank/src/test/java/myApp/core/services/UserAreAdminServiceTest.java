package myApp.core.services;

import myApp.core.database.BankRepository;
import myApp.core.services.authentication.UserAreAdminService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.*;

@RunWith(MockitoJUnitRunner.class)
public class UserAreAdminServiceTest {

    @Mock
    private BankRepository bankRepository;
    @Mock
    private UserAreAdminService service;

    @Test
    public void testUserAreAdmin() {

        boolean result = service.isUserAreAdmin("111-111");
        assertFalse(result);
    }

}

