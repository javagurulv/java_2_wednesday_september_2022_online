package myApp.core.services;

import myApp.core.database.DataBase;
import myApp.core.requests.LogInRequest;
import myApp.core.requests.SwitchUserRequest;
import myApp.core.responses.LogInResponse;
import myApp.core.responses.SwitchUserResponse;
import myApp.core.services.validators.LogInValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertNotEquals;

@RunWith(MockitoJUnitRunner.class)
public class SwitchUserServiceTest {

    @Mock
    private DataBase dataBase;
    @InjectMocks
    private LogInService logInService;
    @Mock
    private LogInValidator validator;
    @Mock
    private UserService service;
    @Mock
    private SwitchUserService switchUserService;

    @Test
    public void testSwitchUser() {
        LogInRequest request = new LogInRequest("000-111", "password");
        LogInResponse response = logInService.execute(request);
        SwitchUserRequest switchUserRequest = new SwitchUserRequest("01", "password");
        String switchUserResponse = switchUserService.execute(switchUserRequest);
        assertNotEquals(switchUserResponse, response.getPersonalCode());
        assertFalse(response.hasErrors());
    }
}
