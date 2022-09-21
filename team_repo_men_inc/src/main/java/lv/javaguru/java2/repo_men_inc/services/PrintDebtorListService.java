package lv.javaguru.java2.repo_men_inc.services;

import lv.javaguru.java2.repo_men_inc.database.Database;

public class PrintDebtorListService {
    Database database;

    public PrintDebtorListService(Database database) {
        this.database = database;
    }

    public void execute() {
        database.getAllDebtors().forEach(System.out::println);
    }
}
