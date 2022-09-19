package lv.javaguru.java2.repo_men_inc.console_ui;

import lv.javaguru.java2.repo_men_inc.Database;
import lv.javaguru.java2.repo_men_inc.Debtor;

import java.util.Scanner;

public class AddDebtorUIAction implements UIAction{
    Database database;
    Scanner scanner;

    public AddDebtorUIAction(Database database, Scanner scanner) {
        this.database = database;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println("Enter debtors name: ");
        String debtorsName = scanner.nextLine();
        Debtor debtor = new Debtor(debtorsName);
        database.save(debtor);
        System.out.println("New Debtor was added to list.");
    }
}
