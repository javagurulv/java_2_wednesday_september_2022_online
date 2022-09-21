package lv.javaguru.java2.cookingApp.services.validators;

import lv.javaguru.java2.cookingApp.requests.DeleteRecipeRequest;
import lv.javaguru.java2.cookingApp.responses.CoreError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeleteRecipeValidatorTest {

    DeleteRecipeValidator validator;

    @BeforeEach
    void setUp() {
        validator = new DeleteRecipeValidator();
    }

    @Test
    void testShouldReturnNoErrors() {
        DeleteRecipeRequest request = new DeleteRecipeRequest(1L);
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testShouldReturnIdError() {
        DeleteRecipeRequest request = new DeleteRecipeRequest(null);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Recipe ID");
        assertEquals(errors.get(0).getMessage(), "Id cannot be null!");
    }
}