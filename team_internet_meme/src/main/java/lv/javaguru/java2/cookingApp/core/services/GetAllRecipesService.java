package lv.javaguru.java2.cookingApp.core.services;


import lv.javaguru.java2.cookingApp.core.database.Database;
import lv.javaguru.java2.cookingApp.core.domain.Recipe;
import lv.javaguru.java2.cookingApp.core.requests.GetAllRecipesRequest;
import lv.javaguru.java2.cookingApp.core.responses.GetAllRecipesResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetAllRecipesService {

    @Autowired
    private Database database;

    public GetAllRecipesResponse execute(GetAllRecipesRequest request) {
        List<Recipe> recipes = database.getAllRecipes();
        return new GetAllRecipesResponse(recipes);
    }
}
