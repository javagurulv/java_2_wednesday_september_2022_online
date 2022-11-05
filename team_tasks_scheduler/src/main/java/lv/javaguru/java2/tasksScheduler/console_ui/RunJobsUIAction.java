package lv.javaguru.java2.tasksScheduler.console_ui;

import lv.javaguru.java2.tasksScheduler.requests.JobRunRequest;
import lv.javaguru.java2.tasksScheduler.responses.JobRunResponse;
import lv.javaguru.java2.tasksScheduler.services.scheduled_jobs.DueDatesUpdateRunService;
import lv.javaguru.java2.tasksScheduler.services.scheduled_jobs.RemindersSendingRunService;
import lv.javaguru.java2.tasksScheduler.services.scheduled_jobs.TasksCleanupRunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

@Component
public class RunJobsUIAction implements UIAction {
    @Autowired private TasksCleanupRunService tasksCleanupRunService;
    @Autowired private DueDatesUpdateRunService  dueDatesUpdateRunService;
    @Autowired private RemindersSendingRunService remindersSendingRunService;

    @Override
    public boolean execute() {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter printFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

        do {
            System.out.println("Select job number to run or other character to exit:");
            System.out.println("1 - Due dates update");
            System.out.println("2 - Reminders sending");
            System.out.println("3 - Tasks cleanup");
            System.out.println();

            String input = scanner.nextLine();
            if (input.equals("1") || input.equals("2") || input.equals("3")) {
                JobRunRequest request = new JobRunRequest(true);
                JobRunResponse response = null;
                switch (input) {
                    case "1":
                        response = dueDatesUpdateRunService.execute(request);
                        break;
                    case "2":
                        response = remindersSendingRunService.execute(request);
                        break;
                    case "3":
                        response = tasksCleanupRunService.execute(request);
                        break;
                    default:
                }
                System.out.println("Job run results:");
                if (response != null) {
                    System.out.println("Job Name: " + response.getRunResult().getJobName());
                    System.out.println("Started: " + response.getRunResult().getTimestampStart().format(printFormat));
                    System.out.println("Ended: " + response.getRunResult().getTimestampEnd().format(printFormat));
                    System.out.println("Items processed: " + response.getRunResult().getActionsCount());
                    System.out.println("Completion status: " + response.getRunResult().getStatus());
                } else {
                    System.out.println("Job has been failed.");
                }
                System.out.println();
            } else {
                break;
            }
        } while (true);
        return true;
    }
}
