package lv.javaguru.java2.tasksScheduler.console_ui;

import lv.javaguru.java2.tasksScheduler.domain.Task;
import lv.javaguru.java2.tasksScheduler.services.AmendTaskService;
import lv.javaguru.java2.tasksScheduler.services.GetOutstandingTasksService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class AmendTaskUIAction implements UIAction {

    Scanner scanner = new Scanner(System.in);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private AmendTaskService amendTaskService;
    private GetOutstandingTasksService getOutstandingTasksService;

    public AmendTaskUIAction(AmendTaskService amendTaskService, GetOutstandingTasksService getOutstandingTasksService) {
        this.amendTaskService = amendTaskService;
        this.getOutstandingTasksService = getOutstandingTasksService;
    }

    @Override
    public boolean execute() {
        List<Task> tasks =  getOutstandingTasksService.execute();
        int recordsTotal = ShowOutstandingTasks(tasks);
        if (recordsTotal == 0)
            return false;
        System.out.println("Select Task number to amend: ");
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(scanner.nextLine());
        } catch (RuntimeException e) {
            System.out.println("Invalid number entered. Operation failed.");
            return false;
        }
        if (taskNumber < 1 && taskNumber > recordsTotal) {
            System.out.println("Wrong selection. Operation failed.");
            return false;
        }
        taskNumber--;
        Task currentTask = tasks.get(taskNumber);
        if (currentTask == null) {
            System.out.println("Problem exists deriving current task data. Please contact administrator");
            return false;
        } else {
            System.out.println("Current task details:");
            System.out.println("Description = " + currentTask.getDescription());
            System.out.println("Regularity = " + currentTask.getRegularity());
            System.out.println("Due date = " + currentTask.getDueDate());
            System.out.println("End date = " + currentTask.getEndDate());
            System.out.println();
        }
        Task amendedTask = collectDataFromScreen(currentTask);
        boolean result = amendTaskService.execute(amendedTask);
        if (result) {
            System.out.println("Task information has been amended.");
        }
        else {
            System.out.println("Task information has not been amended.");
        }
        return true;
    }

    private int ShowOutstandingTasks(List<Task> tasks) {
        if (tasks == null || tasks.isEmpty()) {
            System.out.println("There are no records to amend.");
            return 0;
        }
        System.out.println("---  Outstanding tasks list start --- ");
        System.out.println();
        int recordNumber = 0;
        for (Task task : tasks) {
            System.out.println("Task number = " + ++recordNumber);
            System.out.println("Description: " + task.getDescription());
            System.out.println("Regularity: " + task.getRegularity());
            System.out.println("Due Date: " + task.getDueDate());
            System.out.println("End Date: " + task.getEndDate());
            System.out.println();
        }
        System.out.println("---  Outstanding tasks list end   --- ");
        System.out.println();
        return recordNumber;
    }

    private Task collectDataFromScreen(Task currentTask) {
        String[] fields = {"Description", "Regularity", "Due Date", "End Date"};
        String input;
        Task result = new Task(currentTask.getDescription(), currentTask.getRegularity(),
                currentTask.getDueDate(), currentTask.getEndDate(), currentTask.getUserId());
        result.setId(currentTask.getId());
        for (int i = 0; i < fields.length; i++) {
            if (result == null)
                break;
            System.out.println("Do you wish to amend " + fields[i] + " (Y/N)?");
            input = scanner.nextLine();
            if (input.equals("Y")) {
                System.out.println("Enter " + fields[i] + ": ");
                input = scanner.nextLine();
                switch (i) {
                    case 0: result.setDescription(input);
                        break;
                    case 1:
                        try {
                            result.setRegularity(Integer.parseInt(input));
                        } catch (RuntimeException e) {
                            System.out.println("Invalid number entered. Operation failed.");
                            result = null;
                        }
                        break;
                    case 2:
                        try {
                            result.setDueDate(LocalDateTime.parse(input, formatter));
                        } catch (RuntimeException e) {
                            System.out.println("Invalid Due Date entered. Operation failed.");
                            result = null;
                        }
                        break;
                    case 3:
                        try {
                            result.setEndDate(LocalDateTime.parse(input, formatter));
                        } catch (RuntimeException e) {
                            System.out.println("Invalid End Date entered. Operation failed.");
                            result = null;
                        }
                        break;
                    default:
                        break;
                }
            }
        }
        return result;
    }
}
