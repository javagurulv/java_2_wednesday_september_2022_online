package lv.javaguru.java2.tasksScheduler.services.system;

import lv.javaguru.java2.tasksScheduler.database.SettingsRepository;
import lv.javaguru.java2.tasksScheduler.utils.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ReminderEmailTemplateCreationService {
    @Autowired
    private SettingsRepository settingsRepository;

    @Value("${reminder.email.subject}")
    private String reminderSubject;

    @Value("${reminder.email.body.header}")
    private String bodyHeader;

    @Value("${reminder.email.body.html}")
    private Boolean bodyIsHTML;

    @Value("${reminder.email.body.footer}")
    private String bodyFooter;

    public Email getReminderEmailDraft() {
        if (!settingsRepository.recordExists()) {
            return null;
        }

        String subject = reminderSubject.isBlank() ?
                "Reminder from the Tasks Scheduler application" :
                reminderSubject;

        String header = bodyHeader != null ? bodyHeader : "";
        String footer = bodyFooter != null ? bodyFooter : "";
        boolean htmlFormat = bodyIsHTML != null && bodyIsHTML;

        Email emailDraft = new Email(settingsRepository.getRecord().getEmailFrom(),
                settingsRepository.getRecord().getEmailPassword(),
                settingsRepository.getRecord().getEmailHost(),
                settingsRepository.getRecord().getEmailPort(),
                settingsRepository.getRecord().getEmailProtocol(),
                settingsRepository.getRecord().getEmailFrom(),
                subject,
                header,
                "Undefined content. Please contact Tasks Scheduler support to prevent it in the future.",
                footer,
                htmlFormat);
        return emailDraft;
    }
}
