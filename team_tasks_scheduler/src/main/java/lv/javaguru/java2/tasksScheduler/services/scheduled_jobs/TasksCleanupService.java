package lv.javaguru.java2.tasksScheduler.services.scheduled_jobs;

import lv.javaguru.java2.tasksScheduler.database.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.LocalTime;


@Component
public class TasksCleanupService extends Thread{

    @Autowired private TasksRepository tasksRepository;
    @Value("${task.db.scanning.period}")
    private int period; //time in seconds

    public void run() {
        while(true) {
            if (isInterrupted()) {
                return;
            }
            //System.out.println("deleting tasks");
            tasksRepository.deleteByUserIdTillDate(null, LocalDateTime.now().minusDays(1).with(LocalTime.MIN));
            try {
                Thread.sleep(period * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
