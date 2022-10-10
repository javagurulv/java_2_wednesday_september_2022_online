package lv.javaguru.java2.tasksScheduler.console_ui;

import lv.javaguru.java2.tasksScheduler.requests.ExitSettingsRequest;
import lv.javaguru.java2.tasksScheduler.requests.LogoutRequest;
import lv.javaguru.java2.tasksScheduler.responses.ExitSettingsResponse;
import lv.javaguru.java2.tasksScheduler.responses.LogoutResponse;
import lv.javaguru.java2.tasksScheduler.services.menu_services.ExitSettingsService;
import lv.javaguru.java2.tasksScheduler.services.menu_services.LogoutService;

public class ExitSettingsUIAction implements UIAction {

    private ExitSettingsService exitSettingsService;

    public ExitSettingsUIAction(ExitSettingsService exitSettingsService) {
        this.exitSettingsService = exitSettingsService;
    }

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