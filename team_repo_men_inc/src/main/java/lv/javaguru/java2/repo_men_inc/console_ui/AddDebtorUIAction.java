package lv.javaguru.java2.repo_men_inc.console_ui;

import lv.javaguru.java2.repo_men_inc.core.requests.AddDebtorRequest;
import lv.javaguru.java2.repo_men_inc.core.responses.AddDebtorResponse;
import lv.javaguru.java2.repo_men_inc.core.services.AddDebtorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddDebtorUIAction implements UIAction{
    @Autowired
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
