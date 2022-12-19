package lv.javaguru.java2.tasksScheduler.core.responses;

import lv.javaguru.java2.tasksScheduler.core.domain.Settings;

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
