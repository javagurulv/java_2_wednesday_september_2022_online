package lv.javaguru.java2.cookingApp.services;

import lv.javaguru.java2.cookingApp.domain.Recipe;
import lv.javaguru.java2.cookingApp.database.Database;
import lv.javaguru.java2.cookingApp.requests.PrintRecipeToConsoleRequest;
import lv.javaguru.java2.cookingApp.responses.CoreError;
import lv.javaguru.java2.cookingApp.responses.PrintRecipeToConsoleResponse;
import lv.javaguru.java2.cookingApp.services.validators.PrintRecipeToConsoleValidator;

import java.util.List;


public class PrintRecipeToConsoleService {
    private Database database;
    private PrintRecipeToConsoleValidator validator = new PrintRecipeToConsoleValidator();

    public PrintRecipeToConsoleService(Database database) {
        this.database = database;
    }

    public PrintRecipeToConsoleResponse execute(PrintRecipeToConsoleRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new PrintRecipeToConsoleResponse(errors);
        }
        Recipe recipeToPrint = database.getById(request.getId());
        recipeToPrint.printToConsole();
        return new PrintRecipeToConsoleResponse(recipeToPrint);

    }
}
