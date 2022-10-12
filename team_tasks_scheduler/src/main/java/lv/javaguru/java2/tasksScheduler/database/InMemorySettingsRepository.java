package lv.javaguru.java2.tasksScheduler.database;

import lv.javaguru.java2.tasksScheduler.domain.Settings;
import lv.javaguru.java2.tasksScheduler.utils.Encryption;

import java.util.ArrayList;
import java.util.List;

public class InMemorySettingsRepository implements SettingsRepository {

    private List<Settings> settingsRecord = new ArrayList<>();

    {
        //TODO remove me
        save(new Settings(Encryption.stringHashing("admin"), "olegsktest@gmail.com",
                "glzblubwocovtifc", "smtp.gmail.com", "465",
                "ssl"));
    }

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
