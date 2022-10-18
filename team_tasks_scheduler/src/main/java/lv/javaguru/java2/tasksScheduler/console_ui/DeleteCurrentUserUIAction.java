package lv.javaguru.java2.tasksScheduler.console_ui;

import lv.javaguru.java2.tasksScheduler.dependency_injection.DIComponent;
import lv.javaguru.java2.tasksScheduler.dependency_injection.DIDependency;
import lv.javaguru.java2.tasksScheduler.requests.DeleteCurrentUserRequest;
import lv.javaguru.java2.tasksScheduler.responses.DeleteCurrentUserResponse;
import lv.javaguru.java2.tasksScheduler.services.menu_services.DeleteCurrentUserService;

import java.util.Scanner;

@DIComponent
public class DeleteCurrentUserUIAction implements UIAction {

    @DIDependency
    private DeleteCurrentUserService deleteCurrentUserService;

    @Override
    public boolean execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter 'Y' to confirm current user deletion : ");
        String input = scanner.nextLine();
        if (!input.equals("Y")) {
            return false;
        }

        DeleteCurrentUserRequest request = new DeleteCurrentUserRequest();
        DeleteCurrentUserResponse response = deleteCurrentUserService.execute(request);

        if (response.hasErrors()) {
            System.out.println("Something went wrong. Please contact administrator.");
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
            );
            return false;
        } else {
            System.out.println("User " + response.getDeletedUserName() + " has been deleted.");
            return true;
        }
    }
}
