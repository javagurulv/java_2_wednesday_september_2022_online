package lv.javaguru.java2.tasksScheduler.console_ui;

import lv.javaguru.java2.tasksScheduler.core.requests.AddSettingsRequest;
import lv.javaguru.java2.tasksScheduler.core.requests.CheckSettingsRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.AddSettingsResponse;
import lv.javaguru.java2.tasksScheduler.core.responses.CheckSettingsResponse;
import lv.javaguru.java2.tasksScheduler.core.services.menu_services.AddSettingsService;
import lv.javaguru.java2.tasksScheduler.core.services.system.CheckSettingsExistenceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AddSettingsUIAction implements UIAction {

    @Autowired
    private CheckSettingsExistenceService checkSettingsExistenceService;
    @Autowired private AddSettingsService addSettingsService;

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
