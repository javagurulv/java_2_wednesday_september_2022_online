package lv.javaguru.java2.tasksScheduler.console_ui;

import lv.javaguru.java2.tasksScheduler.requests.AddSettingsRequest;
import lv.javaguru.java2.tasksScheduler.requests.CheckSettingsRequest;
import lv.javaguru.java2.tasksScheduler.responses.AddSettingsResponse;
import lv.javaguru.java2.tasksScheduler.responses.CheckSettingsResponse;
import lv.javaguru.java2.tasksScheduler.services.menu_services.AddSettingsService;
import lv.javaguru.java2.tasksScheduler.services.system.CheckSettingsExistenceService;

import java.util.Scanner;

public class AddSettingsUIAction implements UIAction {

    private CheckSettingsExistenceService checkSettingsExistenceService;
    private AddSettingsService addSettingsService;

    public AddSettingsUIAction(CheckSettingsExistenceService checkSettingsExistenceService,
                               AddSettingsService addSettingsService) {
        this.checkSettingsExistenceService = checkSettingsExistenceService;
        this.addSettingsService = addSettingsService;
    }

    @Override
    public boolean execute() {

        CheckSettingsRequest checkSettingsRequest = new CheckSettingsRequest();
        CheckSettingsResponse checkSettingsResponse = checkSettingsExistenceService.execute(checkSettingsRequest);
        if (checkSettingsResponse.doesRecordExist()) {
            return true;
        }

        Scanner scanner = new Scanner(System.in);
        System.out.println();
        System.out.println("Enter administrator password: ");
        String adminPassword = scanner.nextLine();
        System.out.println("Enter email from: ");
        String emailFrom = scanner.nextLine();
        System.out.println("Enter email password: ");
        String emailPassword = scanner.nextLine();
        System.out.println("Enter email host: ");
        String emailHost = scanner.nextLine();
        System.out.println("Enter email port: ");
        String emailPort = scanner.nextLine();
        System.out.println("Enter email protocol: ");
        String emailProtocol = scanner.nextLine();

        AddSettingsRequest request = new AddSettingsRequest(adminPassword, emailFrom, emailPassword,
                emailHost, emailPort, emailProtocol);
        AddSettingsResponse response = addSettingsService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
            );
            return false;
        }

        System.out.println("Settings have been successfully added.");

        return true;
    }
}
