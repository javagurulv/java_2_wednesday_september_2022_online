package lv.javaguru.java2.tasksScheduler.services.system;

import lv.javaguru.java2.tasksScheduler.database.SettingsRepository;
import lv.javaguru.java2.tasksScheduler.utils.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReminderEmailService {
    @Autowired
    private SettingsRepository settingsRepository;

    public Email getReminderEmailDraft() {
        if (!settingsRepository.recordExists()) {
            return null;
        }
        Email emailDraft = new Email(settingsRepository.getRecord().getEmailFrom(),
                settingsRepository.getRecord().getEmailPassword(),
                settingsRepository.getRecord().getEmailHost(),
                settingsRepository.getRecord().getEmailPort(),
                settingsRepository.getRecord().getEmailProtocol(),
                settingsRepository.getRecord().getEmailFrom(),
                "Reminder from the Tasks Scheduler application",
                "Task: UNKNOWN is scheduled on <b style='color:red;'> UNKNOWN DATE.</b>",
                true);
        return emailDraft;
    }
}
