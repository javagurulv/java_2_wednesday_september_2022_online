package lv.javaguru.java2.tasksScheduler.services.scheduled_jobs;

import lv.javaguru.java2.tasksScheduler.database.TasksRepository;
import lv.javaguru.java2.tasksScheduler.database.UsersRepository;
import lv.javaguru.java2.tasksScheduler.domain.Settings;
import lv.javaguru.java2.tasksScheduler.domain.Task;
import lv.javaguru.java2.tasksScheduler.domain.User;
import lv.javaguru.java2.tasksScheduler.utils.Emails;
import lv.javaguru.java2.tasksScheduler.utils.ValueChecking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component
public class RemindersSendingService {

    @Autowired
    private TasksRepository tasksRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private Settings settings;

    public void execute() {
        List<User> users = usersRepository.getUsersAcceptedReminders();
        if (users == null) {
            return;
        }
        LocalDateTime todayStart = LocalDateTime.now().with(LocalTime.MIN);
        for (User user : users) {
            List<Task> tasks = tasksRepository.getAllOutstandingTasksByUserIdTillDate(user.getId(),
                    todayStart.plusDays(2));
            batchEmails(tasks);
        }
    }

    private void batchEmails(List<Task> tasks) {
        for (Task task : tasks) {
            sendEmail(task);
        }
    }

    private void sendEmail(Task task) {
        boolean send = false;
        LocalDateTime date = LocalDateTime.now();
        if (ValueChecking.dateIsInRange(task.getDueDate(),
                LocalDateTime.now().plusDays(1).with(LocalTime.MIN),
                LocalDateTime.now().plusDays(1).with(LocalTime.MAX))) {
            date = task.getDueDate();
            send = true;
        } else if (ValueChecking.dateIsInRange(task.getDueDate(),
                LocalDateTime.now().with(LocalTime.MIN),
                LocalDateTime.now().with(LocalTime.MAX)) &&
                task.getRegularity() == 1) {
            date = task.getDueDate().plusDays(1);
            send = true;
        }

        if (send) {
            Emails.sendEmail(settings.getEmailFrom(),
                    settings.getEmailPassword(),
                    settings.getEmailHost(),
                    settings.getEmailPort(),
                    settings.getEmailProtocol(),
                    usersRepository.getUserById(task.getUserId()).getEmail(),
                    "Reminder from the Tasks Scheduler application",
                    "Task: '" + task.getDescription() +
                            "' is scheduled on <b style='color:red;'>" +
                            date.toString() + "." + "</b>",
                    true);
        }
    }
}

