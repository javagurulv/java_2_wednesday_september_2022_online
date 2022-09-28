package lv.javaguru.java2.tasksScheduler.console_ui;

import lv.javaguru.java2.tasksScheduler.domain.Task;
import lv.javaguru.java2.tasksScheduler.services.GetAllUsersService;
import lv.javaguru.java2.tasksScheduler.services.GetTasksForTodayService;
import lv.javaguru.java2.tasksScheduler.services.LoginService;
import lv.javaguru.java2.tasksScheduler.utils.Encryption;

import java.util.List;
import java.util.Scanner;

public class LoginUIAction implements UIAction {

    private LoginService loginService;
    private GetTasksForTodayService getTasksForTodayService;

    public LoginUIAction(LoginService loginService, GetTasksForTodayService getTasksForTodayService) {
        this.loginService = loginService;
        this.getTasksForTodayService = getTasksForTodayService;
    }

    @Override
    public boolean execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        if (loginService.execute(username, password)) {
            System.out.println("Welcome to the system, " + username + "!");
            System.out.println();
            List<Task> tasks =  getTasksForTodayService.execute();
            if (tasks == null || tasks.isEmpty()) {
                System.out.println("There are no tasks for today.");
                return true;
            }
            System.out.println("---  Your today's tasks list start --- ");
            System.out.println();
            int recordNumber = 1;
            for (Task task : tasks) {
                System.out.println("Task number = " + recordNumber++);
                System.out.println("Description: " + task.getDescription());
                System.out.println("Regularity: " + task.getRegularity());
                System.out.println("Due date: " + task.getDueDate());
                System.out.println("End date: " + task.getEndDate());
                System.out.println();
            }
            System.out.println("---  Your today's tasks list end   --- ");
            System.out.println();
            return true;
        } else {
            System.out.println("Invalid credentials. Please try again.");
            return false;
        }
    }
}
