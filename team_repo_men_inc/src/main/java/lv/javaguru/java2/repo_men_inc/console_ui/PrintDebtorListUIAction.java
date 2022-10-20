package lv.javaguru.java2.repo_men_inc.console_ui;

import lv.javaguru.java2.repo_men_inc.core.requests.PrintDebtorsListRequest;
import lv.javaguru.java2.repo_men_inc.core.responses.PrintDebtorsListResponse;
import lv.javaguru.java2.repo_men_inc.dependency_injection.DIComponent;
import lv.javaguru.java2.repo_men_inc.dependency_injection.DIDependency;
import lv.javaguru.java2.repo_men_inc.services.PrintDebtorListService;

@DIComponent
public class PrintDebtorListUIAction implements UIAction{
    @DIDependency
    private PrintDebtorListService printDebtorListService;

    @Override
    public void execute() {
        PrintDebtorsListRequest printDebtorsListRequest = new PrintDebtorsListRequest();
        PrintDebtorsListResponse printDebtorsListResponse = printDebtorListService.execute(printDebtorsListRequest);
        if (printDebtorsListResponse.getDebtors().isEmpty()) {
            System.out.println("Debtor List is empty!");
        } else {
            System.out.println("Debtors list: ");
            printDebtorsListResponse.getDebtors().forEach(System.out::println);
            System.out.println("Debtor list end.");
        }
    }
}
