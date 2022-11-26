package lv.javaguru.java2.tasksScheduler.services.scheduled_jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledFuture;

@Component
public class ScheduledJobs {
    private ThreadPoolTaskScheduler taskScheduler;
    private CronTrigger cronTrigger;
    private ScheduledFuture taskCleanupFuture;

    @Autowired
    TasksCleanupJob tasksCleanupService;

    @Autowired
    public ScheduledJobs() {
        this.taskScheduler = threadPoolTaskScheduler();
        this.cronTrigger = cronTrigger();
    }

    public void stopAllJobs() {
        this.taskScheduler.shutdown();
    }

    public void start() {
        taskCleanupFuture = this.taskScheduler.schedule(tasksCleanupService, cronTrigger);
    }

    public void stop(){
        this.taskCleanupFuture.cancel(true);
    }
    private ThreadPoolTaskScheduler threadPoolTaskScheduler(){
        ThreadPoolTaskScheduler threadPoolTaskScheduler = new ThreadPoolTaskScheduler();
        threadPoolTaskScheduler.setPoolSize(3);
        threadPoolTaskScheduler.setThreadNamePrefix("ThreadPoolTaskScheduler");
        threadPoolTaskScheduler.initialize();
        return threadPoolTaskScheduler;
    }

    private CronTrigger cronTrigger() {
        return new CronTrigger("*/40 * * * * ?");
    }

    //ThreadPoolTaskScheduler taskScheduler =
    //        (ThreadPoolTaskScheduler) applicationContext.getBean("threadPoolTaskScheduler");
    //@Bean
    // ThreadPoolTaskScheduler threadPoolTaskScheduler() {
    //}


}
