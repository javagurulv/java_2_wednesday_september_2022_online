package lv.javaguru.java2.cookingApp.integrationtests;

import lv.javaguru.java2.cookingApp.config.SpringCoreConfiguration;
import lv.javaguru.java2.cookingApp.web_ui.config.SpringWebConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {SpringWebConfiguration.class})
public class SpringContextTest {

    private ConfigurableApplicationContext context;

    @BeforeEach
    void setup() {
        context = SpringApplication.run(SpringWebConfiguration.class);
    }

    @Test
    public void start() {
        assertNotNull(context);
    }


}
