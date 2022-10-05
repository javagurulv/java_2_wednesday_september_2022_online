package lv.javaguru.java2.cookingApp.core.services;


import lv.javaguru.java2.cookingApp.core.database.Database;
import lv.javaguru.java2.cookingApp.core.domain.Recipe;
import lv.javaguru.java2.cookingApp.core.requests.SearchRecipeRequest;
import lv.javaguru.java2.cookingApp.core.responses.CoreError;
import lv.javaguru.java2.cookingApp.core.responses.SearchRecipeResponse;
import lv.javaguru.java2.cookingApp.core.services.search_criteria.AndSearchCriteria;
import lv.javaguru.java2.cookingApp.core.services.search_criteria.IngredientNameCriteria;
import lv.javaguru.java2.cookingApp.core.services.search_criteria.SearchCriteria;
import lv.javaguru.java2.cookingApp.core.services.validators.SearchRecipeRequestValidator;
import lv.javaguru.java2.cookingApp.dependency_injection.DIComponent;
import lv.javaguru.java2.cookingApp.dependency_injection.DIDependency;

import java.util.ArrayList;
import java.util.List;

@DIComponent
public class SearchRecipeService {

    @DIDependency
    private Database database;
    @DIDependency private SearchRecipeRequestValidator validator;

    public SearchRecipeResponse execute(SearchRecipeRequest request) {

        List<CoreError> coreErrors = validator.validate(request);
        if (!coreErrors.isEmpty()) {
            return new SearchRecipeResponse(coreErrors, null);
        }

        SearchCriteria searchCriteria = createSearchCriteria(request);
        List<Recipe> recipes = database.find(searchCriteria);
        return new SearchRecipeResponse(null, recipes);
    }


    protected SearchCriteria createSearchCriteria(SearchRecipeRequest request) {
        List<String> ingredients = request.getIngredientNameList();
        int numberOfIngredients = ingredients.size();
        List<SearchCriteria> searchCriteria = new ArrayList<>();
        for (String ingredient : ingredients) {
            searchCriteria.add(new IngredientNameCriteria(ingredient));
        }
        if (numberOfIngredients == 1) {
            return searchCriteria.get(0);
        } else {
            AndSearchCriteria andSearchCriteria = new AndSearchCriteria(searchCriteria.get(0), searchCriteria.get(1));
            int i = 2;
            while (i < numberOfIngredients) {
                andSearchCriteria = new AndSearchCriteria(andSearchCriteria, searchCriteria.get(i));
                i++;
            }
            return andSearchCriteria;
        }
    }
}
