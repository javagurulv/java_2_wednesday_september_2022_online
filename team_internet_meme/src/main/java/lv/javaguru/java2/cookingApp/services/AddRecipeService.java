package lv.javaguru.java2.cookingApp.services;

import lv.javaguru.java2.cookingApp.Recipe;
import lv.javaguru.java2.cookingApp.database.Database;
import lv.javaguru.java2.cookingApp.requests.AddRecipeRequest;
import lv.javaguru.java2.cookingApp.responses.AddRecipeResponse;


public class AddRecipeService {
    private Database database;

    public AddRecipeService(Database database) {
        this.database = database;
    }

    public AddRecipeResponse execute(AddRecipeRequest request) {
        Recipe recipe = new Recipe(request.getDishName(), request.getIngredients(), request.getCookingSteps());
        database.save(recipe);
        return new AddRecipeResponse(recipe);
    }
}
