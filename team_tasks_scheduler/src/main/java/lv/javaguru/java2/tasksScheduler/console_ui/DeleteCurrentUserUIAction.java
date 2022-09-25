package lv.javaguru.java2.tasksScheduler.console_ui;

import lv.javaguru.java2.tasksScheduler.services.DeleteCurrentUserService;

import java.util.Scanner;

public class DeleteCurrentUserUIAction implements UIAction {

    private DeleteCurrentUserService deleteCurrentUserService;

    public DeleteCurrentUserUIAction(DeleteCurrentUserService deleteCurrentUserService) {
        this.deleteCurrentUserService = deleteCurrentUserService;
    }

    @Override
    public boolean execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter 'Y' to confirm current user deletion : ");
        String input = scanner.nextLine();
        if (!input.equals("Y")) {
            return false;
        }
        String deletedUserName = deleteCurrentUserService.execute();
        if (deletedUserName == null) {
            System.out.println("Something went wrong. Please contact administrator.");
            return false;
        }
        System.out.println("User " + deletedUserName + " has been deleted.");
        return true;
    }
}
