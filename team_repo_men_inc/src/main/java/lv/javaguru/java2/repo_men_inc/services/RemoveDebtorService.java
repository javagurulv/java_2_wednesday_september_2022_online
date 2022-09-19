package lv.javaguru.java2.repo_men_inc.services;

import lv.javaguru.java2.repo_men_inc.database.Database;

public class RemoveDebtorService {
    Database database;

    public RemoveDebtorService(Database database) {
        this.database = database;
    }

    public void execute(Long debtorsId) {
        database.delete(debtorsId);
    }
}
