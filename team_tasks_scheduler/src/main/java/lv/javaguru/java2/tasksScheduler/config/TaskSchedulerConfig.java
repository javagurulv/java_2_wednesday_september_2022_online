package lv.javaguru.java2.tasksScheduler.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "lv.javaguru.java2.tasksScheduler")
@PropertySource(value = "classpath:application.properties")
public class TaskSchedulerConfig {

    @Value("${jdbc.url}")
    private String jdbcUrl;

    @Value("${driverClass}")
    private String driverClass;

    @Value("${database.user.name}")
    private String userName;

    @Value("${database.user.password}")
    private String password;

    @Bean
    public DataSource dataSource() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl(jdbcUrl);
        dataSource.setDriverClassName(driverClass);
        dataSource.setUsername(userName);
        dataSource.setPassword(password);
        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}
