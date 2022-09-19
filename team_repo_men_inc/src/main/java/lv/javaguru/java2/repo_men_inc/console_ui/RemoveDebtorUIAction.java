package lv.javaguru.java2.repo_men_inc.console_ui;

import lv.javaguru.java2.repo_men_inc.Database;

import java.util.Scanner;

public class RemoveDebtorUIAction implements UIAction{
    Database database;
    Scanner scanner;

    public RemoveDebtorUIAction(Database database, Scanner scanner) {
        this.database = database;
        this.scanner = scanner;
    }
    @Override
    public void execute() {
        System.out.println("Enter debtors Id: ");
        Long debtorsId = Long.parseLong(scanner.nextLine());
        database.delete(debtorsId);
        System.out.println("Debtor was removed from list.");
    }
}
