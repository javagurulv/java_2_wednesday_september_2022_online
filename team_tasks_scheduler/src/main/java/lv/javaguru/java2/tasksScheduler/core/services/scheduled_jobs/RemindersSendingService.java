package lv.javaguru.java2.tasksScheduler.core.services.scheduled_jobs;


import lv.javaguru.java2.tasksScheduler.core.database.jpa.JpaTasksRepository;
import lv.javaguru.java2.tasksScheduler.core.database.jpa.JpaUsersRepository;
import lv.javaguru.java2.tasksScheduler.core.domain.Task;
import lv.javaguru.java2.tasksScheduler.core.domain.User;
import lv.javaguru.java2.tasksScheduler.core.requests.JobRunRequest;
import lv.javaguru.java2.tasksScheduler.core.responses.JobRunResponse;
import lv.javaguru.java2.tasksScheduler.core.services.system.CreateLogsService;
import lv.javaguru.java2.tasksScheduler.core.services.system.ReminderEmailTemplateCreationService;
import lv.javaguru.java2.tasksScheduler.utils.Email;
import lv.javaguru.java2.tasksScheduler.utils.ValueChecking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class RemindersSendingService {
    @Value("${logs.job.tasks.reminderssending.create}")
    private boolean createLog;

    @Value("${reminder.email.send}")
    private boolean sendReminders;

    @Autowired private JpaTasksRepository tasksRepository;
    @Autowired private JpaUsersRepository usersRepository;
    @Autowired private ReminderEmailTemplateCreationService reminderEmailTemplateCreationService;
    @Autowired private CreateLogsService createLogsService;

    private Email reminder;;

    public JobRunResponse execute(JobRunRequest request) {
        if (!sendReminders) {
            return null;
        }
        JobRunResult result = new JobRunResult("RemindersSending");
        if (!request.isManual()) {
            result.setRunType("Auto");
        }
        try {
            result.setActionsCount(proceed());
            result.setTimestampEnd(LocalDateTime.now());
            result.setStatus("Succeed");
        } catch (Exception e) {
            return new JobRunResponse(result);
        }
        if (createLog) {
            createLogsService.execute(result.getRecordInLogFormat());
        }
        return new JobRunResponse(result);
    }

    private int proceed() {
        reminder = reminderEmailTemplateCreationService.getReminderEmailDraft();
        if (reminder == null) {
            return 0;
        }
        List<User> users = usersRepository.getUsersAcceptedReminders();
        if (users == null) {
            return 0;
        }
        int sentEmails = 0;
        LocalDateTime todayStart = LocalDateTime.now().with(LocalTime.MIN);
        for (User user : users) {
            List<Task> tasks = tasksRepository.getAllOutstandingTasksByUserIdTillDate(user.getId(),
                    todayStart.plusDays(2));
            sentEmails+=batchEmails(tasks);
        }
        return sentEmails;
    }

    private int batchEmails(List<Task> tasks) {
        int sentEmails = 0;
        for (Task task : tasks) {
            if (sendEmail(task)) {
                sentEmails++;
            }
        }
        return sentEmails;
    }

    private boolean sendEmail(Task task) {
        LocalDateTime taskDueDate = getTaskDueDateForEmail(task);
        if (taskDueDate != null) {
            reminder.setTo(usersRepository.findUserById(task.getUserId()).getEmail());
            reminder.setBody(getReminderEmailBody(task, taskDueDate));
            return reminder.send();
        }
        return false;
    }

    private LocalDateTime getTaskDueDateForEmail(Task task) {
        if (ValueChecking.dateIsInRange(task.getDueDate(),
                LocalDateTime.now().plusDays(1).with(LocalTime.MIN),
                LocalDateTime.now().plusDays(1).with(LocalTime.MAX))) {
            return task.getDueDate();
        }
        if (ValueChecking.dateIsInRange(task.getDueDate(),
                LocalDateTime.now().with(LocalTime.MIN),
                LocalDateTime.now().with(LocalTime.MAX)) &&
                task.getRegularity() == 1) {
            return task.getDueDate().plusDays(1);
        }
        return null;
    }

    private String getReminderEmailBody(Task task, LocalDateTime dueDate) {
        DateTimeFormatter printFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String result = "";
        if (reminder.isBodyIsHTML()) {
            if (!reminder.getBodyHeader().isBlank()) {
                result += "<b>" + reminder.getBodyHeader() + "</b><br><br>";
            }
            result += "<b style='color:blue;'>Due date and time</b>: " + dueDate.format(printFormat) + "<br>" +
                    "<b style='color:blue'>Task</b>: " + task.getDescription();
            if (!reminder.getBodyFooter().isBlank()) {
                result += "<br><br><b style='color:red'><em>" + reminder.getBodyFooter() + "</em></b>";
            }
        } else {
            if (!reminder.getBodyHeader().isBlank()) {
                result += reminder.getBodyHeader() + "\r\n" + "\r\n";
            }
            result += "Due date and time: " + dueDate.format(printFormat) + "\r\n" +
                    "Task: " + task.getDescription();
            if (!reminder.getBodyFooter().isBlank()) {
                result += "\r\n" + "\r\n" + reminder.getBodyFooter();
            }
        }
        return result;
    }
}
