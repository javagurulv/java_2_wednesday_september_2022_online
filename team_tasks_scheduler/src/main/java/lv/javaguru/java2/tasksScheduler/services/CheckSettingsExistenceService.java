package lv.javaguru.java2.tasksScheduler.services;

import lv.javaguru.java2.tasksScheduler.database.SettingsRepository;

public class CheckSettingsExistenceService {

    private SettingsRepository settingsRepository;

    public CheckSettingsExistenceService(SettingsRepository settingsRepository) {
        this.settingsRepository = settingsRepository;
    }

    public boolean execute() {
        return settingsRepository.recordExists();
    }
}
