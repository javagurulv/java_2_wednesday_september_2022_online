package lv.javaguru.java2.cookingApp.core.services.validators;

import lv.javaguru.java2.cookingApp.core.database.RecipeRepository;
import lv.javaguru.java2.cookingApp.core.dto.requests.DeleteRecipeRequest;
import lv.javaguru.java2.cookingApp.core.dto.requests.UpdateRecipeNameRequest;
import lv.javaguru.java2.cookingApp.core.dto.responses.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class UpdateRecipeNameValidator {

    @Autowired
    private IdValidator idValidator;
    @Autowired private RecipeRepository recipeRepository;

    public List<CoreError> validate(UpdateRecipeNameRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateNameNotNull(request).ifPresent(errors::add);
        validateNameNotEmpty(request).ifPresent(errors::add);
        validateRecipeExists(request).ifPresent(errors::add);
        errors.addAll(idValidator.validate(request.getId()));
        return errors;
    }

    private Optional<CoreError> validateNameNotNull(UpdateRecipeNameRequest request) {
        return request.getNewName() == null ? Optional.of(new CoreError("Recipe name", "cannot be null"))
                : Optional.empty();
    }

    private Optional<CoreError> validateNameNotEmpty(UpdateRecipeNameRequest request) {
        return request.getNewName().isBlank() ? Optional.of(new CoreError("Recipe name", "cannot be empty"))
                : Optional.empty();
    }

    private Optional<CoreError> validateRecipeExists(UpdateRecipeNameRequest request) {
        return recipeRepository.getById(request.getId()).isEmpty()
                ? Optional.of(new CoreError("Id", "not found"))
                : Optional.empty();
    }
}

