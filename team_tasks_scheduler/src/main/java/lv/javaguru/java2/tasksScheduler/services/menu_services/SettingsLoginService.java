package lv.javaguru.java2.tasksScheduler.services.menu_services;

import lv.javaguru.java2.tasksScheduler.database.SettingsRepository;
import lv.javaguru.java2.tasksScheduler.database.TasksRepository;
import lv.javaguru.java2.tasksScheduler.database.UsersRepository;


import lv.javaguru.java2.tasksScheduler.domain.Settings;
import lv.javaguru.java2.tasksScheduler.domain.User;
import lv.javaguru.java2.tasksScheduler.requests.GetSettingsRequest;
import lv.javaguru.java2.tasksScheduler.requests.LoginRequest;
import lv.javaguru.java2.tasksScheduler.requests.SettingsLoginRequest;
import lv.javaguru.java2.tasksScheduler.responses.*;
import lv.javaguru.java2.tasksScheduler.services.scheduled_jobs.RemindersSendingService;
import lv.javaguru.java2.tasksScheduler.services.system.GetSettingsService;
import lv.javaguru.java2.tasksScheduler.services.system.ReminderEmailService;
import lv.javaguru.java2.tasksScheduler.services.system.SessionService;
import lv.javaguru.java2.tasksScheduler.services.validators.SettingsLoginValidator;
import lv.javaguru.java2.tasksScheduler.utils.Encryption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SettingsLoginService {

    @Autowired
    private SettingsRepository settingsRepository;
    @Autowired private SettingsLoginValidator validator;
    @Autowired private SessionService sessionService;
    @Autowired private GetSettingsService getSettingsService;


    // TODO below is for reminders testing
    @Autowired private TasksRepository tasksRepository;
    @Autowired private UsersRepository usersRepository;
    @Autowired private ReminderEmailService reminderEmailService;

    public SettingsLoginResponse execute(SettingsLoginRequest request) {

        List<CoreError> errors = validator.validate(request, settingsRepository);
        if (!errors.isEmpty()) {
            return new SettingsLoginResponse(errors);
        }

        sessionService.login(0L, request.getAdminPassword());
        GetSettingsResponse getSettingsResponse = getSettingsService.execute(new GetSettingsRequest(true));
        Settings settings = getSettingsResponse.getSettings();

        // TODO below is for reminders testing
        RemindersSendingService remindersSendingService = new RemindersSendingService(
                tasksRepository, usersRepository, reminderEmailService);
        remindersSendingService.execute();

        return new SettingsLoginResponse(settings);
    }
}
