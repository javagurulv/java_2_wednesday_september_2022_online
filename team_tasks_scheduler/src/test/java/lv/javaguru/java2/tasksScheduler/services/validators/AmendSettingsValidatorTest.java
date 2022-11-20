package lv.javaguru.java2.tasksScheduler.services.validators;

import lv.javaguru.java2.tasksScheduler.requests.AddSettingsRequest;
import lv.javaguru.java2.tasksScheduler.requests.AmendSettingsRequest;
import lv.javaguru.java2.tasksScheduler.responses.CoreError;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AmendSettingsValidatorTest {

    AmendSettingsValidator validator = new AmendSettingsValidator();

    @Test
    public void shouldReturnErrorWhenPasswordIsNull() {
        AmendSettingsRequest request = new AmendSettingsRequest(null, "test@test.com",
                "password", "smtp.gmail.com", "465", "ssl");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Administrator password");
        assertEquals(errors.get(0).getMessage(), "Must be > 3 characters!");
    }

    @Test
    public void shouldReturnErrorWhenPasswordIsEmpty() {
        AmendSettingsRequest request = new AmendSettingsRequest(" ", "test@test.com",
                "password", "smtp.gmail.com", "465", "ssl");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Administrator password");
        assertEquals(errors.get(0).getMessage(), "Must be > 3 characters!");
    }

    @Test
    public void shouldReturnErrorWhenPasswordLengthIsInvalid() {
        AmendSettingsRequest request = new AmendSettingsRequest("ab", "test@test.com",
                "password", "smtp.gmail.com", "465", "ssl");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Administrator password");
        assertEquals(errors.get(0).getMessage(), "Must be > 3 characters!");
    }

    @Test
    public void shouldReturnErrorWhenEmailFromIsNull() {
        AmendSettingsRequest request = new AmendSettingsRequest("admin", null,
                "password", "smtp.gmail.com", "465", "ssl");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Email from");
        assertEquals(errors.get(0).getMessage(), "Must be provided and contain char '@'!");
    }

    @Test
    public void shouldReturnErrorWhenEmailFromIsEmpty() {
        AmendSettingsRequest request = new AmendSettingsRequest("admin", " ",
                "password", "smtp.gmail.com", "465", "ssl");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Email from");
        assertEquals(errors.get(0).getMessage(), "Must be provided and contain char '@'!");
    }

    @Test
    public void shouldReturnErrorWhenEmailFromHasWrongFormat() {
        AmendSettingsRequest request = new AmendSettingsRequest("admin", "test.com",
                "password", "smtp.gmail.com", "465", "ssl");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Email from");
        assertEquals(errors.get(0).getMessage(), "Must be provided and contain char '@'!");
    }

    @Test
    public void shouldReturnErrorWhenEmailPasswordIsNull() {
        AmendSettingsRequest request = new AmendSettingsRequest("admin", "test@test.com",
                null, "smtp.gmail.com", "465", "ssl");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Email password");
        assertEquals(errors.get(0).getMessage(), "Must be provided!");
    }

    @Test
    public void shouldReturnErrorWhenEmailPasswordIsEmpty() {
        AmendSettingsRequest request = new AmendSettingsRequest("admin", "test@test.com",
                " ", "smtp.gmail.com", "465", "ssl");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Email password");
        assertEquals(errors.get(0).getMessage(), "Must be provided!");
    }

    @Test
    public void shouldReturnErrorWhenEmailHostIsNull() {
        AmendSettingsRequest request = new AmendSettingsRequest("admin", "test@test.com",
                "password", null, "465", "ssl");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Email host");
        assertEquals(errors.get(0).getMessage(), "Must be provided!");
    }

    @Test
    public void shouldReturnErrorWhenEmailHostIsEmpty() {
        AmendSettingsRequest request = new AmendSettingsRequest("admin", "test@test.com",
                "password", " ", "465", "ssl");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Email host");
        assertEquals(errors.get(0).getMessage(), "Must be provided!");
    }

    @Test
    public void shouldReturnErrorWhenEmailPortIsNull() {
        AmendSettingsRequest request = new AmendSettingsRequest("admin", "test@test.com",
                "password", "smtp.gmail.com", null, "ssl");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Email port");
        assertEquals(errors.get(0).getMessage(), "Must be provided and be an integer!");
    }

    @Test
    public void shouldReturnErrorWhenEmailPortIsEmpty() {
        AmendSettingsRequest request = new AmendSettingsRequest("admin", "test@test.com",
                "password", "smtp.gmail.com", " ", "ssl");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Email port");
        assertEquals(errors.get(0).getMessage(), "Must be provided and be an integer!");
    }

    @Test
    public void shouldReturnErrorWhenEmailPortIsNotInteger() {
        AmendSettingsRequest request = new AmendSettingsRequest("admin", "test@test.com",
                "password", "smtp.gmail.com", "aaa", "ssl");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Email port");
        assertEquals(errors.get(0).getMessage(), "Must be provided and be an integer!");
    }

    @Test
    public void shouldReturnErrorWhenEmailProtocolIsNull() {
        AmendSettingsRequest request = new AmendSettingsRequest("admin", "test@test.com",
                "password", "smtp.gmail.com", "465", null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Email protocol");
        assertEquals(errors.get(0).getMessage(), "Must be provided!");
    }

    @Test
    public void shouldReturnErrorWhenEmailProtocolIsEmpty() {
        AmendSettingsRequest request = new AmendSettingsRequest("admin", "test@test.com",
                "password", "smtp.gmail.com", "465", " ");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Email protocol");
        assertEquals(errors.get(0).getMessage(), "Must be provided!");
    }

    @Test
    public void shouldSuccess() {
        AmendSettingsRequest request = new AmendSettingsRequest("admin", "test@test.com",
                "password", "smtp.gmail.com", "465", "ssl");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }
}