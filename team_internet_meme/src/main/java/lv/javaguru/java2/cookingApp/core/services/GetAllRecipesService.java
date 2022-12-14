package lv.javaguru.java2.cookingApp.core.services;


import lv.javaguru.java2.cookingApp.core.database.RecipeRepository;
import lv.javaguru.java2.cookingApp.core.domain.Recipe;
import lv.javaguru.java2.cookingApp.core.dto.requests.GetAllRecipesRequest;
import lv.javaguru.java2.cookingApp.core.dto.responses.GetAllRecipesResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class GetAllRecipesService {

    @Autowired
    private RecipeRepository recipeRepository;

    public GetAllRecipesResponse execute(GetAllRecipesRequest request) {
        List<Recipe> recipes = recipeRepository.getAllRecipes();
        return new GetAllRecipesResponse(recipes);
    }
}
