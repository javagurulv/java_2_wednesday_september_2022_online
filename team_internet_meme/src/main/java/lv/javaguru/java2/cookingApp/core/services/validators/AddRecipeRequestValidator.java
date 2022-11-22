package lv.javaguru.java2.cookingApp.core.services.validators;


import lv.javaguru.java2.cookingApp.core.dto.requests.AddRecipeRequest;
import lv.javaguru.java2.cookingApp.core.dto.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class AddRecipeRequestValidator {

    public List<CoreError> validate(AddRecipeRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateDishName(request).ifPresent(errors::add);
        validateIngredientsNotNull(request).ifPresent(errors::add);
        if (validateIngredientsNotNull(request).isEmpty()) {
            validateIngredientsNotEmpty(request).ifPresent(errors::add);
            if (validateIngredientsNotEmpty(request).isEmpty()) {
                validateIngredientName(request).ifPresent(errors::add);
                validateIngredientAmount(request).ifPresent(errors::add);
            }
        }
        validateCookingStepsNotNull(request).ifPresent(errors::add);
        if (validateCookingStepsNotNull(request).isEmpty()) {
            validateCookingStepsNotEmpty(request).ifPresent(errors::add);
            if (validateIngredientsNotEmpty(request).isEmpty()) {
                validateCookingStepDescription(request).ifPresent(errors::add);
            }
        }
        return errors;
    }

    private Optional<CoreError> validateDishName(AddRecipeRequest request) {
        String dishName = request.getDishName();
        return (dishName == null || dishName.isBlank())
                ? Optional.of(new CoreError("Dish name", "Cannot be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateIngredientsNotEmpty(AddRecipeRequest request) {
        return request.getIngredients().isEmpty()
                ? Optional.of(new CoreError("Ingredients list", "cannot be empty"))
                : Optional.empty();
    }

    private Optional<CoreError> validateIngredientsNotNull(AddRecipeRequest request) {
        return request.getIngredients() == null
                ? Optional.of(new CoreError("Ingredients list", "cannot be null"))
                : Optional.empty();
    }

    private Optional<CoreError> validateIngredientName(AddRecipeRequest request) {
        return request.getIngredients().stream()
                .anyMatch(ingredient -> ingredient.getName() == null || ingredient.getName().isBlank())
                ? Optional.of(new CoreError("Ingredient name", "Cannot be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateCookingStepsNotEmpty(AddRecipeRequest request) {
        return request.getCookingSteps().isEmpty()
                ? Optional.of(new CoreError("Cooking steps list", "cannot be empty"))
                : Optional.empty();
    }

    private Optional<CoreError> validateCookingStepsNotNull(AddRecipeRequest request) {
        return request.getCookingSteps() == null
                ? Optional.of(new CoreError("Cooking steps list", "cannot be null"))
                : Optional.empty();
    }

    private Optional<CoreError> validateCookingStepDescription(AddRecipeRequest request) {
        return request.getCookingSteps().stream()
                .anyMatch(cookingStep -> cookingStep.getStepDescription() == null || cookingStep.getStepDescription().isBlank())
                ? Optional.of(new CoreError("Cooking Step", "Cannot be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateIngredientAmount(AddRecipeRequest request) {
        return request.getIngredients().stream()
                .anyMatch(ingredient -> ingredient.getAmount() == null || ingredient.getAmount() == 0)
                ? Optional.of(new CoreError("Ingredient amount", "Cannot be 0!"))
                : Optional.empty();
    }
}
