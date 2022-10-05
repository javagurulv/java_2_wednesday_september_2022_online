package myApp.core.services;

import myApp.core.database.DataBase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.*;

@RunWith(MockitoJUnitRunner.class)
public class UserAreAdminServiceTest {

    @Mock
    private DataBase dataBase;

    @Mock
    private UserAreAdminService service;

    @Test
    public void testUserAreAdmin() {
        boolean result = service.isUserAreAdmin("111-111");
        assertFalse(result);
    }

}

