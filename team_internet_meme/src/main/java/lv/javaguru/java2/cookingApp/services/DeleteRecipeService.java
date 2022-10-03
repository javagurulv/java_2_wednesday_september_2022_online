package lv.javaguru.java2.cookingApp.services;

import lv.javaguru.java2.cookingApp.database.Database;
import lv.javaguru.java2.cookingApp.requests.DeleteRecipeRequest;
import lv.javaguru.java2.cookingApp.responses.CoreError;
import lv.javaguru.java2.cookingApp.responses.DeleteRecipeResponse;
import lv.javaguru.java2.cookingApp.services.validators.DeleteRecipeRequestValidator;

import java.util.List;

public class DeleteRecipeService {
    private Database database;
    private DeleteRecipeRequestValidator validator;

    public DeleteRecipeService(Database database, DeleteRecipeRequestValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public DeleteRecipeResponse execute(DeleteRecipeRequest request) {
        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new DeleteRecipeResponse(errors);
        }

        boolean isDeleted = database.deleteById(request.getId());
        return new DeleteRecipeResponse(isDeleted);
    }
}
