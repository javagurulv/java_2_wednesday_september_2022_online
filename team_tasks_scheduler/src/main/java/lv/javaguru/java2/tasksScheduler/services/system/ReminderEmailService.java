package lv.javaguru.java2.tasksScheduler.services.system;

import lv.javaguru.java2.tasksScheduler.database.SettingsRepository;
import lv.javaguru.java2.tasksScheduler.utils.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ReminderEmailService {
    @Autowired
    private SettingsRepository settingsRepository;

    @Value("${reminder.email.subject}")
    private String reminderSubject;

    @Value("${reminder.email.body.html}")
    private Boolean bodyHTML;

    public Email getReminderEmailDraft() {
        if (!settingsRepository.recordExists()) {
            return null;
        }

        String subject = reminderSubject.isBlank() ?
                "Reminder from the Tasks Scheduler application" :
                reminderSubject;

        boolean htmlFormat = bodyHTML != null && bodyHTML;

        Email emailDraft = new Email(settingsRepository.getRecord().getEmailFrom(),
                settingsRepository.getRecord().getEmailPassword(),
                settingsRepository.getRecord().getEmailHost(),
                settingsRepository.getRecord().getEmailPort(),
                settingsRepository.getRecord().getEmailProtocol(),
                settingsRepository.getRecord().getEmailFrom(),
                subject,
                "Undefined content. Please contact Tasks Scheduler support to prevent it in the future.",
                htmlFormat);
        return emailDraft;
    }
}
