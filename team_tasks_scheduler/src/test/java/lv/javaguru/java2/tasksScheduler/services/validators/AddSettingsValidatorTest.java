package lv.javaguru.java2.tasksScheduler.services.validators;

import lv.javaguru.java2.tasksScheduler.requests.AddSettingsRequest;
import lv.javaguru.java2.tasksScheduler.responses.CoreError;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AddSettingsValidatorTest {

    private AddSettingsValidator validator = new AddSettingsValidator();

    @Test
    public void shouldReturnErrorWhenPasswordIsNull() {
        AddSettingsRequest request = new AddSettingsRequest(null, "test@test.com",
                "password", "smtp.gmail.com", "465", "ssl");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Administrator password");
        assertEquals(errors.get(0).getMessage(), "Must be > 3 characters!");
    }

    @Test
    public void shouldReturnErrorWhenPasswordIsEmpty() {
        AddSettingsRequest request = new AddSettingsRequest(" ", "test@test.com",
                "password", "smtp.gmail.com", "465", "ssl");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Administrator password");
        assertEquals(errors.get(0).getMessage(), "Must be > 3 characters!");
    }

    @Test
    public void shouldReturnErrorWhenPasswordLengthIsInvalid() {
        AddSettingsRequest request = new AddSettingsRequest("ab", "test@test.com",
                "password", "smtp.gmail.com", "465", "ssl");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Administrator password");
        assertEquals(errors.get(0).getMessage(), "Must be > 3 characters!");
    }

    @Test
    public void shouldReturnErrorWhenEmailFromIsNull() {
        AddSettingsRequest request = new AddSettingsRequest("admin", null,
                "password", "smtp.gmail.com", "465", "ssl");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Email from");
        assertEquals(errors.get(0).getMessage(), "Must be provided and contain char '@'!");
    }

    @Test
    public void shouldReturnErrorWhenEmailFromIsEmpty() {
        AddSettingsRequest request = new AddSettingsRequest("admin", " ",
                "password", "smtp.gmail.com", "465", "ssl");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Email from");
        assertEquals(errors.get(0).getMessage(), "Must be provided and contain char '@'!");
    }

    @Test
    public void shouldReturnErrorWhenEmailFromHasWrongFormat() {
        AddSettingsRequest request = new AddSettingsRequest("admin", "test.com",
                "password", "smtp.gmail.com", "465", "ssl");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Email from");
        assertEquals(errors.get(0).getMessage(), "Must be provided and contain char '@'!");
    }

    @Test
    public void shouldReturnErrorWhenEmailPasswordIsNull() {
        AddSettingsRequest request = new AddSettingsRequest("admin", "test@test.com",
                null, "smtp.gmail.com", "465", "ssl");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Email password");
        assertEquals(errors.get(0).getMessage(), "Must be provided!");
    }

    @Test
    public void shouldReturnErrorWhenEmailPasswordIsEmpty() {
        AddSettingsRequest request = new AddSettingsRequest("admin", "test@test.com",
                " ", "smtp.gmail.com", "465", "ssl");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Email password");
        assertEquals(errors.get(0).getMessage(), "Must be provided!");
    }

    @Test
    public void shouldReturnErrorWhenEmailHostIsNull() {
        AddSettingsRequest request = new AddSettingsRequest("admin", "test@test.com",
                "password", null, "465", "ssl");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Email host");
        assertEquals(errors.get(0).getMessage(), "Must be provided!");
    }

    @Test
    public void shouldReturnErrorWhenEmailHostIsEmpty() {
        AddSettingsRequest request = new AddSettingsRequest("admin", "test@test.com",
                "password", " ", "465", "ssl");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Email host");
        assertEquals(errors.get(0).getMessage(), "Must be provided!");
    }

    @Test
    public void shouldReturnErrorWhenEmailPortIsNull() {
        AddSettingsRequest request = new AddSettingsRequest("admin", "test@test.com",
                "password", "smtp.gmail.com", null, "ssl");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Email port");
        assertEquals(errors.get(0).getMessage(), "Must be provided and be an integer!");
    }

    @Test
    public void shouldReturnErrorWhenEmailPortIsEmpty() {
        AddSettingsRequest request = new AddSettingsRequest("admin", "test@test.com",
                "password", "smtp.gmail.com", " ", "ssl");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Email port");
        assertEquals(errors.get(0).getMessage(), "Must be provided and be an integer!");
    }

    @Test
    public void shouldReturnErrorWhenEmailPortIsNotInteger() {
        AddSettingsRequest request = new AddSettingsRequest("admin", "test@test.com",
                "password", "smtp.gmail.com", "aaa", "ssl");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Email port");
        assertEquals(errors.get(0).getMessage(), "Must be provided and be an integer!");
    }

    @Test
    public void shouldReturnErrorWhenEmailProtocolIsNull() {
        AddSettingsRequest request = new AddSettingsRequest("admin", "test@test.com",
                "password", "smtp.gmail.com", "465", null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Email protocol");
        assertEquals(errors.get(0).getMessage(), "Must be provided!");
    }

    @Test
    public void shouldReturnErrorWhenEmailProtocolIsEmpty() {
        AddSettingsRequest request = new AddSettingsRequest("admin", "test@test.com",
                "password", "smtp.gmail.com", "465", " ");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Email protocol");
        assertEquals(errors.get(0).getMessage(), "Must be provided!");
    }

    @Test
    public void shouldSuccess() {
        AddSettingsRequest request = new AddSettingsRequest("admin", "test@test.com",
                "password", "smtp.gmail.com", "465", "ssl");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }
}