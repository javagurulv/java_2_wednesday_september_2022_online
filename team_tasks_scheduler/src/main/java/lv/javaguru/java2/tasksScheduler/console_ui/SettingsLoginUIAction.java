package lv.javaguru.java2.tasksScheduler.console_ui;

import lv.javaguru.java2.tasksScheduler.domain.Task;
import lv.javaguru.java2.tasksScheduler.requests.CheckSettingsRequest;
import lv.javaguru.java2.tasksScheduler.requests.GetTasksForTodayRequests;
import lv.javaguru.java2.tasksScheduler.requests.LoginRequest;
import lv.javaguru.java2.tasksScheduler.requests.SettingsLoginRequest;
import lv.javaguru.java2.tasksScheduler.responses.CheckSettingsResponse;
import lv.javaguru.java2.tasksScheduler.responses.GetTaskForTodayResponse;
import lv.javaguru.java2.tasksScheduler.responses.LoginResponse;
import lv.javaguru.java2.tasksScheduler.responses.SettingsLoginResponse;
import lv.javaguru.java2.tasksScheduler.services.menu_services.GetTasksForTodayService;
import lv.javaguru.java2.tasksScheduler.services.menu_services.LoginService;
import lv.javaguru.java2.tasksScheduler.services.menu_services.SettingsLoginService;
import lv.javaguru.java2.tasksScheduler.services.system.CheckSettingsExistenceService;
import lv.javaguru.java2.tasksScheduler.services.system.SessionService;

import java.util.List;
import java.util.Scanner;

public class SettingsLoginUIAction implements UIAction {

    private CheckSettingsExistenceService checkSettingsExistenceService;
    private SettingsLoginService settingsLoginService;
    private SessionService sessionService;

    public SettingsLoginUIAction(CheckSettingsExistenceService checkSettingsExistenceService, SettingsLoginService settingsLoginService, SessionService sessionService) {
        this.checkSettingsExistenceService = checkSettingsExistenceService;
        this.settingsLoginService = settingsLoginService;
        this.sessionService = sessionService;
    }

    @Override
    public boolean execute() {
        CheckSettingsRequest checkSettingsRequest = new CheckSettingsRequest();
        CheckSettingsResponse checkSettingsResponse = checkSettingsExistenceService.execute(checkSettingsRequest);
        if (!checkSettingsResponse.doesRecordExist()) {
            System.out.println("Settings are not set up.");
            return false;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter administrator password: ");
        String password = scanner.nextLine();

        SettingsLoginRequest request = new SettingsLoginRequest(password);
        SettingsLoginResponse response = settingsLoginService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
            );
            return false;
        }
        else {
            System.out.println("Welcome to the system settings!");
            System.out.println();

            System.out.println("--- Current settings start ---");
            System.out.println("Administrator password = " + sessionService.getDecryptedPassword());
            System.out.println("Email from = " + response.getSettings().getEmailFrom());
            System.out.println("Email password = " + response.getSettings().getEmailPassword());
            System.out.println("Email host = " + response.getSettings().getEmailHost());
            System.out.println("Email port = " + response.getSettings().getEmailPort());
            System.out.println("Email protocol = " + response.getSettings().getEmailProtocol());
            System.out.println("---  Current settings end  ---");

            return true;
        }
    }
}
