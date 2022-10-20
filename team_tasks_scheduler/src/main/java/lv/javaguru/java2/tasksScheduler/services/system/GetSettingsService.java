package lv.javaguru.java2.tasksScheduler.services.system;

import lv.javaguru.java2.tasksScheduler.database.SettingsRepository;
import lv.javaguru.java2.tasksScheduler.database.UsersRepository;


import lv.javaguru.java2.tasksScheduler.domain.Settings;
import lv.javaguru.java2.tasksScheduler.domain.User;
import lv.javaguru.java2.tasksScheduler.requests.GetCurrentUserRequest;
import lv.javaguru.java2.tasksScheduler.requests.GetSettingsRequest;
import lv.javaguru.java2.tasksScheduler.responses.GetCurrentUserResponse;
import lv.javaguru.java2.tasksScheduler.responses.GetSettingsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GetSettingsService {

    @Autowired
    private SettingsRepository settingsRepository;

    public GetSettingsResponse execute(GetSettingsRequest request) {
        return new GetSettingsResponse(settingsRepository.getRecord());
    }
}
