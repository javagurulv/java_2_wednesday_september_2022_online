package lv.javaguru.java2.repo_men_inc.integration_tests;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lv.javaguru.java2.repo_men_inc.config.RepoMenIncConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RepoMenIncConfiguration.class})
public class SpringContextTest {

    @Autowired private ApplicationContext appContext;

    @Test
    public void start() {
        assertNotNull(appContext);
    }

}
