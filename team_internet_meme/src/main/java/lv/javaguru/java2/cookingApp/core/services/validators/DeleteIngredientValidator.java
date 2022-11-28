package lv.javaguru.java2.cookingApp.core.services.validators;

import lv.javaguru.java2.cookingApp.core.database.IngredientRepository;
import lv.javaguru.java2.cookingApp.core.dto.requests.DeleteIngredientRequest;
import lv.javaguru.java2.cookingApp.core.dto.responses.CoreError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DeleteIngredientValidator {

    @Autowired private IdValidator idValidator;
    @Autowired private IngredientRepository ingredientRepository;

    public List<CoreError> validate(DeleteIngredientRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateIdExists(request).ifPresent(errors::add);
        errors.addAll(idValidator.validate(request.getId()));
        return errors;
    }

    private Optional<CoreError> validateIdExists(DeleteIngredientRequest request) {
        return ingredientRepository.getById(request.getId()).isEmpty()
                ? Optional.of(new CoreError("Id", "not found")) : Optional.empty();
    }

}
