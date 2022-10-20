package lv.javaguru.java2.repo_men_inc.console_ui;

import lv.javaguru.java2.repo_men_inc.core.requests.AddDebtorRequest;
import lv.javaguru.java2.repo_men_inc.core.responses.AddDebtorResponse;
import lv.javaguru.java2.repo_men_inc.dependency_injection.DIComponent;
import lv.javaguru.java2.repo_men_inc.dependency_injection.DIDependency;
import lv.javaguru.java2.repo_men_inc.services.AddDebtorService;

import java.util.Scanner;

@DIComponent
public class AddDebtorUIAction implements UIAction{
    @DIDependency
    private AddDebtorService addDebtorService;

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter debtors name: ");
        String debtorsName = scanner.nextLine();
        AddDebtorRequest addDebtorRequest = new AddDebtorRequest(debtorsName);
        AddDebtorResponse addDebtorResponse = addDebtorService.execute(addDebtorRequest);

        if (addDebtorResponse.hasErrors()) {
            System.out.println("===================== errors =====================");
            addDebtorResponse.getErrors().forEach(coreError -> System.out.println(coreError.getField() + ": " + coreError.getMessage()));
            System.out.println("==================================================");
        } else {
            if (addDebtorResponse.isDebtorSavedToDatabase()) {
                System.out.println("New Debtor was added to the list.");
            } else {
                System.out.println("Failed to add new Debtor the list.");
            }
        }
    }
}
