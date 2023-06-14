package lv.javaguru.java2.tasksScheduler.core.services.scheduled_jobs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledFuture;

@Component
public class ScheduledJobs {
    private ThreadPoolTaskScheduler taskScheduler;
//    private CronTrigger cronTrigger;
    private CronTrigger cronTriggerTaskCleanup;
    private CronTrigger cronTriggerDueDateUpdate;
    private CronTrigger cronTriggerReminderSending;
    private ScheduledFuture taskCleanupFuture;
    private ScheduledFuture dueDateUpdateFuture;
    private ScheduledFuture reminderSendingFuture;

//    @Value("${job.tasks.cleanup.cron.trigger}")
//    private String cleanupCron;
//
//    @Value("${job.tasks.date.update.cron.trigger}")
//    private String dateUpdateCron;
//
//    @Value("${job.tasks.reminder.cron.trigger}")
//    private String reminderCron;

    @Autowired
    TasksCleanupJob tasksCleanupServiceJob;
    @Autowired
    DueDatesUpdateJob dueDatesUpdateServiceJob;
    @Autowired
    RemindersSendingJob reminderSendingServiceJob;

    @Autowired
    public ScheduledJobs() {
        this.taskScheduler = threadPoolTaskScheduler();
//        this.cronTrigger = cronTrigger();
        this.cronTriggerTaskCleanup = getTaskCleanupCronTrigger();
        this.cronTriggerDueDateUpdate = getDueDateUpdateCronTrigger();
        this.cronTriggerReminderSending = getReminderSendingCronTrigger();
    }

    public void stopAllJobs() {
        this.taskScheduler.shutdown();
    }

    public void start() {
        taskCleanupFuture = this.taskScheduler.schedule(tasksCleanupServiceJob, cronTriggerTaskCleanup);
        dueDateUpdateFuture = taskScheduler.schedule(dueDatesUpdateServiceJob, cronTriggerDueDateUpdate);
        reminderSendingFuture = taskScheduler.schedule(reminderSendingServiceJob, cronTriggerReminderSending);
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

//    private CronTrigger cronTrigger() {
//        return new CronTrigger("*/40 * * * * ?");
//    }

    private CronTrigger getTaskCleanupCronTrigger() {
//        return new CronTrigger(cleanupCron);
        return new CronTrigger("*/25 * * * * ?");
    }

    private CronTrigger getDueDateUpdateCronTrigger() {
//        return new CronTrigger(dateUpdateCron);
        return new CronTrigger("*/30 * * * * ?");
    }

    private CronTrigger getReminderSendingCronTrigger() {
//        return new CronTrigger(reminderCron);
        return new CronTrigger("*/40 * * * * ?");
    }

    //ThreadPoolTaskScheduler taskScheduler =
    //        (ThreadPoolTaskScheduler) applicationContext.getBean("threadPoolTaskScheduler");
    //@Bean
    // ThreadPoolTaskScheduler threadPoolTaskScheduler() {
    //}


}
