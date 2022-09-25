package lv.javaguru.java2.tasksScheduler.console_ui;

import lv.javaguru.java2.tasksScheduler.domain.Task;
import lv.javaguru.java2.tasksScheduler.services.GetOutstandingTasksService;

import java.util.List;

public class GetOutstandingTasksUIAction implements UIAction {

    private GetOutstandingTasksService getOutstandingTasksService;

    public GetOutstandingTasksUIAction(GetOutstandingTasksService getOutstandingTasksService) {
        this.getOutstandingTasksService = getOutstandingTasksService;
    }

    @Override
    public boolean execute() {
        List<Task> tasks =  getOutstandingTasksService.execute();
        if (tasks == null || tasks.isEmpty()) {
            System.out.println("There are no records to display.");
            return true;
        }
        System.out.println("---  Outstanding tasks list start --- ");
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
        System.out.println("---  Outstanding tasks list end   --- ");
        System.out.println();
        return true;
    }
}
