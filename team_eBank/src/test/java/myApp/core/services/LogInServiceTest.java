package myApp.core.services;

import myApp.core.database.DataBase;
import myApp.core.requests.LogInRequest;
import myApp.core.responses.CoreError;
import myApp.core.responses.LogInResponse;
import myApp.core.services.validators.LogInValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class LogInServiceTest {

    @Mock
    private DataBase dataBase;
    @Mock
    private LogInValidator validator;
    @InjectMocks
    private UserService userService;
    @InjectMocks
    private LogInService service;

    @Test
    public void testLogInWithoutErrors() {
        LogInRequest request = new LogInRequest("111-111", "password");
        when(validator.validate(request)).thenReturn(List.of());
        LogInResponse response = service.execute(request);
        assertFalse(response.hasErrors());
    }

    @Test
    public void testLogInWithErrorsInPersonalCode() {
        LogInRequest request = new LogInRequest(null, "password");
        when(validator.validate(request)).thenReturn(List.of(new CoreError("Personal code",
                "Personal code may contains only numbers and cannot be empty")));
        LogInResponse response = service.execute(request);
        assertTrue(response.hasErrors());
    }

    @Test
    public void testLogInWithErrorsInPassword() {
        LogInRequest request = new LogInRequest("111-111", null);
        when(validator.validate(request)).thenReturn(List.of(new CoreError("Password",
                "cannot be empty")));
        LogInResponse response = service.execute(request);
        assertTrue(response.hasErrors());
    }
}