package lv.javaguru.java2.cookingApp.core.services;


import lv.javaguru.java2.cookingApp.core.database.Database;
import lv.javaguru.java2.cookingApp.core.domain.Recipe;
import lv.javaguru.java2.cookingApp.core.requests.PrintRecipeToConsoleRequest;
import lv.javaguru.java2.cookingApp.core.responses.CoreError;
import lv.javaguru.java2.cookingApp.core.responses.PrintRecipeToConsoleResponse;
import lv.javaguru.java2.cookingApp.core.services.validators.PrintRecipeToConsoleValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PrintRecipeToConsoleService {

    @Autowired
    private Database database;
    @Autowired private PrintRecipeToConsoleValidator validator;


    public PrintRecipeToConsoleResponse execute(PrintRecipeToConsoleRequest request) {
        List<CoreError> errors = validator.validate(request);
        if (!errors.isEmpty()) {
            return new PrintRecipeToConsoleResponse(errors);
        }
        Optional<Recipe> recipeToPrintOpt = database.getById(request.getId());
        recipeToPrintOpt.ifPresent(Recipe::printToConsole);

        return new PrintRecipeToConsoleResponse(recipeToPrintOpt);

    }
}
