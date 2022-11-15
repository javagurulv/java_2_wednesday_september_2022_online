package lv.javaguru.java2.cookingApp.core.services;


import lv.javaguru.java2.cookingApp.core.database.RecipeRepository;
import lv.javaguru.java2.cookingApp.core.domain.Recipe;
import lv.javaguru.java2.cookingApp.core.requests.SearchRecipeRequest;
import lv.javaguru.java2.cookingApp.core.responses.CoreError;
import lv.javaguru.java2.cookingApp.core.responses.SearchRecipeResponse;
import lv.javaguru.java2.cookingApp.core.services.validators.SearchRecipeRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
@Transactional
public class SearchRecipeService {

    @Autowired private RecipeRepository recipeRepository;
    @Autowired private SearchRecipeRequestValidator validator;

    public SearchRecipeResponse execute(SearchRecipeRequest request) {

        List<CoreError> coreErrors = validator.validate(request);
        if (!coreErrors.isEmpty()) {
            return new SearchRecipeResponse(coreErrors, null);
        }

        List<Recipe> recipes = recipeRepository.searchByIngredients(request.getIngredientNameList());
        return new SearchRecipeResponse(null, recipes);
    }

}
