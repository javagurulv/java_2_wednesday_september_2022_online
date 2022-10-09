package lv.javaguru.java2.tasksScheduler.console_ui;

import lv.javaguru.java2.tasksScheduler.requests.AddTaskRequest;
import lv.javaguru.java2.tasksScheduler.responses.AddTaskResponse;
import lv.javaguru.java2.tasksScheduler.services.menu_services.AddTaskService;

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
        AddTaskRequest request = new AddTaskRequest(description, regularity, dueDate, endDate);
        AddTaskResponse response = addTaskService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
            );
            return false;
        } else {
            System.out.println("Task successfully added." + response.getTask().getId());
            return true;
        }
    }
}
