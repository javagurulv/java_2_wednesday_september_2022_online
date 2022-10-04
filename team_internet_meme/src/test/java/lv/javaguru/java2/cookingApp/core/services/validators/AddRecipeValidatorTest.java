package lv.javaguru.java2.cookingApp.core.services.validators;

import lv.javaguru.java2.cookingApp.core.domain.CookingStep;
import lv.javaguru.java2.cookingApp.core.domain.Ingredient;
import lv.javaguru.java2.cookingApp.core.requests.AddRecipeRequest;
import lv.javaguru.java2.cookingApp.core.responses.CoreError;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AddRecipeValidatorTest {

    AddRecipeRequestValidator validator;
    @BeforeEach
    void setUp() {
        validator = new AddRecipeRequestValidator();
    }

    @Test
    void testShouldReturnDishNameError() {
        List<Ingredient> ingredients = new ArrayList<>();
        List<CookingStep> cookingSteps = new ArrayList<>();
        ingredients.add(new Ingredient("Name", "Measurement", 1.0));
        cookingSteps.add(new CookingStep(1, "Description"));
        AddRecipeRequest request = new AddRecipeRequest("", ingredients, cookingSteps);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Dish name");
        assertEquals(errors.get(0).getMessage(), "Cannot be empty!");
    }

    @Test
    void testShouldReturnNoErrors() {
        List<Ingredient> ingredients = new ArrayList<>();
        List<CookingStep> cookingSteps = new ArrayList<>();
        ingredients.add(new Ingredient("Name", "Measurement", 1.0));
        cookingSteps.add(new CookingStep(1, "Description"));
        AddRecipeRequest request = new AddRecipeRequest("Dish name", ingredients, cookingSteps);
        List<CoreError> errors = validator.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    void testShouldReturnIngredientNameError() {
        List<Ingredient> ingredients = new ArrayList<>();
        List<CookingStep> cookingSteps = new ArrayList<>();
        ingredients.add(new Ingredient("", "Measurement", 1.00));
        cookingSteps.add(new CookingStep(1, "Description"));
        AddRecipeRequest request = new AddRecipeRequest("Dish name", ingredients, cookingSteps);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Ingredient name");
        assertEquals(errors.get(0).getMessage(), "Cannot be empty!");
    }

    @Test
    void testShouldReturnIngredientAmountError() {
        List<Ingredient> ingredients = new ArrayList<>();
        List<CookingStep> cookingSteps = new ArrayList<>();
        ingredients.add(new Ingredient("Name", "Measurement", 0.0));
        cookingSteps.add(new CookingStep(1, "Description"));
        AddRecipeRequest request = new AddRecipeRequest("Dish name", ingredients, cookingSteps);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Ingredient amount");
        assertEquals(errors.get(0).getMessage(), "Cannot be 0!");
    }

    @Test
    void testShouldReturnCookingStepError() {
        List<Ingredient> ingredients = new ArrayList<>();
        List<CookingStep> cookingSteps = new ArrayList<>();
        ingredients.add(new Ingredient("Name", "Measurement", 1.0));
        cookingSteps.add(new CookingStep(1, ""));
        AddRecipeRequest request = new AddRecipeRequest("Dish name", ingredients, cookingSteps);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 1);
        assertEquals(errors.get(0).getField(), "Cooking Step");
        assertEquals(errors.get(0).getMessage(), "Cannot be empty!");
    }

    @Test
    void testShouldReturnDishNameAndIngredientNameErrors() {
        List<Ingredient> ingredients = new ArrayList<>();
        List<CookingStep> cookingSteps = new ArrayList<>();
        ingredients.add(new Ingredient("", "Measurement", 1.0));
        cookingSteps.add(new CookingStep(1, "Description"));
        AddRecipeRequest request = new AddRecipeRequest("", ingredients, cookingSteps);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 2);
        assertEquals(errors.get(0).getField(), "Dish name");
        assertEquals(errors.get(0).getMessage(), "Cannot be empty!");
        assertEquals(errors.get(1).getField(), "Ingredient name");
        assertEquals(errors.get(1).getMessage(), "Cannot be empty!");
    }

    @Test
    void testShouldReturnDishNameAndIngredientAmountErrors() {
        List<Ingredient> ingredients = new ArrayList<>();
        List<CookingStep> cookingSteps = new ArrayList<>();
        ingredients.add(new Ingredient("Name", "Measurement", 0.0));
        cookingSteps.add(new CookingStep(1, "Description"));
        AddRecipeRequest request = new AddRecipeRequest("", ingredients, cookingSteps);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 2);
        assertEquals(errors.get(0).getField(), "Dish name");
        assertEquals(errors.get(0).getMessage(), "Cannot be empty!");
        assertEquals(errors.get(1).getField(), "Ingredient amount");
        assertEquals(errors.get(1).getMessage(), "Cannot be 0!");
    }

    @Test
    void testShouldReturnDishNameAndCookingStepErrors() {
        List<Ingredient> ingredients = new ArrayList<>();
        List<CookingStep> cookingSteps = new ArrayList<>();
        ingredients.add(new Ingredient("Name", "Measurement", 1.0));
        cookingSteps.add(new CookingStep(1, ""));
        AddRecipeRequest request = new AddRecipeRequest("", ingredients, cookingSteps);
        List<CoreError> errors = validator.validate(request);
        assertEquals(errors.size(), 2);
        assertEquals(errors.get(0).getField(), "Dish name");
        assertEquals(errors.get(0).getMessage(), "Cannot be empty!");
        assertEquals(errors.get(1).getField(), "Cooking Step");
        assertEquals(errors.get(1).getMessage(), "Cannot be empty!");
    }
}