package lv.javaguru.java2.tasksScheduler.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

@Configuration
@ComponentScan(basePackages = "lv.javaguru.java2.tasksScheduler")
@PropertySource(value = "classpath:application.properties")
public class TaskSchedulerConfig {

}
