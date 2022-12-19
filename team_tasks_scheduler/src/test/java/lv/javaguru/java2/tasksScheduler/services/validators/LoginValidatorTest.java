package lv.javaguru.java2.tasksScheduler.services.validators;

import lv.javaguru.java2.tasksScheduler.core.database.UsersRepository;
import lv.javaguru.java2.tasksScheduler.core.domain.User;
import lv.javaguru.java2.tasksScheduler.core.requests.LoginRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.core.services.validators.LoginValidator;
import lv.javaguru.java2.tasksScheduler.utils.Encryption;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginValidatorTest {

    @Mock private UsersRepository usersRepository;
    @InjectMocks private LoginValidator validator;

    @Test
    public void shouldReturnErrorWhenUsernameIsNull() {
        LoginRequest request = new LoginRequest(null, "password");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Username");
        assertEquals(errors.get(0).getMessage(), "Must be provided.");
    }

    @Test
    public void shouldReturnErrorWhenUsernameIsEmpty() {
        LoginRequest request = new LoginRequest(" ", "password");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Username");
        assertEquals(errors.get(0).getMessage(), "Must be provided.");
    }

    @Test
    public void shouldReturnErrorWhenPasswordIsNull() {
        LoginRequest request = new LoginRequest("username", null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Password");
        assertEquals(errors.get(0).getMessage(), "Must be provided.");
    }

    @Test
    public void shouldReturnErrorWhenPasswordIsEmpty() {
        LoginRequest request = new LoginRequest("username", " ");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Password");
        assertEquals(errors.get(0).getMessage(), "Must be provided.");
    }

    @Test
    public void shouldReturnErrorWhenUsernameIsInvalid() {
        LoginRequest request = new LoginRequest("wrong", "1111");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Username/Password");
        assertEquals(errors.get(0).getMessage(), "Invalid credentials provided.");
    }

    @Test
    public void shouldReturnErrorWhenPasswordIsInvalid() {
        LoginRequest request = new LoginRequest("1111", "wrong");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Username/Password");
        assertEquals(errors.get(0).getMessage(), "Invalid credentials provided.");
    }

    @Test
    public void shouldReturnErrorWhenUsernameAndPasswordAreInvalid() {
        LoginRequest request = new LoginRequest("wrong", "wrong");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Username/Password");
        assertEquals(errors.get(0).getMessage(), "Invalid credentials provided.");
    }

    @Test
    public void shouldSuccessWhenCredentialsAreValid() {
        LoginRequest request = new LoginRequest("1111", "1111");
        when(usersRepository.getUserByNameAndPassword(request.getUserName(),
                Encryption.stringHashing(request.getPassword())))
                .thenReturn(new User(request.getUserName(), Encryption.stringHashing(request.getPassword()),
                        "test@test.com", true));
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }
}