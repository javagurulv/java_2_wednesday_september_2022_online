package lv.javaguru.java2.tasksScheduler.console_ui;



import lv.javaguru.java2.tasksScheduler.core.requests.AddTaskRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.AddTaskResponse;
import lv.javaguru.java2.tasksScheduler.core.services.menu_services.AddTaskService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

@Component
public class AddTaskUIAction implements UIAction {

    @Autowired
    private AddTaskService addTaskService;

    @Override
    public boolean execute() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter task description: ");
        String description = scanner.nextLine();
        System.out.println("Enter repeat cycle in days or 0 if this is a single task: ");
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
            System.out.println("Task successfully added. Task ID = " + response.getTask().getId());
            return true;
        }
    }
}
