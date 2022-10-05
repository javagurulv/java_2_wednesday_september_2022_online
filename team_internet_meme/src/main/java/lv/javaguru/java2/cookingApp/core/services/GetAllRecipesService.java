package lv.javaguru.java2.cookingApp.core.services;


import lv.javaguru.java2.cookingApp.core.database.Database;
import lv.javaguru.java2.cookingApp.core.domain.Recipe;
import lv.javaguru.java2.cookingApp.core.requests.GetAllRecipesRequest;
import lv.javaguru.java2.cookingApp.core.responses.GetAllRecipesResponse;
import lv.javaguru.java2.cookingApp.dependency_injection.DIComponent;
import lv.javaguru.java2.cookingApp.dependency_injection.DIDependency;

import java.util.List;

@DIComponent
public class GetAllRecipesService {

    @DIDependency
    private Database database;

    public GetAllRecipesResponse execute(GetAllRecipesRequest request) {
        List<Recipe> recipes = database.getAllRecipes();
        return new GetAllRecipesResponse(recipes);
    }
}
