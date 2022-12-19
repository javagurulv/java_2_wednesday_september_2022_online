package lv.javaguru.java2.tasksScheduler.services.validators;

import lv.javaguru.java2.tasksScheduler.core.database.SettingsRepository;
import lv.javaguru.java2.tasksScheduler.core.requests.SettingsLoginRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.core.services.validators.SettingsLoginValidator;
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
public class SettingsLoginValidatorTest {

    @Mock private SettingsRepository settingsRepository;
    @InjectMocks private SettingsLoginValidator validator;

    @Test
    public void shouldReturnErrorWhenPasswordIsNull() {
        SettingsLoginRequest request = new SettingsLoginRequest(null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Administrator password");
        assertEquals(errors.get(0).getMessage(), "Is invalid.");
    }

    @Test
    public void shouldReturnErrorWhenPasswordIsEmpty() {
        SettingsLoginRequest request = new SettingsLoginRequest(" ");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Administrator password");
        assertEquals(errors.get(0).getMessage(), "Is invalid.");
    }

    @Test
    public void shouldReturnErrorWhenPasswordIsInvalid() {
        SettingsLoginRequest request = new SettingsLoginRequest("wrong");
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Administrator password");
        assertEquals(errors.get(0).getMessage(), "Is invalid.");
    }

    @Test
    public void shouldSuccessWhenPasswordIsValid() {
        SettingsLoginRequest request = new SettingsLoginRequest("admin");
        when(settingsRepository.passwordIsValid(Encryption.stringHashing(request.getAdminPassword())))
                .thenReturn(true);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 0);
    }
}