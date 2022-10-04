package lv.javaguru.java2.cookingApp.console_ui;

import lv.javaguru.java2.cookingApp.core.requests.GetAllRecipesRequest;
import lv.javaguru.java2.cookingApp.core.services.GetAllRecipesService;
import lv.javaguru.java2.cookingApp.dependency_injection.DIComponent;
import lv.javaguru.java2.cookingApp.dependency_injection.DIDependency;

@DIComponent
public class GetAllRecipesUIAction implements UIAction {
    @DIDependency private GetAllRecipesService getAllRecipesService;


    @Override
    public void execute() {
        System.out.println("The list of all saved dishes: ");
        getAllRecipesService.execute(new GetAllRecipesRequest()).getRecipes().
                stream().
                forEach(System.out::println);
    }
}
