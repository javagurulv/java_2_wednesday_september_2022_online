package lv.javaguru.java2.cookingApp.services.validators;

import lv.javaguru.java2.cookingApp.requests.DeleteRecipeRequest;
import lv.javaguru.java2.cookingApp.responses.CoreError;

import java.util.ArrayList;
import java.util.List;

public class DeleteRecipeRequestValidator {

    public List<CoreError> validate(DeleteRecipeRequest request) {
        List<CoreError> errors = new ArrayList<>();
        if (request.getId() == null) {
            errors.add(new CoreError("Recipe ID", "Id cannot be null!"));
        }
        return errors;
    }
}
