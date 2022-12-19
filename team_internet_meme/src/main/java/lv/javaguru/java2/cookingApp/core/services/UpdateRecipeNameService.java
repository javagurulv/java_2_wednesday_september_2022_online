package lv.javaguru.java2.cookingApp.core.services;

import lv.javaguru.java2.cookingApp.core.database.RecipeRepository;
import lv.javaguru.java2.cookingApp.core.dto.requests.UpdateRecipeNameRequest;
import lv.javaguru.java2.cookingApp.core.dto.responses.CoreError;
import lv.javaguru.java2.cookingApp.core.dto.responses.UpdateRecipeNameResponse;
import lv.javaguru.java2.cookingApp.core.services.validators.UpdateRecipeNameValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class UpdateRecipeNameService {

    @Autowired private RecipeRepository recipeRepository;
    @Autowired private UpdateRecipeNameValidator validator;

    public UpdateRecipeNameResponse execute(UpdateRecipeNameRequest request) {
        List<CoreError> errors = new ArrayList<>(validator.validate(request));
        if (!errors.isEmpty()) {
            return new UpdateRecipeNameResponse(errors);
        }
        boolean isUpdated = recipeRepository.update(request.getNewName(), request.getId());
        return new UpdateRecipeNameResponse(isUpdated);
    }
}
