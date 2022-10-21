package myApp.consoleUI;
import myApp.core.requests.SeeYourAccountRequest;
import myApp.core.responses.SeeYourAccountResponse;
import myApp.core.services.SeeYourAccountService;
import myApp.core.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SeeYourAccountUIAction implements UIAction {
    @Autowired
    private SeeYourAccountService service;
    @Autowired
    private UserService userService;

    @Override
    public void execute() {
        String personalCode = userService.getPersonalCode();
        SeeYourAccountRequest request = new SeeYourAccountRequest(personalCode);
        SeeYourAccountResponse response = service.execute(request);
        if (response.getBankAccount().get().getAccount() != null) {
            System.out.println("Account: " + response.getBankAccount().get().getName() + " "
                    + response.getBankAccount().get().getSurname());
            System.out.println("Balance: " + response.getBankAccount().get().getAccount().getBalance());
        } else {
            System.out.println("Account: " + response.getBankAccount().get().getName() + " "
                    + response.getBankAccount().get().getSurname());
            System.out.println("Balance: don't have any account");
        }
    }
}
