package lv.javaguru.java2.repo_men_inc.console_ui;

import lv.javaguru.java2.repo_men_inc.Database;

import java.util.Scanner;

public class PrintDebtorList implements UIAction{
    Database database;

    public PrintDebtorList(Database database) {
        this.database = database;
    }

    @Override
    public void execute() {
        System.out.println("Debtors list: ");
        database.getAllDebtors().forEach(System.out::println);
        System.out.println("Debtor list end.");
    }
}
