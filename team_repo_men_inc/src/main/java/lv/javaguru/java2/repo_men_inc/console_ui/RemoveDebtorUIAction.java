package lv.javaguru.java2.repo_men_inc.console_ui;

import lv.javaguru.java2.repo_men_inc.services.RemoveDebtorService;

import java.util.Scanner;

public class RemoveDebtorUIAction implements UIAction{
    RemoveDebtorService removeDebtorService;
    Scanner scanner;

    public RemoveDebtorUIAction(RemoveDebtorService removeDebtorService, Scanner scanner) {
        this.removeDebtorService = removeDebtorService;
        this.scanner = scanner;
    }
    @Override
    public void execute() {
        System.out.println("Enter debtors Id: ");
        Long debtorsId = Long.parseLong(scanner.nextLine());
        removeDebtorService.execute(debtorsId);
        System.out.println("Debtor was removed from list.");
    }
}
