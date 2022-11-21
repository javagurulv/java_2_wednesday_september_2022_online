package lv.javaguru.java2.cookingApp.core.services;


import lv.javaguru.java2.cookingApp.core.database.RecipeRepository;
import lv.javaguru.java2.cookingApp.core.dto.requests.DeleteRecipeRequest;
import lv.javaguru.java2.cookingApp.core.dto.responses.CoreError;
import lv.javaguru.java2.cookingApp.core.dto.responses.DeleteRecipeResponse;
import lv.javaguru.java2.cookingApp.core.services.validators.DeleteRecipeValidator;
import lv.javaguru.java2.cookingApp.core.services.validators.IdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class DeleteRecipeService {
    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired DeleteRecipeValidator validator;

    public DeleteRecipeResponse execute(DeleteRecipeRequest request) {
        List<CoreError> errors = validator.validate(request);

        if (!errors.isEmpty()) {
            return new DeleteRecipeResponse(errors);
        }

        boolean isDeleted = recipeRepository.deleteById(request.getId());
        return new DeleteRecipeResponse(isDeleted);
    }
}
