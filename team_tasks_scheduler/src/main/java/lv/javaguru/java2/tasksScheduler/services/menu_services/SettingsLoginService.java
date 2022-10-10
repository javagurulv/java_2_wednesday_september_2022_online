package lv.javaguru.java2.tasksScheduler.services.menu_services;

import lv.javaguru.java2.tasksScheduler.database.SettingsRepository;
import lv.javaguru.java2.tasksScheduler.database.TasksRepository;
import lv.javaguru.java2.tasksScheduler.database.UsersRepository;
import lv.javaguru.java2.tasksScheduler.domain.Settings;
import lv.javaguru.java2.tasksScheduler.domain.User;
import lv.javaguru.java2.tasksScheduler.requests.LoginRequest;
import lv.javaguru.java2.tasksScheduler.requests.SettingsLoginRequest;
import lv.javaguru.java2.tasksScheduler.responses.AddSettingsResponse;
import lv.javaguru.java2.tasksScheduler.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.responses.LoginResponse;
import lv.javaguru.java2.tasksScheduler.responses.SettingsLoginResponse;
import lv.javaguru.java2.tasksScheduler.services.system.SessionService;
import lv.javaguru.java2.tasksScheduler.services.validators.SettingsLoginValidator;
import lv.javaguru.java2.tasksScheduler.utils.Encryption;

import java.util.ArrayList;
import java.util.List;

public class SettingsLoginService {

    private SettingsRepository settingsRepository;
    private SettingsLoginValidator validator;
    private SessionService sessionService;

    public SettingsLoginService(SettingsRepository settingsRepository, SettingsLoginValidator validator, SessionService sessionService) {
        this.settingsRepository = settingsRepository;
        this.validator = validator;
        this.sessionService = sessionService;
    }

    public SettingsLoginResponse execute(SettingsLoginRequest request) {

        List<CoreError> errors = validator.validate(request, settingsRepository);
        if (!errors.isEmpty()) {
            return new SettingsLoginResponse(errors);
        }

        Settings settings = settingsRepository.getRecord();
        sessionService.login(0L, request.getAdminPassword());
        return new SettingsLoginResponse(settings);
    }
}