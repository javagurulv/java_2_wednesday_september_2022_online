package lv.javaguru.java2.tasksScheduler.core.responses;

import lv.javaguru.java2.tasksScheduler.core.domain.Settings;

import java.util.List;

public class AddSettingsResponse extends CoreResponse {

    private Settings settings;

    public AddSettingsResponse(List<CoreError> errors) {
        super(errors);
    }

    public AddSettingsResponse(Settings settings) {
        this.settings = settings;
    }

    public Settings getSettings() {
        return settings;
    }
}
