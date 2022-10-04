package lv.javaguru.java2.cookingApp.core.services;

import lv.javaguru.java2.cookingApp.core.domain.Recipe;
import lv.javaguru.java2.cookingApp.core.database.Database;
import lv.javaguru.java2.cookingApp.core.requests.PrintRecipeToConsoleRequest;
import lv.javaguru.java2.cookingApp.core.responses.CoreError;
import lv.javaguru.java2.cookingApp.core.responses.PrintRecipeToConsoleResponse;
import lv.javaguru.java2.cookingApp.core.services.validators.PrintRecipeToConsoleValidator;
import lv.javaguru.java2.cookingApp.dependency_injection.DIComponent;
import lv.javaguru.java2.cookingApp.dependency_injection.DIDependency;

import java.util.List;

@DIComponent
public class PrintRecipeToConsoleService {

    @DIDependency private Database database;
    @DIDependency private PrintRecipeToConsoleValidator validator;


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
