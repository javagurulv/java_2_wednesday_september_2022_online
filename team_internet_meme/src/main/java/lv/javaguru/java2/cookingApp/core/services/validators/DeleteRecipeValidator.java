package lv.javaguru.java2.cookingApp.core.services.validators;

import lv.javaguru.java2.cookingApp.core.database.RecipeRepository;
import lv.javaguru.java2.cookingApp.core.dto.requests.DeleteRecipeRequest;
import lv.javaguru.java2.cookingApp.core.dto.responses.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DeleteRecipeValidator {

    @Autowired private IdValidator idValidator;
    @Autowired private RecipeRepository recipeRepository;

    public List<CoreError> validate(DeleteRecipeRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateRecipeExists(request).ifPresent(errors::add);
        errors.addAll(idValidator.validate(request.getId()));
        return errors;
    }

    private Optional<CoreError> validateRecipeExists(DeleteRecipeRequest request) {
        return recipeRepository.getById(request.getId()).isEmpty()
                ? Optional.of(new CoreError("Id", "There is no recipe with such id in the database"))
                : Optional.empty();
    }
}
