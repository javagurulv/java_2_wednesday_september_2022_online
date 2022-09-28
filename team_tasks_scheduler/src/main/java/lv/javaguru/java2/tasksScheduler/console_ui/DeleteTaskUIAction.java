package lv.javaguru.java2.tasksScheduler.console_ui;

import lv.javaguru.java2.tasksScheduler.domain.Task;
import lv.javaguru.java2.tasksScheduler.services.DeleteTaskService;
import lv.javaguru.java2.tasksScheduler.services.GetOutstandingTasksService;

import java.util.List;
import java.util.Scanner;

public class DeleteTaskUIAction implements UIAction{

    Scanner scanner = new Scanner(System.in);

    private DeleteTaskService deleteTaskService;
    private GetOutstandingTasksService getOutstandingTasksService;

    public DeleteTaskUIAction(DeleteTaskService deleteTaskService, GetOutstandingTasksService getOutstandingTasksService) {
        this.deleteTaskService = deleteTaskService;
        this.getOutstandingTasksService = getOutstandingTasksService;
    }

    @Override
    public boolean execute() {
        List<Task> tasks =  getOutstandingTasksService.execute();
        int recordsTotal = ShowOutstandingTasks(tasks);
        if (recordsTotal == 0)
            return false;
        System.out.println("Select Task number to delete: ");
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
        deleteTaskService.execute(tasks.get(taskNumber).getId());
        System.out.println("Task successfully deleted.");
        return true;
    }

    private int ShowOutstandingTasks(List<Task> tasks) {
        if (tasks == null || tasks.isEmpty()) {
            System.out.println("There are no records to delete.");
            return 0;
        }
        System.out.println("---  Outstanding tasks list start --- ");
        System.out.println();
        int recordNumber = 0;
        for (Task task : tasks) {
            System.out.println("Task number = " + ++recordNumber);
            System.out.println("Description: " + task.getDescription());
            System.out.println("Regularity: " + task.getRegularity());
            System.out.println("Due date: " + task.getDueDate());
            System.out.println("End date: " + task.getEndDate());
            System.out.println();
        }
        System.out.println("---  Outstanding tasks list end   --- ");
        System.out.println();
        return recordNumber;
    }
}