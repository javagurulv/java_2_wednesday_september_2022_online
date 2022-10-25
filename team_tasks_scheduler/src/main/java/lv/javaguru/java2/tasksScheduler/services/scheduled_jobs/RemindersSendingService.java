package lv.javaguru.java2.tasksScheduler.services.scheduled_jobs;

import lv.javaguru.java2.tasksScheduler.database.TasksRepository;
import lv.javaguru.java2.tasksScheduler.database.UsersRepository;
import lv.javaguru.java2.tasksScheduler.domain.Task;
import lv.javaguru.java2.tasksScheduler.domain.User;
import lv.javaguru.java2.tasksScheduler.services.system.ReminderEmailService;
import lv.javaguru.java2.tasksScheduler.utils.Email;
import lv.javaguru.java2.tasksScheduler.utils.ValueChecking;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Component
public class RemindersSendingService {

    private TasksRepository tasksRepository;
    private UsersRepository usersRepository;
    private ReminderEmailService reminderEmailService;

	private Email reminder;

	public RemindersSendingService(TasksRepository tasksRepository,
								   UsersRepository usersRepository,
								   ReminderEmailService reminderEmailService) {
		this.tasksRepository = tasksRepository;
		this.usersRepository = usersRepository;
		this.reminderEmailService = reminderEmailService;
		reminder = reminderEmailService.getReminderEmailDraft();
	}

    public void execute() {
        if (reminder == null) {
            return;
        }
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
        LocalDateTime taskDueDate = getTaskDueDateForEmail(task);
        if (taskDueDate != null) {
            reminder.setTo(usersRepository.getUserById(task.getUserId()).getEmail());
            reminder.setBody("Task: " + task.getDescription() +
                    " is scheduled on <b style='color:red;'>" +
                    taskDueDate + "." + "</b>");
            reminder.send();
        }
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
}

