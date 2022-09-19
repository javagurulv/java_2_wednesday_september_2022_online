package lv.javaguru.java2.repo_men_inc.console_ui;

import lv.javaguru.java2.repo_men_inc.Database;

import java.util.Scanner;

public class AddHarvestedItem implements UIAction{
    Database database;
    Scanner scanner;

    public AddHarvestedItem(Database database, Scanner scanner) {
        this.database = database;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println("Enter debtors Id: ");
        Long debtorsId = Long.parseLong(scanner.nextLine());
        System.out.println("Enter harvested item");
        String harvestedItem = scanner.nextLine();
        database.getById(debtorsId).addIem(harvestedItem);
        System.out.println("Harvested Item added to debtors list.");
    }
}
