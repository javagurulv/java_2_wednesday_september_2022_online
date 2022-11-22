package lv.javaguru.java2.cookingApp.core.services.validators;


import lv.javaguru.java2.cookingApp.core.dto.requests.SearchRecipeRequest;
import lv.javaguru.java2.cookingApp.core.dto.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SearchRecipeRequestValidator {

    public List<CoreError> validate(SearchRecipeRequest request) {
        List<CoreError> coreErrors = new ArrayList<>();
        validateRequestIsNotEmpty(request).ifPresent(coreErrors::add);
        validateIngredientNamesAreNotEmpty(request).ifPresent(coreErrors::add);
        validateIngredientNameLength(request).ifPresent(coreErrors::add);
        validateNoDuplicateIngredient(request).ifPresent(coreErrors::add);
        return coreErrors;
    }

    protected Optional<CoreError> validateIngredientNamesAreNotEmpty(SearchRecipeRequest request) {
        if (request.getIngredientNameList().stream().anyMatch(s -> s == null || s.isBlank())) {
            return Optional.of(new CoreError("Ingredient name", "cannot be empty"));
        } else return Optional.empty();
    }

    protected Optional<CoreError> validateIngredientNameLength(SearchRecipeRequest request) {
        if (request.getIngredientNameList().stream().anyMatch(s -> s.length() > 30)) {
            return Optional.of(new CoreError("Ingredient name", "cannot be longer that 30 characters"));
        } else return Optional.empty();
    }

    protected Optional<CoreError> validateRequestIsNotEmpty(SearchRecipeRequest request) {
        if (request == null || request.getIngredientNameList() == null || request.getIngredientNameList().isEmpty()) {
            return Optional.of(new CoreError("Ingredient to search", "must enter at least 1 ingredient"));
        } else return Optional.empty();
    }

    protected Optional<CoreError> validateNoDuplicateIngredient(SearchRecipeRequest request) {
        return (request.getIngredientNameList().size() != request.getIngredientNameList().stream().distinct().count())
                ? Optional.of(new CoreError("Ingredient", "cannot enter duplicates")) : Optional.empty();
    }
}
