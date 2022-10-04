package lv.javaguru.java2.cookingApp.core.services;

import lv.javaguru.java2.cookingApp.core.domain.Recipe;
import lv.javaguru.java2.cookingApp.core.database.Database;
import lv.javaguru.java2.cookingApp.core.requests.GetAllRecipesRequest;
import lv.javaguru.java2.cookingApp.core.responses.GetAllRecipesResponse;

import java.util.List;

public class GetAllRecipesService {
    private Database database;

    public GetAllRecipesService(Database database) {
        this.database = database;
    }

    public GetAllRecipesResponse execute(GetAllRecipesRequest request) {
        List<Recipe> recipes = database.getAllRecipes();
        return new GetAllRecipesResponse(recipes);
    }
}
