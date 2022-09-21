package lv.javaguru.java2.repo_men_inc.services;

import lv.javaguru.java2.repo_men_inc.Debtor;
import lv.javaguru.java2.repo_men_inc.database.Database;

public class AddDebtorService {
    Database database;

    public AddDebtorService(Database database) {
        this.database = database;
    }

    public void execute(String debtorsName) {
        Debtor debtor = new Debtor(debtorsName);
        database.save(debtor);
    }
}
