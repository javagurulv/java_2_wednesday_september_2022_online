package lv.javaguru.java2.repo_men_inc.console_ui;

import lv.javaguru.java2.repo_men_inc.services.AddDebtorService;

import java.util.Scanner;

public class AddDebtorUIAction implements UIAction{
    AddDebtorService addDebtorService;
    Scanner scanner;
    public AddDebtorUIAction(AddDebtorService addDebtorService, Scanner scanner) {
        this.addDebtorService = addDebtorService;
        this.scanner = scanner;
    }

    @Override
    public void execute() {
        System.out.println("Enter debtors name: ");
        String debtorsName = scanner.nextLine();
        addDebtorService.execute(debtorsName);
        System.out.println("New Debtor was added to list.");
    }
}
