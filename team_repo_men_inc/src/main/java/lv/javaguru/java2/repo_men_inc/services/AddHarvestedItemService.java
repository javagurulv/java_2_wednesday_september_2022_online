package lv.javaguru.java2.repo_men_inc.services;

import lv.javaguru.java2.repo_men_inc.database.Database;

public class AddHarvestedItemService {
    Database database;

    public AddHarvestedItemService(Database database) {
        this.database = database;
    }

    public void execute(Long debtorsId, String harvestedItem) {
        database.getById(debtorsId).addIem(harvestedItem);
    }
}
