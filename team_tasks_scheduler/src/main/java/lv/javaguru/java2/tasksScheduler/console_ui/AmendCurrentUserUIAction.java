package lv.javaguru.java2.tasksScheduler.console_ui;



import lv.javaguru.java2.tasksScheduler.core.domain.User;
import lv.javaguru.java2.tasksScheduler.core.requests.AmendCurrentUserRequest;
import lv.javaguru.java2.tasksScheduler.core.requests.GetCurrentUserRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.AmendCurrentUserResponse;
import lv.javaguru.java2.tasksScheduler.core.responses.GetCurrentUserResponse;
import lv.javaguru.java2.tasksScheduler.core.services.menu_services.AmendCurrentUserService;
import lv.javaguru.java2.tasksScheduler.core.services.system.GetCurrentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class AmendCurrentUserUIAction implements UIAction {

    @Autowired
    private AmendCurrentUserService amendCurrentUserService;
    @Autowired private GetCurrentUserService getCurrentUserService;

    @Override
    public boolean execute() {

        GetCurrentUserRequest getCurrentUserRequest = new GetCurrentUserRequest(true);
        GetCurrentUserResponse getCurrentUserResponse = getCurrentUserService.execute(getCurrentUserRequest);
        User currentUser = getCurrentUserResponse.getUser();

        if (currentUser == null) {
            System.out.println("Problem exists deriving current user information. Please contact administrator");
            return false;
        } else {
            System.out.println("Current user details:");
            System.out.println("Username = " + currentUser.getUsername());
            System.out.println("Password = " + currentUser.getPassword());
            System.out.println("Email = " + currentUser.getEmail());
            System.out.println("Send reminders = " + currentUser.isSendReminders());
            System.out.println();
        }

        User input = collectDataFromScreen(currentUser);

        AmendCurrentUserRequest request = new AmendCurrentUserRequest(input);
        AmendCurrentUserResponse response = amendCurrentUserService.execute(request);

        if (response == null) {
            System.out.println("Nothing has been detected for amending.");
            return true;
        }

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
            );
            return false;
        } else {
            System.out.println("User information has been successfully amended. User ID = " + response.getUser().getId());
            return true;
        }
    }

    private User collectDataFromScreen(User user) {
        String[] fields = {"Username", "Password", "Email", "Reminders indicator"};
        Scanner scanner = new Scanner(System.in);
        String input;

        for (int i = 0; i < fields.length; i++) {
            System.out.println("Press 'Y' to amend " + fields[i]);
            input = scanner.nextLine();
            input = input.toUpperCase();
            if (input.equals("Y")) {
                if (i == 3)
                    System.out.println("Push 'Y' if reminders by email are required: ");
                else
                    System.out.println("Enter " + fields[i] + ": ");

                switch (i) {
                    case 0: user.setUsername(scanner.nextLine());
                    break;
                    case 1: user.setPassword(scanner.nextLine());
                    break;
                    case 2: user.setEmail(scanner.nextLine());
                    break;
                    case 3:
                        if (scanner.nextLine().equalsIgnoreCase("Y")) {
                            user.setSendReminders(true);
                        } else {
                            user.setSendReminders(false);
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        return user;
    }
}
