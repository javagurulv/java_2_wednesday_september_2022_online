package lv.javaguru.java2.repo_men_inc.console_ui;

import lv.javaguru.java2.repo_men_inc.core.requests.RemoveDebtorRequest;
import lv.javaguru.java2.repo_men_inc.core.responses.RemoveDebtorResponse;
import lv.javaguru.java2.repo_men_inc.dependency_injection.DIComponent;
import lv.javaguru.java2.repo_men_inc.dependency_injection.DIDependency;
import lv.javaguru.java2.repo_men_inc.services.RemoveDebtorService;

import java.util.Scanner;

@DIComponent
public class RemoveDebtorUIAction implements UIAction{
    @DIDependency
    private RemoveDebtorService removeDebtorService;

    @Override
    public void execute() {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter debtors Id: ");
            Long debtorsId = Long.parseLong(scanner.nextLine());
            RemoveDebtorRequest removeDebtorRequest = new RemoveDebtorRequest(debtorsId);
            RemoveDebtorResponse removeDebtorResponse = removeDebtorService.execute(removeDebtorRequest);

            if (removeDebtorResponse.hasErrors()) {
                System.out.println("===================== errors =====================");
                removeDebtorResponse.getErrors().forEach(coreError -> System.out.println(coreError.getField() + ": " + coreError.getMessage()));
                System.out.println("==================================================");
            } else {
                if (removeDebtorResponse.isDebtorRemoved()) {
                    System.out.println("Debtor was removed from the list.");
                } else {
                    System.out.println("Failed to remove debtor from the list.");
                }
            }
        } catch (NumberFormatException numberFormatException) {
            System.out.println("INVALID INPUT!");
        }
    }
}
