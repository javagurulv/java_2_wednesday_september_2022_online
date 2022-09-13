package lv.javaguru.java2.cookingApp.services;

import lv.javaguru.java2.cookingApp.database.Database;


public class PrintRecipeToConsoleService {
    private Database database;

    public PrintRecipeToConsoleService(Database database) {
        this.database = database;
    }

    public void execute(Long id) {
        database.getById(id).printToConsole();
    }
}
