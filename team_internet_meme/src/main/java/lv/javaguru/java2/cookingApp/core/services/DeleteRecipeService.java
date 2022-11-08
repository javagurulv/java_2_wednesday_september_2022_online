package lv.javaguru.java2.cookingApp.core.services;


import lv.javaguru.java2.cookingApp.core.database.RecipeRepository;
import lv.javaguru.java2.cookingApp.core.requests.DeleteRecipeRequest;
import lv.javaguru.java2.cookingApp.core.responses.CoreError;
import lv.javaguru.java2.cookingApp.core.responses.DeleteRecipeResponse;
import lv.javaguru.java2.cookingApp.core.services.validators.IdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeleteRecipeService {
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private IdValidator validator;


    public DeleteRecipeResponse execute(DeleteRecipeRequest request) {
        List<CoreError> errors = validator.validate(request.getId());

        if (!errors.isEmpty()) {
            return new DeleteRecipeResponse(errors);
        }

        boolean isDeleted = recipeRepository.deleteById(request.getId());
        return new DeleteRecipeResponse(isDeleted);
    }
}
