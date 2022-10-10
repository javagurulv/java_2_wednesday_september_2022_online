package lv.javaguru.java2.tasksScheduler.responses;

import lv.javaguru.java2.tasksScheduler.domain.Settings;

import java.util.List;

public class AmendSettingsResponse extends CoreResponse {

    private Settings settings;

    public AmendSettingsResponse(List<CoreError> errors) {
        super(errors);
    }

    public AmendSettingsResponse(Settings settings) {
        this.settings = settings;
    }

    public Settings getSettings() {
        return settings;
    }
}
