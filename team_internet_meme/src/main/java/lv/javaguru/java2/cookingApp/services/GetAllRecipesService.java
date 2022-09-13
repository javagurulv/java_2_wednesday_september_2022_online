package lv.javaguru.java2.cookingApp.services;

import lv.javaguru.java2.cookingApp.Recipe;
import lv.javaguru.java2.cookingApp.database.Database;

import java.util.List;

public class GetAllRecipesService {
    private Database database;

    public GetAllRecipesService(Database database) {
        this.database = database;
    }

    public List<Recipe> execute() {
        return database.getAllRecipes();
    }
}
