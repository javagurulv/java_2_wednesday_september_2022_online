package lv.javaguru.java2.cookingApp.core.services;


import lv.javaguru.java2.cookingApp.core.database.Database;
import lv.javaguru.java2.cookingApp.core.domain.Recipe;
import lv.javaguru.java2.cookingApp.core.requests.SearchRecipeRequest;
import lv.javaguru.java2.cookingApp.core.responses.CoreError;
import lv.javaguru.java2.cookingApp.core.responses.SearchRecipeResponse;
import lv.javaguru.java2.cookingApp.core.services.validators.SearchRecipeRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SearchRecipeService {

    @Autowired private Database database;
    @Autowired private SearchRecipeRequestValidator validator;

    public SearchRecipeResponse execute(SearchRecipeRequest request) {

        List<CoreError> coreErrors = validator.validate(request);
        if (!coreErrors.isEmpty()) {
            return new SearchRecipeResponse(coreErrors, null);
        }

        List<Recipe> recipes = database.searchByIngredients(request.getIngredientNameList());
        return new SearchRecipeResponse(null, recipes);
    }

}
