package lv.javaguru.java2.cookingApp.services;

import lv.javaguru.java2.cookingApp.database.Database;
import lv.javaguru.java2.cookingApp.requests.DeleteRecipeRequest;
import lv.javaguru.java2.cookingApp.responses.DeleteRecipeResponse;

public class DeleteRecipeService {
    private Database database;

    public DeleteRecipeService(Database database) {
        this.database = database;
    }

    public DeleteRecipeResponse execute(DeleteRecipeRequest request) {
        boolean isDeleted = database.deleteById(request.getId());
        return new DeleteRecipeResponse(isDeleted);
    }
}
