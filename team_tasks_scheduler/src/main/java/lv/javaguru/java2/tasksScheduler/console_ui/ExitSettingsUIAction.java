package lv.javaguru.java2.tasksScheduler.console_ui;

import lv.javaguru.java2.tasksScheduler.dependency_injection.DIComponent;
import lv.javaguru.java2.tasksScheduler.dependency_injection.DIDependency;
import lv.javaguru.java2.tasksScheduler.requests.ExitSettingsRequest;
import lv.javaguru.java2.tasksScheduler.requests.LogoutRequest;
import lv.javaguru.java2.tasksScheduler.responses.ExitSettingsResponse;
import lv.javaguru.java2.tasksScheduler.responses.LogoutResponse;
import lv.javaguru.java2.tasksScheduler.services.menu_services.ExitSettingsService;
import lv.javaguru.java2.tasksScheduler.services.menu_services.LogoutService;

@DIComponent
public class ExitSettingsUIAction implements UIAction {

    @DIDependency private ExitSettingsService exitSettingsService;

    @Override
    public boolean execute() {
        ExitSettingsRequest request = new ExitSettingsRequest();
        ExitSettingsResponse response = exitSettingsService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
            );
            return false;
        }
        return true;
    }
}
