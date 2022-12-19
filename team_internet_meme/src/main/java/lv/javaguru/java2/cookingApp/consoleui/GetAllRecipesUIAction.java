package lv.javaguru.java2.cookingApp.consoleui;


import lv.javaguru.java2.cookingApp.core.dto.requests.GetAllRecipesRequest;
import lv.javaguru.java2.cookingApp.core.services.GetAllRecipesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class GetAllRecipesUIAction implements UIAction {

    @Autowired
    private GetAllRecipesService getAllRecipesService;

    @Override
    public void execute() {
        System.out.println("The list of all saved dishes: ");
        getAllRecipesService.execute(new GetAllRecipesRequest()).getRecipes().
                stream().
                forEach(System.out::println);
    }
}
