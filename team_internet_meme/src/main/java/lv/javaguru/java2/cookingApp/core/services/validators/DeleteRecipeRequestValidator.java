package lv.javaguru.java2.cookingApp.core.services.validators;


import lv.javaguru.java2.cookingApp.core.requests.DeleteRecipeRequest;
import lv.javaguru.java2.cookingApp.core.responses.CoreError;
import lv.javaguru.java2.cookingApp.dependency_injection.DIComponent;

import java.util.ArrayList;
import java.util.List;

@DIComponent
public class DeleteRecipeRequestValidator {

    public List<CoreError> validate(DeleteRecipeRequest request) {
        List<CoreError> errors = new ArrayList<>();
        if (request.getId() == null) {
            errors.add(new CoreError("Recipe ID", "Id cannot be null!"));
        }
        return errors;
    }
}