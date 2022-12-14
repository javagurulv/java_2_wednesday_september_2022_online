package lv.javaguru.java2.tasksScheduler.console_ui;



import lv.javaguru.java2.tasksScheduler.core.domain.Task;
import lv.javaguru.java2.tasksScheduler.core.requests.DeleteTaskRequest;
import lv.javaguru.java2.tasksScheduler.core.requests.GetOutstandingTasksRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.DeleteTaskResponse;
import lv.javaguru.java2.tasksScheduler.core.responses.GetOutstandingTasksResponse;
import lv.javaguru.java2.tasksScheduler.core.services.menu_services.DeleteTaskService;
import lv.javaguru.java2.tasksScheduler.core.services.menu_services.GetOutstandingTasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

@Component
public class DeleteTaskUIAction implements UIAction{

    @Autowired
    private DeleteTaskService deleteTaskService;
    @Autowired private GetOutstandingTasksService getOutstandingTasksService;

    @Override
    public boolean execute() {
        Scanner scanner = new Scanner(System.in);

        GetOutstandingTasksRequest requestTasks = new GetOutstandingTasksRequest(LocalDateTime.MAX);
        GetOutstandingTasksResponse responseTasks = getOutstandingTasksService.execute(requestTasks);

        List<Task> tasks = responseTasks.getTasks();
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

        DeleteTaskRequest request = new DeleteTaskRequest(tasks.get(taskNumber).getId());
        DeleteTaskResponse response = deleteTaskService.execute(request);

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
