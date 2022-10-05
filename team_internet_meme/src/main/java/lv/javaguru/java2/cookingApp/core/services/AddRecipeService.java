package lv.javaguru.java2.cookingApp.core.services;


import lv.javaguru.java2.cookingApp.core.database.Database;
import lv.javaguru.java2.cookingApp.core.domain.Recipe;
import lv.javaguru.java2.cookingApp.core.requests.AddRecipeRequest;
import lv.javaguru.java2.cookingApp.core.responses.AddRecipeResponse;
import lv.javaguru.java2.cookingApp.core.responses.CoreError;
import lv.javaguru.java2.cookingApp.core.services.validators.AddRecipeRequestValidator;
import lv.javaguru.java2.cookingApp.dependency_injection.DIComponent;
import lv.javaguru.java2.cookingApp.dependency_injection.DIDependency;

import java.util.List;

@DIComponent
public class AddRecipeService {

    @DIDependency
    private Database database;
    @DIDependency private AddRecipeRequestValidator validator;

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
