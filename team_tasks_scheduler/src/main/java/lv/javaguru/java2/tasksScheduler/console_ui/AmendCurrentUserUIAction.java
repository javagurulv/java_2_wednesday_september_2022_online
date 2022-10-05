package lv.javaguru.java2.tasksScheduler.console_ui;

import lv.javaguru.java2.tasksScheduler.domain.User;
import lv.javaguru.java2.tasksScheduler.requests.AmendCurrentUserRequest;
import lv.javaguru.java2.tasksScheduler.responses.AmendCurrentUserResponse;
import lv.javaguru.java2.tasksScheduler.services.AmendCurrentUserService;
import lv.javaguru.java2.tasksScheduler.services.GetCurrentUserService;

import java.util.Scanner;

public class AmendCurrentUserUIAction implements UIAction {

    private AmendCurrentUserService amendCurrentUserService;
    private GetCurrentUserService getCurrentUserService;

    public AmendCurrentUserUIAction(AmendCurrentUserService amendCurrentUserService, GetCurrentUserService getCurrentUserService) {
        this.amendCurrentUserService = amendCurrentUserService;
        this.getCurrentUserService = getCurrentUserService;
    }

    @Override
    public boolean execute() {
        User currentUser = getCurrentUserService.execute();
        if (currentUser == null) {
            System.out.println("Problem exists deriving current user information. Please contact administrator");
            return false;
        } else {
            System.out.println("Current user details:");
            System.out.println("Username = " + currentUser.getUsername());
            System.out.println("Password = " + currentUser.getPassword());
            System.out.println("Email = " + currentUser.getEmail());
            System.out.println("Mobile Phone = " + currentUser.getMobilePhone());
            System.out.println();
        }
        String[] input = collectDataFromScreen(currentUser);

        AmendCurrentUserRequest request = new AmendCurrentUserRequest(input[0], input[1], input[2], input[3]);
        AmendCurrentUserResponse response = amendCurrentUserService.execute(request);

        if (response.hasErrors()) {
            System.out.println("User information has not been amended.");
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
            );
            return false;
        } else {
            System.out.println("User information has been amended." + response.getUser().getId());
            return true;
        }
    }

    private String[] collectDataFromScreen(User currentUser) {
        String[] fields = {"Username", "Password", "Email", "Mobile Phone"};
        String[] result = new String[fields.length];
        Scanner scanner = new Scanner(System.in);
        String input;

        for (int i = 0; i < fields.length; i++) {
            System.out.println("Do you wish to amend " + fields[i] + " (Y/N)?");
            input = scanner.nextLine();
            input = input.toUpperCase();
            if (input.equals("Y")) {
                System.out.println("Enter " + fields[i] + ": ");
                input = scanner.nextLine();
                result[i] = input;
            }
            else {
                switch (i) {
                    case 0: result[i] = currentUser.getUsername();
                    break;
                    case 1: result[i] = currentUser.getPassword();
                    break;
                    case 2: result[i] = currentUser.getEmail();
                    break;
                    case 3: result[i] = currentUser.getMobilePhone();
                    break;
                    default:
                        break;
                }
            }
        }
        return result;
    }
}
