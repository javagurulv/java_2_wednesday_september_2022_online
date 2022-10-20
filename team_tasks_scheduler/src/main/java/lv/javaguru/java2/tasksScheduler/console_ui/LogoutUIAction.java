package lv.javaguru.java2.tasksScheduler.console_ui;

import lv.javaguru.java2.tasksScheduler.dependency_injection.DIComponent;
import lv.javaguru.java2.tasksScheduler.dependency_injection.DIDependency;
import lv.javaguru.java2.tasksScheduler.requests.LogoutRequest;
import lv.javaguru.java2.tasksScheduler.responses.LogoutResponse;
import lv.javaguru.java2.tasksScheduler.services.menu_services.LogoutService;

@DIComponent
public class LogoutUIAction implements UIAction {

    @DIDependency private LogoutService logoutService;

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
