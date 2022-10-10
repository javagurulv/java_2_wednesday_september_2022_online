package lv.javaguru.java2.tasksScheduler.responses;

import lv.javaguru.java2.tasksScheduler.domain.Settings;
import lv.javaguru.java2.tasksScheduler.domain.User;

import java.util.List;

public class SettingsLoginResponse extends CoreResponse {
    Settings settings;
    public SettingsLoginResponse(List<CoreError> errors) {
        super(errors);
    }
    public SettingsLoginResponse(Settings settings) {
        this.settings = settings;
    }
    public Settings getSettings() {
        return settings;
    }
}
