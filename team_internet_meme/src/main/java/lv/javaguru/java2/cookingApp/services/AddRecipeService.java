package lv.javaguru.java2.cookingApp.services;

import lv.javaguru.java2.cookingApp.domain.Recipe;
import lv.javaguru.java2.cookingApp.database.Database;
import lv.javaguru.java2.cookingApp.requests.AddRecipeRequest;
import lv.javaguru.java2.cookingApp.responses.AddRecipeResponse;
import lv.javaguru.java2.cookingApp.responses.CoreError;
import lv.javaguru.java2.cookingApp.services.validators.AddRecipeRequestValidator;

import java.util.List;


public class AddRecipeService {
    private Database database;
    private AddRecipeRequestValidator validator = new AddRecipeRequestValidator();

    public AddRecipeService(Database database) {
        this.database = database;
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
