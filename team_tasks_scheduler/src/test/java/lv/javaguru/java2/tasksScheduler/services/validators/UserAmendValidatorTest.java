package lv.javaguru.java2.tasksScheduler.services.validators;


import lv.javaguru.java2.tasksScheduler.core.database.jpa.JpaUsersRepository;
import lv.javaguru.java2.tasksScheduler.core.domain.User;
import lv.javaguru.java2.tasksScheduler.core.requests.AmendCurrentUserRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.core.services.system.SessionService;
import lv.javaguru.java2.tasksScheduler.core.services.validators.UserAmendValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserAmendValidatorTest {

    @Mock private JpaUsersRepository usersRepository;
    @Mock private SessionService sessionService;
    @InjectMocks private UserAmendValidator validator;

    @Test
    public void shouldReturnErrorWhenCannotGetCurrentUser() {
        User amendedUser = new User("amended", "password",
                "test@test.com", false);
        AmendCurrentUserRequest request = new AmendCurrentUserRequest(amendedUser);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "User");
        assertEquals(errors.get(0).getMessage(), "Problem occurs deriving current user details!");
    }

    @Test
    public void shouldReturnErrorWhenDuplicateUser() {
        User currentUser = new User("current", "password",
                "test@test.com", false);
        User amendedUser = new User("amended", "password",
                "test@test.com", false);
        AmendCurrentUserRequest request = new AmendCurrentUserRequest(amendedUser);
        when(sessionService.getCurrentUserId()).thenReturn(1L);
        when(usersRepository.findUserById(1L)).thenReturn(currentUser);
        when(usersRepository.existsByUsername(request.getUsername())).thenReturn(true);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "User");
        assertEquals(errors.get(0).getMessage(), "Already exists in the database!");
    }

    @Test
    public void shouldSuccessWhenCurrentUserIsAmended() {
        User currentUser = new User("current", "password",
                "test@test.com", false);
        User amendedUser = new User("current", "password",
                "test@test.com", false);
        AmendCurrentUserRequest request = new AmendCurrentUserRequest(amendedUser);
        when(sessionService.getCurrentUserId()).thenReturn(1L);
        when(usersRepository.findUserById(1L)).thenReturn(currentUser);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

    @Test
    public void shouldReturnErrorWhenUsernameIsNull() {
        User currentUser = new User("current", "password",
                "test@test.com", false);
        User amendedUser = new User(null, "password",
                "test@test.com", false);
        AmendCurrentUserRequest request = new AmendCurrentUserRequest(amendedUser);
        when(sessionService.getCurrentUserId()).thenReturn(1L);
        when(usersRepository.findUserById(1L)).thenReturn(currentUser);
        when(usersRepository.existsByUsername(request.getUsername())).thenReturn(false);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "User name");
        assertEquals(errors.get(0).getMessage(), "Has to be longer than 3 chars!");
    }

    @Test
    public void shouldReturnErrorWhenUsernameIsEmpty() {
        User currentUser = new User("current", "password",
                "test@test.com", false);
        User amendedUser = new User(" ", "password",
                "test@test.com", false);
        AmendCurrentUserRequest request = new AmendCurrentUserRequest(amendedUser);
        when(sessionService.getCurrentUserId()).thenReturn(1L);
        when(usersRepository.findUserById(1L)).thenReturn(currentUser);
        when(usersRepository.existsByUsername(request.getUsername())).thenReturn(false);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "User name");
        assertEquals(errors.get(0).getMessage(), "Has to be longer than 3 chars!");
    }

    @Test
    public void shouldReturnErrorWhenUsernameHasInvalidLength() {
        User currentUser = new User("current", "password",
                "test@test.com", false);
        User amendedUser = new User("abc", "password",
                "test@test.com", false);
        AmendCurrentUserRequest request = new AmendCurrentUserRequest(amendedUser);
        when(sessionService.getCurrentUserId()).thenReturn(1L);
        when(usersRepository.findUserById(1L)).thenReturn(currentUser);
        when(usersRepository.existsByUsername(request.getUsername())).thenReturn(false);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "User name");
        assertEquals(errors.get(0).getMessage(), "Has to be longer than 3 chars!");
    }

    @Test
    public void shouldReturnErrorWhenEmailIsNull() {
        User currentUser = new User("current", "password",
                "test@test.com", false);
        User amendedUser = new User("amended", "password",
                null, false);
        AmendCurrentUserRequest request = new AmendCurrentUserRequest(amendedUser);
        when(sessionService.getCurrentUserId()).thenReturn(1L);
        when(usersRepository.findUserById(1L)).thenReturn(currentUser);
        when(usersRepository.existsByUsername(request.getUsername())).thenReturn(false);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "E-mail");
        assertEquals(errors.get(0).getMessage(), "Has to contain char '@'!");
    }

    @Test
    public void shouldReturnErrorWhenEmailIsEmpty() {
        User currentUser = new User("current", "password",
                "test@test.com", false);
        User amendedUser = new User("amended", "password",
                " ", false);
        AmendCurrentUserRequest request = new AmendCurrentUserRequest(amendedUser);
        when(sessionService.getCurrentUserId()).thenReturn(1L);
        when(usersRepository.findUserById(1L)).thenReturn(currentUser);
        when(usersRepository.existsByUsername(request.getUsername())).thenReturn(false);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "E-mail");
        assertEquals(errors.get(0).getMessage(), "Has to contain char '@'!");
    }

    @Test
    public void shouldReturnErrorWhenEmailIsWithoutAt() {
        User currentUser = new User("current", "password",
                "test@test.com", false);
        User amendedUser = new User("amended", "password",
                "test.test.com", false);
        AmendCurrentUserRequest request = new AmendCurrentUserRequest(amendedUser);
        when(sessionService.getCurrentUserId()).thenReturn(1L);
        when(usersRepository.findUserById(1L)).thenReturn(currentUser);
        when(usersRepository.existsByUsername(request.getUsername())).thenReturn(false);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "E-mail");
        assertEquals(errors.get(0).getMessage(), "Has to contain char '@'!");
    }

    @Test
    public void shouldReturnErrorWhenPasswordIsNull() {
        User currentUser = new User("current", "password",
                "test@test.com", false);
        User amendedUser = new User("amended", null,
                "test@test.com", false);
        AmendCurrentUserRequest request = new AmendCurrentUserRequest(amendedUser);
        when(sessionService.getCurrentUserId()).thenReturn(1L);
        when(usersRepository.findUserById(1L)).thenReturn(currentUser);
        when(usersRepository.existsByUsername(request.getUsername())).thenReturn(false);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Password");
        assertEquals(errors.get(0).getMessage(), "Should be > 3 characters!");
    }

    @Test
    public void shouldReturnErrorWhenPasswordIsEmpty() {
        User currentUser = new User("current", "password",
                "test@test.com", false);
        User amendedUser = new User("amended", " ",
                "test@test.com", false);
        AmendCurrentUserRequest request = new AmendCurrentUserRequest(amendedUser);
        when(sessionService.getCurrentUserId()).thenReturn(1L);
        when(usersRepository.findUserById(1L)).thenReturn(currentUser);
        when(usersRepository.existsByUsername(request.getUsername())).thenReturn(false);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Password");
        assertEquals(errors.get(0).getMessage(), "Should be > 3 characters!");
    }

    @Test
    public void shouldReturnErrorWhenPasswordHasInvalidLength() {
        User currentUser = new User("current", "password",
                "test@test.com", false);
        User amendedUser = new User("amended", "pas",
                "test@test.com", false);
        AmendCurrentUserRequest request = new AmendCurrentUserRequest(amendedUser);
        when(sessionService.getCurrentUserId()).thenReturn(1L);
        when(usersRepository.findUserById(1L)).thenReturn(currentUser);
        when(usersRepository.existsByUsername(request.getUsername())).thenReturn(false);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Password");
        assertEquals(errors.get(0).getMessage(), "Should be > 3 characters!");
    }

    @Test
    public void shouldSuccessWhenValidDataIsProvided() {
        User currentUser = new User("current", "password",
                "test@test.com", false);
        User amendedUser = new User("amended", "password",
                "test@test.com", false);
        AmendCurrentUserRequest request = new AmendCurrentUserRequest(amendedUser);
        when(sessionService.getCurrentUserId()).thenReturn(1L);
        when(usersRepository.findUserById(1L)).thenReturn(currentUser);
        when(usersRepository.existsByUsername(request.getUsername())).thenReturn(false);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }

}