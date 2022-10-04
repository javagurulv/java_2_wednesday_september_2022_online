package lv.javaguru.java2.cookingApp.core.services;

import lv.javaguru.java2.cookingApp.core.domain.Recipe;
import lv.javaguru.java2.cookingApp.core.database.Database;
import lv.javaguru.java2.cookingApp.core.requests.AddRecipeRequest;
import lv.javaguru.java2.cookingApp.core.responses.AddRecipeResponse;
import lv.javaguru.java2.cookingApp.core.responses.CoreError;
import lv.javaguru.java2.cookingApp.core.services.validators.AddRecipeRequestValidator;

import java.util.List;


public class AddRecipeService {
    private Database database;
    private AddRecipeRequestValidator validator;

    public AddRecipeService(Database database, AddRecipeRequestValidator validator) {
        this.database = database;
        this.validator = validator;
    }

    public AddRecipeResponse execute(AddRecipeRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new AddRecipeResponse(errors);
        }
        Recipe recipe = new Recipe(request.getDishName(), request.getIngredients(), request.getCookingSteps());
        database.save(recipe);
        return new AddRecipeResponse(recipe);
    }
}
