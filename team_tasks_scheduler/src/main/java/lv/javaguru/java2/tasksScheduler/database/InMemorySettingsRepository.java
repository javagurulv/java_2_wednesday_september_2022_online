package lv.javaguru.java2.tasksScheduler.database;

import lv.javaguru.java2.tasksScheduler.domain.Settings;

import java.util.ArrayList;
import java.util.List;

public class InMemorySettingsRepository implements SettingsRepository {

    private List<Settings> settingsRecord = new ArrayList<>();

    @Override
    public boolean save(Settings settings) {
        if (settings == null)
            return false;
        settingsRecord.add(settings);
        return true;
    }

    @Override
    public boolean update(Settings settings) {
        if (settings == null || settingsRecord == null || settingsRecord.isEmpty())
            return false;
        settingsRecord.set(0, settings);
        return true;
    }

    @Override
    public boolean recordExists() {
        return settingsRecord != null && !settingsRecord.isEmpty();
    }

    @Override
    public boolean passwordIsValid(String password) {
        return settingsRecord.get(0).getAdminPassword().equals(password);
    }

    @Override
    public Settings getRecord() {
        return settingsRecord.get(0);
    }
}
