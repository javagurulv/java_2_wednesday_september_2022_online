package lv.javaguru.java2.tasksScheduler.console_ui;



import lv.javaguru.java2.tasksScheduler.domain.Settings;
import lv.javaguru.java2.tasksScheduler.domain.User;
import lv.javaguru.java2.tasksScheduler.requests.AddSettingsRequest;
import lv.javaguru.java2.tasksScheduler.requests.AmendSettingsRequest;
import lv.javaguru.java2.tasksScheduler.requests.CheckSettingsRequest;
import lv.javaguru.java2.tasksScheduler.requests.GetSettingsRequest;
import lv.javaguru.java2.tasksScheduler.responses.AddSettingsResponse;
import lv.javaguru.java2.tasksScheduler.responses.AmendSettingsResponse;
import lv.javaguru.java2.tasksScheduler.responses.CheckSettingsResponse;
import lv.javaguru.java2.tasksScheduler.responses.GetSettingsResponse;
import lv.javaguru.java2.tasksScheduler.services.menu_services.AddSettingsService;
import lv.javaguru.java2.tasksScheduler.services.menu_services.AmendSettingsService;
import lv.javaguru.java2.tasksScheduler.services.system.CheckSettingsExistenceService;
import lv.javaguru.java2.tasksScheduler.services.system.GetSettingsService;
import lv.javaguru.java2.tasksScheduler.services.system.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AmendSettingsUIAction implements UIAction {

    @Autowired
    private AmendSettingsService amendSettingsService;
    @Autowired private GetSettingsService getSettingsService;
    @Autowired private SessionService sessionService;

    @Override
    public boolean execute() {

        GetSettingsResponse getSettingsResponse = getSettingsService.execute(new GetSettingsRequest());
        Settings currentSettings = getSettingsResponse.getSettings();

        if (currentSettings == null) {
            System.out.println("Settings are not set up.");
            return false;
        } else {
            System.out.println("Administrator password = " + sessionService.getDecryptedPassword());
            System.out.println("Email from = " + currentSettings.getEmailFrom());
            System.out.println("Email password = " + currentSettings.getEmailPassword());
            System.out.println("Email host = " + currentSettings.getEmailHost());
            System.out.println("Email port = " + currentSettings.getEmailPort());
            System.out.println("Email protocol = " + currentSettings.getEmailProtocol());
            System.out.println();
        }

        String[] input = collectDataFromScreen(currentSettings);

        AmendSettingsRequest request = new AmendSettingsRequest(input[0], input[1], input[2],
                input[3], input[4], input[5]);
        AmendSettingsResponse response = amendSettingsService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
            );
            return false;
        }
        System.out.println("Settings have been successfully amended.");
        return true;
    }

    private String[] collectDataFromScreen(Settings currentSettings) {
        String[] fields = {"Administrator password", "Email from", "Email password",
        "Email host", "Email port", "Email protocol"};
        String[] result = new String[fields.length];
        Scanner scanner = new Scanner(System.in);
        String input;

        for (int i = 0; i < fields.length; i++) {
            System.out.println("Press 'Y' to amend " + fields[i]);
            input = scanner.nextLine();
            input = input.toUpperCase();
            if (input.equals("Y")) {
                System.out.println("Enter " + fields[i] + ": ");
                input = scanner.nextLine();
                result[i] = input;
            }
            else {
                switch (i) {
                    case 0: result[i] = sessionService.getDecryptedPassword();
                        break;
                    case 1: result[i] = currentSettings.getEmailFrom();
                        break;
                    case 2: result[i] = currentSettings.getEmailPassword();
                        break;
                    case 3: result[i] = currentSettings.getEmailHost();
                        break;
                    case 4: result[i] = currentSettings.getEmailPort();
                        break;
                    case 5: result[i] = currentSettings.getEmailProtocol();
                        break;
                    default:
                        break;
                }
            }
        }
        return result;
    }
}
