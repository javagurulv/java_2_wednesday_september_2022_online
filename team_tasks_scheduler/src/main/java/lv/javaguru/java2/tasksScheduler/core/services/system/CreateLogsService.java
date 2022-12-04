package lv.javaguru.java2.tasksScheduler.core.services.system;

import lv.javaguru.java2.tasksScheduler.utils.ValueChecking;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

@Component
public class CreateLogsService {

    @Value("${logs.directory.path}")
    private String logDirectoryPath;

    public void execute(String record) {
         if (ValueChecking.stringIsEmpty(logDirectoryPath)) {
             return;
         }
         String filePath = getLogFilePath();
        if (filePath == null) {
            return;
        }
        addRecordToLogFile(filePath, record);
    }

    private String getLogFilePath() {
        if (ValueChecking.stringIsEmpty(logDirectoryPath)) {
            return null;
        }
        String path = (logDirectoryPath.charAt(logDirectoryPath.length() - 1) == '/') ?
                logDirectoryPath : logDirectoryPath + "/";
        path += "log_" + LocalDateTime.now().getYear() +
                "_" + LocalDateTime.now().getMonth().getValue() + ".txt";
        return path;
    }

    private void addRecordToLogFile(String path, String record) {
        record+="\r\n";
        File file = new File(path);
        file.getParentFile().mkdirs();
        try {
            file.createNewFile(); // if file already exists will do nothing
            FileOutputStream fileOutputStream = new FileOutputStream(file, true);
            fileOutputStream.write(record.getBytes());
            fileOutputStream.close();
        } catch (IOException e) {
            return;
        }
    }
}
