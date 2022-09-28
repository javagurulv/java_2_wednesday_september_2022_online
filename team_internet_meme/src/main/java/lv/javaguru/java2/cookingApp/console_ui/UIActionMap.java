package lv.javaguru.java2.cookingApp.console_ui;

import lv.javaguru.java2.cookingApp.database.Database;
import lv.javaguru.java2.cookingApp.database.InMemoryDatabaseImpl;
import lv.javaguru.java2.cookingApp.services.*;

import java.util.HashMap;
import java.util.Map;

public class UIActionMap {
    private Map<Integer, UIAction> actionMap;
    private Database database = new InMemoryDatabaseImpl();
    private AddRecipeService addRecipeService = new AddRecipeService(database);
    private DeleteRecipeService deleteRecipeService= new DeleteRecipeService(database);
    private GetAllRecipesService getAllRecipesService = new GetAllRecipesService(database);
    private PrintRecipeToConsoleService printRecipeToConsoleService = new PrintRecipeToConsoleService(database);
    private SearchRecipeService searchRecipeService = new SearchRecipeService(database);


    public UIActionMap() {
        actionMap = new HashMap<>();
        actionMap.put(1, new AddRecipeUIAction(addRecipeService));
        actionMap.put(2, new DeleteRecipeUIAction(deleteRecipeService));
        actionMap.put(3, new GetAllRecipesUIAction(getAllRecipesService));
        actionMap.put(4, new PrintRecipeToConsoleUIAction(printRecipeToConsoleService));
        actionMap.put(5, new SearchRecipeUIAction(searchRecipeService));
        actionMap.put(6, new ExitUIAction());
    }

    public UIAction getAction(int userChoice) {
        return actionMap.get(userChoice);
    }
}
