package lv.javaguru.java2.cookingApp.services;

import lv.javaguru.java2.cookingApp.Recipe;
import lv.javaguru.java2.cookingApp.database.Database;
import lv.javaguru.java2.cookingApp.requests.PrintRecipeToConsoleRequest;
import lv.javaguru.java2.cookingApp.responses.PrintRecipeToConsoleResponse;


public class PrintRecipeToConsoleService {
    private Database database;

    public PrintRecipeToConsoleService(Database database) {
        this.database = database;
    }

    public PrintRecipeToConsoleResponse execute(PrintRecipeToConsoleRequest request) {
        Recipe recipeToPrint = database.getById(request.getId());
        recipeToPrint.printToConsole();
        return new PrintRecipeToConsoleResponse(recipeToPrint);

    }
}
