package lv.javaguru.java2.cookingApp.core.services;


import lv.javaguru.java2.cookingApp.core.database.Database;
import lv.javaguru.java2.cookingApp.core.requests.DeleteRecipeRequest;
import lv.javaguru.java2.cookingApp.core.responses.CoreError;
import lv.javaguru.java2.cookingApp.core.responses.DeleteRecipeResponse;
import lv.javaguru.java2.cookingApp.core.services.validators.DeleteRecipeRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeleteRecipeService {
    @Autowired
    private Database database;
    @Autowired
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
