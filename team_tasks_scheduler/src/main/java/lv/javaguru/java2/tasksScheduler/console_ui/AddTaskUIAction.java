package lv.javaguru.java2.tasksScheduler.console_ui;

import lv.javaguru.java2.tasksScheduler.domain.User;
import lv.javaguru.java2.tasksScheduler.services.AddTaskService;
import lv.javaguru.java2.tasksScheduler.services.CurrentUserService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AddTaskUIAction implements UIAction {

    private AddTaskService addTaskService;
    private CurrentUserService currentUserService;


    public AddTaskUIAction(AddTaskService addTaskService, CurrentUserService currentUserService) {
        this.addTaskService = addTaskService;
        this.currentUserService = currentUserService;
    }


    @Override
    public boolean execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter task description: ");
        String description = scanner.nextLine();
        System.out.println("Enter task regularity: ");
        int regularity = Integer.parseInt(scanner.nextLine());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        System.out.println("Enter task due date (yyyy-MM-dd HH:mm): ");
        String dueDateStr = scanner.nextLine();
        LocalDateTime  dueDate = LocalDateTime.parse(dueDateStr, formatter); //transform string to date/time
        System.out.println("Enter task end date (yyyy-MM-dd HH:mm): ");
        String endDateStr = scanner.nextLine();
        LocalDateTime  endDate = LocalDateTime.parse(endDateStr, formatter); //transform string to date/time
        User user  = currentUserService.execute();
        Long userId = user.getId();
        addTaskService.execute(description, regularity, dueDate, endDate, userId);

        return true;
    }
}
