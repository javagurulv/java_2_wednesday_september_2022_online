package lv.javaguru.java2.cookingApp.core.services;


import lv.javaguru.java2.cookingApp.core.database.CookingStepRepository;
import lv.javaguru.java2.cookingApp.core.database.IngredientRepository;
import lv.javaguru.java2.cookingApp.core.database.RecipeRepository;
import lv.javaguru.java2.cookingApp.core.domain.CookingStep;
import lv.javaguru.java2.cookingApp.core.domain.Ingredient;
import lv.javaguru.java2.cookingApp.core.domain.Recipe;
import lv.javaguru.java2.cookingApp.core.dto.requests.PrintRecipeToConsoleRequest;
import lv.javaguru.java2.cookingApp.core.dto.responses.CoreError;
import lv.javaguru.java2.cookingApp.core.dto.responses.PrintRecipeToConsoleResponse;
import lv.javaguru.java2.cookingApp.core.services.validators.IdValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
@Transactional
public class PrintRecipeToConsoleService {

    @Autowired private RecipeRepository recipeRepository;
    @Autowired private IngredientRepository ingredientRepository;
    @Autowired private CookingStepRepository cookingStepRepository;
    @Autowired private IdValidator validator;


    public PrintRecipeToConsoleResponse execute(PrintRecipeToConsoleRequest request) {
        List<CoreError> errors = validator.validate(request.getId());
        if (!errors.isEmpty()) {
            return new PrintRecipeToConsoleResponse(errors);
        }
        Optional<Recipe> recipeToPrintOpt = recipeRepository.getById(request.getId());

        if (recipeToPrintOpt.isPresent()) {
            List<Ingredient> ingredients = ingredientRepository.getIngredientsByRecipeId(request.getId());
            List<CookingStep> cookingSteps = cookingStepRepository.getCookingStepsByRecipeId(request.getId());
            return new PrintRecipeToConsoleResponse(recipeToPrintOpt, ingredients, cookingSteps);
        } else
            return new PrintRecipeToConsoleResponse(recipeToPrintOpt);
    }

}
