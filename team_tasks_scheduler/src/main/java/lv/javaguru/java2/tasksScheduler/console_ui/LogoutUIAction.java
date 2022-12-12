package lv.javaguru.java2.tasksScheduler.console_ui;



import lv.javaguru.java2.tasksScheduler.core.requests.LogoutRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.LogoutResponse;
import lv.javaguru.java2.tasksScheduler.core.services.menu_services.LogoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LogoutUIAction implements UIAction {

    @Autowired
    private LogoutService logoutService;

    @Override
    public boolean execute() {
        LogoutRequest request = new LogoutRequest();
        LogoutResponse response = logoutService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
            );
            return false;
        } else {
            System.out.println("You have been logged out");
            return true;
        }
    }
}
