package lv.javaguru.java2.tasksScheduler.console_ui;

import lv.javaguru.java2.tasksScheduler.services.scheduled_jobs.DueDatesUpdateRunService;
import lv.javaguru.java2.tasksScheduler.services.scheduled_jobs.JobRunResult;
import lv.javaguru.java2.tasksScheduler.services.scheduled_jobs.RemindersSendingRunService;
import lv.javaguru.java2.tasksScheduler.services.scheduled_jobs.TasksCleanupRunService;
import lv.javaguru.java2.tasksScheduler.utils.ValueChecking;
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
                JobRunResult runResult = new JobRunResult();
                switch (input) {
                    case "1":
                        runResult = dueDatesUpdateRunService.execute(true);
                        break;
                    case "2":
                        runResult = remindersSendingRunService.execute(true);
                        break;
                    case "3":
                        runResult = tasksCleanupRunService.execute(true);
                        break;
                    default:
                }
                System.out.println("Job run results:");
                if (runResult != null) {
                    System.out.println("Job Name: " + runResult.getJobName());
                    System.out.println("Started: " + runResult.getTimestampStart().format(printFormat));
                    System.out.println("Ended: " + runResult.getTimestampEnd().format(printFormat));
                    System.out.println("Items processed: " + runResult.getActionsCount());
                    System.out.println("Completion status: " + runResult.getStatus());
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
