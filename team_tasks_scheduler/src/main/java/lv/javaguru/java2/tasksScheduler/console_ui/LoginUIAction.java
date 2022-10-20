package lv.javaguru.java2.tasksScheduler.console_ui;



import lv.javaguru.java2.tasksScheduler.domain.Task;
import lv.javaguru.java2.tasksScheduler.requests.GetTasksForTodayRequests;
import lv.javaguru.java2.tasksScheduler.requests.LoginRequest;
import lv.javaguru.java2.tasksScheduler.responses.GetTaskForTodayResponse;
import lv.javaguru.java2.tasksScheduler.responses.LoginResponse;
import lv.javaguru.java2.tasksScheduler.services.menu_services.GetTasksForTodayService;
import lv.javaguru.java2.tasksScheduler.services.menu_services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class LoginUIAction implements UIAction {

    @Autowired private LoginService loginService;
    @Autowired private GetTasksForTodayService getTasksForTodayService;

    @Override
    public boolean execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        LoginRequest requestLogin = new LoginRequest(username, password);
        LoginResponse responseLogin = loginService.execute(requestLogin);

        if (responseLogin.hasErrors()) {
            responseLogin.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
            );
            return false;
        }
        else {
            System.out.println("Welcome to the system, " + username  + "!");
            System.out.println();

            GetTasksForTodayRequests requestTasks = new GetTasksForTodayRequests();
            GetTaskForTodayResponse responseTasks =  getTasksForTodayService.execute(requestTasks);

            List<Task> tasks = responseTasks.getTasks();
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
        }
    }
}
