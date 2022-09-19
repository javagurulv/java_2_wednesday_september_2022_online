package lv.javaguru.java2.repo_men_inc.console_ui;

import lv.javaguru.java2.repo_men_inc.services.PrintDebtorListService;

public class PrintDebtorListUIAction implements UIAction{
    PrintDebtorListService printDebtorListService;

    public PrintDebtorListUIAction(PrintDebtorListService printDebtorListService) {
        this.printDebtorListService = printDebtorListService;
    }

    @Override
    public void execute() {
        System.out.println("Debtors list: ");
        printDebtorListService.execute();
        System.out.println("Debtor list end.");
    }
}
