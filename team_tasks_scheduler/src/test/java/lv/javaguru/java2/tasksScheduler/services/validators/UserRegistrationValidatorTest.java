package lv.javaguru.java2.tasksScheduler.services.validators;

import lv.javaguru.java2.tasksScheduler.core.database.UsersRepository;
import lv.javaguru.java2.tasksScheduler.core.domain.User;
import lv.javaguru.java2.tasksScheduler.core.requests.UserRegistrationRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.core.services.validators.UserRegistrationValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserRegistrationValidatorTest {

    @Mock private UsersRepository usersRepository;
    @InjectMocks private UserRegistrationValidator validator;

    @Test
    public void shouldReturnErrorWhenUsernameIsNull() {
        User addedUser = new User(null, "password",
                "test@test.com", false);
        UserRegistrationRequest request = new UserRegistrationRequest(addedUser);
        when(usersRepository.existsByName(request.getUsername())).thenReturn(false);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "User name");
        assertEquals(errors.get(0).getMessage(), "Has to be longer than 3 chars!");
    }

    @Test
    public void shouldReturnErrorWhenUsernameIsEmpty() {
        User addedUser = new User(" ", "password",
                "test@test.com", false);
        UserRegistrationRequest request = new UserRegistrationRequest(addedUser);
        when(usersRepository.existsByName(request.getUsername())).thenReturn(false);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "User name");
        assertEquals(errors.get(0).getMessage(), "Has to be longer than 3 chars!");
    }

    @Test
    public void shouldReturnErrorWhenUsernameHasInvalidLength() {
        User addedUser = new User("abc", "password",
                "test@test.com", false);
        UserRegistrationRequest request = new UserRegistrationRequest(addedUser);
        when(usersRepository.existsByName(request.getUsername())).thenReturn(false);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "User name");
        assertEquals(errors.get(0).getMessage(), "Has to be longer than 3 chars!");
    }

    @Test
    public void shouldReturnErrorWhenEmailIsNull() {
        User addedUser = new User("username", "password",
                null, false);
        UserRegistrationRequest request = new UserRegistrationRequest(addedUser);
        when(usersRepository.existsByName(request.getUsername())).thenReturn(false);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "E-mail");
        assertEquals(errors.get(0).getMessage(), "Has to contain char '@'!");
    }

    @Test
    public void shouldReturnErrorWhenEmailIsEmpty() {
        User addedUser = new User("username", "password",
                " ", false);
        UserRegistrationRequest request = new UserRegistrationRequest(addedUser);
        when(usersRepository.existsByName(request.getUsername())).thenReturn(false);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "E-mail");
        assertEquals(errors.get(0).getMessage(), "Has to contain char '@'!");
    }


    @Test
    public void shouldReturnErrorWhenEmailIsWithoutAt() {
        User addedUser = new User("username", "password",
                "test.test.com", false);
        UserRegistrationRequest request = new UserRegistrationRequest(addedUser);
        when(usersRepository.existsByName(request.getUsername())).thenReturn(false);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "E-mail");
        assertEquals(errors.get(0).getMessage(), "Has to contain char '@'!");
    }

    @Test
    public void shouldReturnErrorWhenPasswordIsNull() {
        User addedUser = new User("username", null,
                "test@test.com", false);
        UserRegistrationRequest request = new UserRegistrationRequest(addedUser);
        when(usersRepository.existsByName(request.getUsername())).thenReturn(false);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Password");
        assertEquals(errors.get(0).getMessage(), "Should be >3 characters!");
    }

    @Test
    public void shouldReturnErrorWhenPasswordIsEmpty() {
        User addedUser = new User("username", " ",
                "test@test.com", false);
        UserRegistrationRequest request = new UserRegistrationRequest(addedUser);
        when(usersRepository.existsByName(request.getUsername())).thenReturn(false);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Password");
        assertEquals(errors.get(0).getMessage(), "Should be >3 characters!");
    }

    @Test
    public void shouldReturnErrorWhenPasswordHasInvalidLength() {
        User addedUser = new User("username", "pas",
                "test@test.com", false);
        UserRegistrationRequest request = new UserRegistrationRequest(addedUser);
        when(usersRepository.existsByName(request.getUsername())).thenReturn(false);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Password");
        assertEquals(errors.get(0).getMessage(), "Should be >3 characters!");
    }

    @Test
    public void shouldReturnErrorWhenDuplicateUsername() {
        User addedUser = new User("username", "password",
                "test@test.com", false);
        UserRegistrationRequest request = new UserRegistrationRequest(addedUser);
        when(usersRepository.existsByName(request.getUsername())).thenReturn(true);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Username");
        assertEquals(errors.get(0).getMessage(), "This username is already used!");
    }

    @Test
    public void shouldSuccessWhenValidDataIsProvided() {
        User addedUser = new User("username", "password",
                "test@test.com", false);
        UserRegistrationRequest request = new UserRegistrationRequest(addedUser);
        when(usersRepository.existsByName(request.getUsername())).thenReturn(false);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }


}