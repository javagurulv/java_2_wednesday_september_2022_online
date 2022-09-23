package lv.javaguru.java2.repo_men_inc.console_ui;

import lv.javaguru.java2.repo_men_inc.core.requests.PrintDebtorsListRequest;
import lv.javaguru.java2.repo_men_inc.core.responses.PrintDebtorsListResponse;
import lv.javaguru.java2.repo_men_inc.services.PrintDebtorListService;

public class PrintDebtorListUIAction implements UIAction{
    PrintDebtorListService printDebtorListService;

    public PrintDebtorListUIAction(PrintDebtorListService printDebtorListService) {
        this.printDebtorListService = printDebtorListService;
    }

    @Override
    public void execute() {
        System.out.println("Debtors list: ");
        PrintDebtorsListRequest printDebtorsListRequest = new PrintDebtorsListRequest();
        PrintDebtorsListResponse printDebtorsListResponse = printDebtorListService.execute(printDebtorsListRequest);
        printDebtorsListResponse.getDebtors().forEach(System.out::println);
        System.out.println("Debtor list end.");
    }
}
