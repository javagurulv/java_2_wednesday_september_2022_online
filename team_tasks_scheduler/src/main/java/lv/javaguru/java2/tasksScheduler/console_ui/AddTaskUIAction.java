package lv.javaguru.java2.tasksScheduler.console_ui;

import lv.javaguru.java2.tasksScheduler.services.AddTaskService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class AddTaskUIAction implements UIAction {

    Scanner scanner = new Scanner(System.in);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private AddTaskService addTaskService;

    public AddTaskUIAction(AddTaskService addTaskService) {
        this.addTaskService = addTaskService;
    }

    @Override
    public boolean execute() {
        System.out.println("Enter task description: ");
        String description = scanner.nextLine();
        System.out.println("Enter after what days count to repeat the task or 0 if this is once only task: ");
        int regularity;
        try {
            regularity = Integer.parseInt(scanner.nextLine());
        } catch (RuntimeException e) {
            System.out.println("Invalid number entered. Operation failed.");
            return false;
        }
        System.out.println("Enter task Due Date in format 'yyyy-MM-dd HH:mm':");
        LocalDateTime dueDate;
        try {
            dueDate = LocalDateTime.parse(scanner.nextLine(), formatter);
        } catch (RuntimeException e) {
            System.out.println("Invalid Due Date entered. Operation failed.");
            return false;
        }
        LocalDateTime endDate;
        if (regularity > 0) {
            System.out.println("Enter task End Date in format 'yyyy-MM-dd HH:mm':");
            try {
                endDate = LocalDateTime.parse(scanner.nextLine(), formatter);
            } catch (RuntimeException e) {
                System.out.println("Invalid End Date entered. Operation failed.");
                return false;
            }
        } else {
            endDate = dueDate;
        }
        boolean result = addTaskService.execute(description, regularity, dueDate, endDate);
        if (result)
            System.out.println("Task successfully added.");
        else
            System.out.println("Task adding failed.");
        return result;
    }
}
