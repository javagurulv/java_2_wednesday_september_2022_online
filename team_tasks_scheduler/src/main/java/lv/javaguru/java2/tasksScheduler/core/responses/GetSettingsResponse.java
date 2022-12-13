package lv.javaguru.java2.tasksScheduler.core.responses;

import lv.javaguru.java2.tasksScheduler.core.domain.Settings;

public class GetSettingsResponse extends CoreResponse {
    private Settings settings;

    public GetSettingsResponse(Settings settings) {
        this.settings = settings;
    }

    public Settings getSettings() {
        return settings;
    }
}
