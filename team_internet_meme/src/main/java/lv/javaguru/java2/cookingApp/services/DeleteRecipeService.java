package lv.javaguru.java2.cookingApp.services;

import lv.javaguru.java2.cookingApp.database.Database;

public class DeleteRecipeService {
    private Database database;

    public DeleteRecipeService(Database database) {
        this.database = database;
    }

    public void execute(Long id) {
        database.deleteById(id);
    }
}
