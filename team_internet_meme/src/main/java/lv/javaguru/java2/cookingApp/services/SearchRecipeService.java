package lv.javaguru.java2.cookingApp.services;

import lv.javaguru.java2.cookingApp.database.Database;
import lv.javaguru.java2.cookingApp.requests.SearchRecipeRequest;
import lv.javaguru.java2.cookingApp.responses.SearchRecipeResponse;

public class SearchRecipeService {

    private Database database;

    public SearchRecipeService(Database database) {
        this.database = database;
    }

    public SearchRecipeResponse execute(SearchRecipeRequest request) {
        return null;
    }
}
