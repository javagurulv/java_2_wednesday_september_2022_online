package lv.javaguru.java2.cookingApp.core.services;


import lv.javaguru.java2.cookingApp.core.database.Database;
import lv.javaguru.java2.cookingApp.core.requests.DeleteRecipeRequest;
import lv.javaguru.java2.cookingApp.core.responses.CoreError;
import lv.javaguru.java2.cookingApp.core.responses.DeleteRecipeResponse;
import lv.javaguru.java2.cookingApp.core.services.validators.DeleteRecipeRequestValidator;
import lv.javaguru.java2.cookingApp.dependency_injection.DIComponent;
import lv.javaguru.java2.cookingApp.dependency_injection.DIDependency;

import java.util.List;

@DIComponent
public class DeleteRecipeService {
    @DIDependency
    private Database database;
    @DIDependency
    private DeleteRecipeRequestValidator validator;


    public DeleteRecipeResponse execute(DeleteRecipeRequest request) {
        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new DeleteRecipeResponse(errors);
        }

        boolean isDeleted = database.deleteById(request.getId());
        return new DeleteRecipeResponse(isDeleted);
    }
}
