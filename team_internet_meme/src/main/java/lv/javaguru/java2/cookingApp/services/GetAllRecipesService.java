package lv.javaguru.java2.cookingApp.services;

import lv.javaguru.java2.cookingApp.Recipe;
import lv.javaguru.java2.cookingApp.database.Database;
import lv.javaguru.java2.cookingApp.requests.GetAllRecipesRequest;
import lv.javaguru.java2.cookingApp.responses.GetAllRecipesResponse;

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
