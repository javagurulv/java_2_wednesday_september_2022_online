package lv.javaguru.java2.cookingApp.core.services;

import lv.javaguru.java2.cookingApp.core.database.RecipeRepository;
import lv.javaguru.java2.cookingApp.core.dto.requests.GetRecipeRequest;
import lv.javaguru.java2.cookingApp.core.dto.responses.CoreError;
import lv.javaguru.java2.cookingApp.core.dto.responses.GetRecipeResponse;
import lv.javaguru.java2.cookingApp.core.services.validators.IdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class GetRecipeService {

    @Autowired private RecipeRepository recipeRepository;
    @Autowired private IdValidator validator;

    public GetRecipeResponse execute(GetRecipeRequest request) {
        List<CoreError> errors = new ArrayList<>(validator.validate(request.getId()));
        if (!errors.isEmpty()) {
            return new GetRecipeResponse(errors);
        }
        return recipeRepository.getById(request.getId())
                .map(GetRecipeResponse::new)
                .orElseGet(() -> {
                    errors.add(new CoreError("Id", "Not found"));
                    return new GetRecipeResponse(errors);
                });
    }
}
