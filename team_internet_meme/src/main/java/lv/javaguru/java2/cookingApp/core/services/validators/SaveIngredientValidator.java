package lv.javaguru.java2.cookingApp.core.services.validators;

import lv.javaguru.java2.cookingApp.core.dto.requests.SaveIngredientRequest;
import lv.javaguru.java2.cookingApp.core.dto.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SaveIngredientValidator {

    public List<CoreError> validate(SaveIngredientRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateStringNotNull(request).ifPresent(errors::add);
        validateStringNotBlank(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateStringNotBlank(SaveIngredientRequest request) {
        return request.getIngredientName().isBlank() ? Optional.of(new CoreError("Ingredient name", "cannot be empty"))
                : Optional.empty();
    }

    private Optional<CoreError> validateStringNotNull(SaveIngredientRequest request) {
        return request.getIngredientName() == null ? Optional.of(new CoreError("Ingredient", "cannot be null"))
                : Optional.empty();
    }
}
