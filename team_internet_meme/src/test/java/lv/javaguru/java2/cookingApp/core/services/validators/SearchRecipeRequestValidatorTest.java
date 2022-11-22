package lv.javaguru.java2.cookingApp.core.services.validators;

import lv.javaguru.java2.cookingApp.core.dto.requests.SearchRecipeRequest;
import lv.javaguru.java2.cookingApp.core.dto.responses.CoreError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class SearchRecipeRequestValidatorTest {

    SearchRecipeRequestValidator validator;

    @BeforeEach
    void setUp() {
        validator = new SearchRecipeRequestValidator();
    }

    @Test
    void testValidateReturns3Errors() {
        List<String> ingredients = new ArrayList<>();
        ingredients.add("");
        ingredients.add("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        ingredients.add("eggs");
        ingredients.add("eggs");
        SearchRecipeRequest request = new SearchRecipeRequest(ingredients);
        List<CoreError> coreErrors = validator.validate(request);
        assertFalse(coreErrors.isEmpty());
        assertEquals(3, coreErrors.size());
        assertEquals("Ingredient name", coreErrors.get(0).getField());
        assertEquals("cannot be empty", coreErrors.get(0).getMessage());
        assertEquals("Ingredient name", coreErrors.get(1).getField());
        assertEquals("cannot be longer that 30 characters", coreErrors.get(1).getMessage());
        assertEquals("Ingredient", coreErrors.get(2).getField());
        assertEquals("cannot enter duplicates", coreErrors.get(2).getMessage());
    }

    @Test
    void testValidateRequestIsNotEmptyShouldReturnErrorWhenRequestIsEmpty() {
        List<String> emptyList = new ArrayList<>();
        SearchRecipeRequest request = new SearchRecipeRequest(emptyList);
        Optional<CoreError> error = validator.validateRequestIsNotEmpty(request);
        assertTrue(error.isPresent());
        assertEquals("Ingredient to search", error.get().getField());
        assertEquals("must enter at least 1 ingredient", error.get().getMessage());
    }
    @Test
    void testValidateRequestIsNotEmptyShouldReturnNoErrorWhenRequestIsNotEmpty() {
        List<String> ingredients = new ArrayList<>();
        ingredients.add("bacon");
        SearchRecipeRequest request = new SearchRecipeRequest(ingredients);
        Optional<CoreError> error = validator.validateRequestIsNotEmpty(request);
        assertTrue(error.isEmpty());
    }

    @Test
    void testValidateIngredientNameShouldReturnErrorWhenIngredientWithEmptyNameIsPresent() {
        List<String> ingredients = new ArrayList<>();
        ingredients.add("bacon");
        ingredients.add("");
        SearchRecipeRequest request = new SearchRecipeRequest(ingredients);
        Optional<CoreError> error = validator.validateIngredientNamesAreNotEmpty(request);
        assertTrue(error.isPresent());
        assertEquals("Ingredient name", error.get().getField());
        assertEquals("cannot be empty", error.get().getMessage());
    }

    @Test
    void testValidateIngredientNameShouldReturnNoErrorWhenAllIngredientsHaveNames() {
        List<String> ingredients = new ArrayList<>();
        ingredients.add("bacon");
        ingredients.add("eggs");
        SearchRecipeRequest request = new SearchRecipeRequest(ingredients);
        Optional<CoreError> error = validator.validateIngredientNamesAreNotEmpty(request);
        assertTrue(error.isEmpty());
    }

    @Test
    void testValidateIngredientNameShouldReturnErrorWhenIngredientWithNameLongerThan30CharsIsPresent() {
        List<String> ingredients = new ArrayList<>();
        ingredients.add("bacon");
        ingredients.add("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        SearchRecipeRequest request = new SearchRecipeRequest(ingredients);
        Optional<CoreError> error = validator.validateIngredientNameLength(request);
        assertTrue(error.isPresent());
        assertEquals("Ingredient name", error.get().getField());
        assertEquals("cannot be longer that 30 characters", error.get().getMessage());
    }

    @Test
    void testValidateIngredientNameShouldReturnNoErrorWhenNoIngredientWithNameLongerThan30CharsIsPresent() {
        List<String> ingredients = new ArrayList<>();
        ingredients.add("bacon");
        ingredients.add("eggs");
        SearchRecipeRequest request = new SearchRecipeRequest(ingredients);
        Optional<CoreError> error = validator.validateIngredientNameLength(request);
        assertTrue(error.isEmpty());
    }

    @Test
    void testValidateNoDuplicateIngredientsShouldReturnNoError() {
        List<String> ingredients = new ArrayList<>();
        ingredients.add("bacon");
        ingredients.add("eggs");
        SearchRecipeRequest request = new SearchRecipeRequest(ingredients);
        Optional<CoreError> error = validator.validateNoDuplicateIngredient(request);
        assertTrue(error.isEmpty());
    }

    @Test
    void testValidateNoDuplicateIngredientsShouldReturnError() {
        List<String> ingredients = new ArrayList<>();
        ingredients.add("bacon");
        ingredients.add("bacon");
        SearchRecipeRequest request = new SearchRecipeRequest(ingredients);
        Optional<CoreError> error = validator.validateNoDuplicateIngredient(request);
        assertTrue(error.isPresent());
        assertEquals("Ingredient", error.get().getField());
        assertEquals("cannot enter duplicates", error.get().getMessage());
    }
}