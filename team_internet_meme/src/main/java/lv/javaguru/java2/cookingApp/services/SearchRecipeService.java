package lv.javaguru.java2.cookingApp.services;

import lv.javaguru.java2.cookingApp.database.Database;
import lv.javaguru.java2.cookingApp.domain.Recipe;
import lv.javaguru.java2.cookingApp.requests.SearchRecipeRequest;
import lv.javaguru.java2.cookingApp.responses.CoreError;
import lv.javaguru.java2.cookingApp.responses.SearchRecipeResponse;
import lv.javaguru.java2.cookingApp.services.search_criteria.AndSearchCriteria;
import lv.javaguru.java2.cookingApp.services.search_criteria.IngredientNameCriteria;
import lv.javaguru.java2.cookingApp.services.search_criteria.SearchCriteria;
import lv.javaguru.java2.cookingApp.services.validators.SearchRecipeValidator;

import java.util.ArrayList;
import java.util.List;

public class SearchRecipeService {

    private Database database;

    public SearchRecipeService(Database database) {
        this.database = database;
    }

    public SearchRecipeResponse execute(SearchRecipeRequest request) {

        List<CoreError> coreErrors = new SearchRecipeValidator().validate(request);
        if (!coreErrors.isEmpty()) {
            return new SearchRecipeResponse(coreErrors, null);
        }

        SearchCriteria searchCriteria = createSearchCriteria(request);
        List<Recipe> recipes = database.find(searchCriteria);
        return new SearchRecipeResponse(null, recipes);
    }


    private SearchCriteria createSearchCriteria(SearchRecipeRequest request) {
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
