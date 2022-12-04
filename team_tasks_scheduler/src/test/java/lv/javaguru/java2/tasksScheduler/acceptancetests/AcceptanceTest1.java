package lv.javaguru.java2.tasksScheduler.acceptancetests;

import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lv.javaguru.java2.tasksScheduler.config.TaskSchedulerConfig;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TaskSchedulerConfig.class})
@Sql({"/schema.sql"})
public class AcceptanceTest1 {
}
