package lv.javaguru.java2.cookingApp.core.services.validators;


import lv.javaguru.java2.cookingApp.core.requests.AddRecipeRequest;
import lv.javaguru.java2.cookingApp.core.responses.CoreError;
import lv.javaguru.java2.cookingApp.dependency_injection.DIComponent;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DIComponent
public class AddRecipeRequestValidator {

    public List<CoreError> validate(AddRecipeRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateDishName(request).ifPresent(errors::add);
        validateIngredientName(request).ifPresent(errors::add);
        validateCookingStep(request).ifPresent(errors::add);
        validateIngredientAmount(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateDishName(AddRecipeRequest request) {
        String dishName = request.getDishName();
        return (dishName == null || dishName.isBlank())
                ? Optional.of(new CoreError("Dish name", "Cannot be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateIngredientName(AddRecipeRequest request) {
        return request.getIngredients().stream()
                .anyMatch(ingredient -> ingredient.getName() == null || ingredient.getName().isBlank())
                ? Optional.of(new CoreError("Ingredient name", "Cannot be empty!"))
                : Optional.empty();
    }

    private Optional<CoreError> validateCookingStep(AddRecipeRequest request) {
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
