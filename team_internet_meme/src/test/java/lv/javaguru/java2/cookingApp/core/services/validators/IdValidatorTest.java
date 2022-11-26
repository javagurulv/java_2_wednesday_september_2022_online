package lv.javaguru.java2.cookingApp.core.services.validators;

import lv.javaguru.java2.cookingApp.core.dto.requests.PrintRecipeToConsoleRequest;
import lv.javaguru.java2.cookingApp.core.dto.responses.CoreError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IdValidatorTest {

    IdValidator validator;
    @BeforeEach
    void setUp() {
        validator = new IdValidator();
    }

    @Test
    void testShouldReturnNoErrors() {
        PrintRecipeToConsoleRequest request = new PrintRecipeToConsoleRequest(1L);
        List<CoreError> errors = validator.validate(request.getId());
        assertTrue(errors.isEmpty());
    }

    @Test
    void testShouldReturnIdError() {
        PrintRecipeToConsoleRequest request = new PrintRecipeToConsoleRequest(null);
        List<CoreError> errors = validator.validate(request.getId());
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Recipe ID");
        assertEquals(errors.get(0).getMessage(), "Cannot be null");
    }
}