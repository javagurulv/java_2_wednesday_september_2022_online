package lv.javaguru.java2.cookingApp.core.services;


import lv.javaguru.java2.cookingApp.core.database.CookingStepRepository;
import lv.javaguru.java2.cookingApp.core.database.IngredientRepository;
import lv.javaguru.java2.cookingApp.core.database.RecipeRepository;
import lv.javaguru.java2.cookingApp.core.domain.Recipe;
import lv.javaguru.java2.cookingApp.core.dto.requests.AddRecipeRequest;
import lv.javaguru.java2.cookingApp.core.dto.responses.AddRecipeResponse;
import lv.javaguru.java2.cookingApp.core.dto.responses.CoreError;
import lv.javaguru.java2.cookingApp.core.services.validators.AddRecipeRequestValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class AddRecipeService {

    @Autowired private RecipeRepository recipeRepository;
    @Autowired private IngredientRepository ingredientRepository;
    @Autowired private CookingStepRepository cookingStepRepository;
    @Autowired private AddRecipeRequestValidator validator;

    public AddRecipeResponse execute(AddRecipeRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddRecipeResponse(errors);
        }
        Recipe recipe = new Recipe(request.getDishName());
        Long recipeId = recipeRepository.save(recipe);
        ingredientRepository.saveRecipeIngredients(request.getIngredients(), recipeId);
        cookingStepRepository.saveCookingSteps(request.getCookingSteps(), recipeId);
        return new AddRecipeResponse(recipe);
    }
}
