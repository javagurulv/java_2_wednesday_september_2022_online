package lv.javaguru.java2.tasksScheduler.core.database;


import lv.javaguru.java2.tasksScheduler.core.domain.Settings;

import java.util.ArrayList;
import java.util.List;

//@Component
public class InMemorySettingsRepository implements SettingsRepository {

    private List<Settings> settingsRecord = new ArrayList<>();

    @Override
    public boolean save(Settings settings) {
        if (settings == null)
            return false;
        if (settingsRecord == null || settingsRecord.isEmpty())
            settingsRecord.add(settings);
        else
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
