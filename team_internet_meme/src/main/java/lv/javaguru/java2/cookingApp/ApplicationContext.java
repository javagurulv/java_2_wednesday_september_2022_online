package lv.javaguru.java2.cookingApp;

import lv.javaguru.java2.cookingApp.console_ui.*;
import lv.javaguru.java2.cookingApp.core.database.Database;
import lv.javaguru.java2.cookingApp.core.database.InMemoryDatabaseImpl;
import lv.javaguru.java2.cookingApp.core.services.*;
import lv.javaguru.java2.cookingApp.core.services.validators.AddRecipeRequestValidator;
import lv.javaguru.java2.cookingApp.core.services.validators.DeleteRecipeRequestValidator;
import lv.javaguru.java2.cookingApp.core.services.validators.PrintRecipeToConsoleValidator;
import lv.javaguru.java2.cookingApp.core.services.validators.SearchRecipeRequestValidator;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {

    private Map<Class, Object> beans = new HashMap<>();

    public ApplicationContext() {
        beans.put(Database.class, new InMemoryDatabaseImpl());
        beans.put(AddRecipeRequestValidator.class, new AddRecipeRequestValidator());
        beans.put(DeleteRecipeRequestValidator.class, new DeleteRecipeRequestValidator());
        beans.put(SearchRecipeRequestValidator.class, new SearchRecipeRequestValidator());
        beans.put(AddRecipeService.class, new AddRecipeService(getBean(Database.class), getBean(AddRecipeRequestValidator.class)));
        beans.put(DeleteRecipeService.class, new DeleteRecipeService(getBean(Database.class), getBean(DeleteRecipeRequestValidator.class)));
        beans.put(GetAllRecipesService.class, new GetAllRecipesService(getBean(Database.class)));
        beans.put(PrintRecipeToConsoleService.class, new PrintRecipeToConsoleService(getBean(Database.class), getBean(PrintRecipeToConsoleValidator.class)));
        beans.put(SearchRecipeService.class, new SearchRecipeService(getBean(Database.class), getBean(SearchRecipeRequestValidator.class)));

        beans.put(AddRecipeUIAction.class, new AddRecipeUIAction(getBean(AddRecipeService.class)));
        beans.put(DeleteRecipeUIAction.class, new DeleteRecipeUIAction(getBean(DeleteRecipeService.class)));
        beans.put(GetAllRecipesUIAction.class, new GetAllRecipesUIAction(getBean(GetAllRecipesService.class)));
        beans.put(PrintRecipeToConsoleUIAction.class, new PrintRecipeToConsoleUIAction(getBean(PrintRecipeToConsoleService.class)));
        beans.put(SearchRecipeUIAction.class, new SearchRecipeUIAction(getBean(SearchRecipeService.class)));
        beans.put(ExitUIAction.class, new ExitUIAction());
    }

    public <T extends Object> T getBean(Class c) {
        return (T) beans.get(c);
    }

}
