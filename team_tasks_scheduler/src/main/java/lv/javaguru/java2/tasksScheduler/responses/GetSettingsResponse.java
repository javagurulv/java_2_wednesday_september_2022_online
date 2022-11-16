package lv.javaguru.java2.tasksScheduler.responses;

import lv.javaguru.java2.tasksScheduler.domain.Settings;

public class GetSettingsResponse extends CoreResponse {
    private Settings settings;

    public GetSettingsResponse(Settings settings) {
        this.settings = settings;
    }

    public Settings getSettings() {
        return settings;
    }
}
