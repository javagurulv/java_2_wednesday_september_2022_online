package lv.javaguru.java2.cookingApp.services;

import lv.javaguru.java2.cookingApp.database.Database;
import lv.javaguru.java2.cookingApp.requests.DeleteRecipeRequest;
import lv.javaguru.java2.cookingApp.responses.CoreError;
import lv.javaguru.java2.cookingApp.responses.DeleteRecipeResponse;
import lv.javaguru.java2.cookingApp.services.validators.DeleteRecipeValidator;

import java.util.List;

public class DeleteRecipeService {
    private Database database;
    private DeleteRecipeValidator validator = new DeleteRecipeValidator();

    public DeleteRecipeService(Database database) {
        this.database = database;
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
