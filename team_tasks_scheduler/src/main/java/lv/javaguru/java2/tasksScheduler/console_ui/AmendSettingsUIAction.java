package lv.javaguru.java2.tasksScheduler.console_ui;



import lv.javaguru.java2.tasksScheduler.core.domain.Settings;
import lv.javaguru.java2.tasksScheduler.core.requests.AmendSettingsRequest;
import lv.javaguru.java2.tasksScheduler.core.requests.GetSettingsRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.AmendSettingsResponse;
import lv.javaguru.java2.tasksScheduler.core.responses.GetSettingsResponse;
import lv.javaguru.java2.tasksScheduler.core.services.menu_services.AmendSettingsService;
import lv.javaguru.java2.tasksScheduler.core.services.system.GetSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AmendSettingsUIAction implements UIAction {

    @Autowired
    private AmendSettingsService amendSettingsService;
    @Autowired private GetSettingsService getSettingsService;

    @Override
    public boolean execute() {

        GetSettingsResponse getSettingsResponse = getSettingsService.execute(new GetSettingsRequest(true));
        Settings currentSettings = getSettingsResponse.getSettings();

        if (currentSettings == null) {
            System.out.println("Settings are not set up.");
            return false;
        } else {
            System.out.println("Administrator password = " + currentSettings.getAdminPassword());
            System.out.println("Email from = " + currentSettings.getEmailFrom());
            System.out.println("Email password = " + currentSettings.getEmailPassword());
            System.out.println("Email host = " + currentSettings.getEmailHost());
            System.out.println("Email port = " + currentSettings.getEmailPort());
            System.out.println("Email protocol = " + currentSettings.getEmailProtocol());
            System.out.println();
        }

        Settings input = collectDataFromScreen(currentSettings);

        AmendSettingsRequest request = new AmendSettingsRequest(input);
        AmendSettingsResponse response = amendSettingsService.execute(request);

        if (response == null) {
            System.out.println("Nothing has been detected for amending.");
            return true;
        }

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
            );
            return false;
        }
        System.out.println("Settings have been successfully amended.");
        return true;
    }

    private Settings collectDataFromScreen(Settings settings) {
        String[] fields = {"Administrator password", "Email from", "Email password",
        "Email host", "Email port", "Email protocol"};
        Scanner scanner = new Scanner(System.in);
        String input;

        for (int i = 0; i < fields.length; i++) {
            System.out.println("Press 'Y' to amend " + fields[i]);
            input = scanner.nextLine();
            input = input.toUpperCase();
            if (input.equals("Y")) {
                System.out.println("Enter " + fields[i] + ": ");

                switch (i) {
                    case 0: settings.setAdminPassword(scanner.nextLine());
                        break;
                    case 1: settings.setEmailFrom(scanner.nextLine());
                        break;
                    case 2: settings.setEmailPassword(scanner.nextLine());
                        break;
                    case 3: settings.setEmailHost(scanner.nextLine());
                        break;
                    case 4: settings.setEmailPort(scanner.nextLine());
                        break;
                    case 5: settings.setEmailProtocol(scanner.nextLine());
                        break;
                    default:
                        break;
                }
            }
        }
        return settings;
    }
}
