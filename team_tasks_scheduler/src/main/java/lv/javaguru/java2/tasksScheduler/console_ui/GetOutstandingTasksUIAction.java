package lv.javaguru.java2.tasksScheduler.console_ui;

import lv.javaguru.java2.tasksScheduler.dependency_injection.DIComponent;
import lv.javaguru.java2.tasksScheduler.dependency_injection.DIDependency;
import lv.javaguru.java2.tasksScheduler.domain.Task;
import lv.javaguru.java2.tasksScheduler.requests.GetOutstandingTasksRequests;
import lv.javaguru.java2.tasksScheduler.responses.GetOutstandingTasksResponse;
import lv.javaguru.java2.tasksScheduler.services.menu_services.GetOutstandingTasksService;

import java.util.List;

@DIComponent
public class GetOutstandingTasksUIAction implements UIAction {

    @DIDependency private GetOutstandingTasksService getOutstandingTasksService;

    @Override
    public boolean execute() {
        GetOutstandingTasksRequests request = new GetOutstandingTasksRequests();
        GetOutstandingTasksResponse response = getOutstandingTasksService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
            );
            return false;
        } else {
            List<Task> tasks =  response.getTasks();
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
}
