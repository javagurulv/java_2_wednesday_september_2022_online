package lv.javaguru.java2.cookingApp.core.services;


import lv.javaguru.java2.cookingApp.core.database.RecipeRepository;
import lv.javaguru.java2.cookingApp.core.domain.Recipe;
import lv.javaguru.java2.cookingApp.core.requests.AddRecipeRequest;
import lv.javaguru.java2.cookingApp.core.responses.AddRecipeResponse;
import lv.javaguru.java2.cookingApp.core.responses.CoreError;
import lv.javaguru.java2.cookingApp.core.services.validators.AddRecipeRequestValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddRecipeService {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired private AddRecipeRequestValidator validator;

    public AddRecipeResponse execute(AddRecipeRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddRecipeResponse(errors);
        }
        Recipe recipe = new Recipe(request.getDishName());
        recipeRepository.save(recipe);
        return new AddRecipeResponse(recipe);
    }
}
