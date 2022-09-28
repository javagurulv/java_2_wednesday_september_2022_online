package lv.javaguru.java2.cookingApp.console_ui;

import lv.javaguru.java2.cookingApp.services.SearchRecipeService;

public class SearchRecipeUIAction implements UIAction {

    private SearchRecipeService searchRecipeService;

    public SearchRecipeUIAction(SearchRecipeService searchRecipeService) {
        this.searchRecipeService = searchRecipeService;
    }

    @Override
    public void execute() {

    }
}
