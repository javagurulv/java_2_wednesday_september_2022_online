package lv.javaguru.java2.tasksScheduler.console_ui;



import lv.javaguru.java2.tasksScheduler.core.domain.Task;
import lv.javaguru.java2.tasksScheduler.core.requests.GetOutstandingTasksRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.GetOutstandingTasksResponse;
import lv.javaguru.java2.tasksScheduler.core.services.menu_services.GetOutstandingTasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component
public class GetTasksForTodayUIAction implements UIAction {

    @Autowired
    private GetOutstandingTasksService getOutstandingTasksService;

    @Override
    public boolean execute() {
        GetOutstandingTasksRequest request = new GetOutstandingTasksRequest(LocalDateTime.now().plusDays(1).with(LocalTime.MIN));
        GetOutstandingTasksResponse response = getOutstandingTasksService.execute(request);

        if (response.hasErrors()) {
            response.getErrors().forEach(coreError ->
                    System.out.println("Error: " + coreError.getField() + " " + coreError.getMessage())
            );
            return false;
        } else {
            List<Task> tasks = response.getTasks();
            if (tasks == null || tasks.isEmpty()) {
                System.out.println("There are no tasks for today.");
                return true;
            }
            System.out.println("---  Today's tasks list start --- ");
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
            System.out.println("---  Today's tasks list end   --- ");
            System.out.println();
            return true;
        }
    }
}
