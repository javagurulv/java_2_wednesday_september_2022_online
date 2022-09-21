package lv.javaguru.java2.cookingApp.console_ui;

import lv.javaguru.java2.cookingApp.requests.GetAllRecipesRequest;
import lv.javaguru.java2.cookingApp.services.GetAllRecipesService;

public class GetAllRecipesUIAction implements UIAction {
    private GetAllRecipesService getAllRecipesService;

    public GetAllRecipesUIAction(GetAllRecipesService getAllRecipesService) {
        this.getAllRecipesService = getAllRecipesService;
    }

    @Override
    public void execute() {
        System.out.println("The list of all saved dishes: ");
        getAllRecipesService.execute(new GetAllRecipesRequest()).getRecipes().
                stream().
                forEach(System.out::println);
    }
}
