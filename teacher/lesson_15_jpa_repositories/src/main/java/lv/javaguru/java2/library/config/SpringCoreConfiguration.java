package lv.javaguru.java2.library.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = "lv.javaguru.java2.library.core")
@PropertySource(value = "classpath:application.properties")
@EnableTransactionManagement
@EntityScan(basePackages = "lv.javaguru.java2.library.core.domain")
@EnableJpaRepositories(value = "lv.javaguru.java2.library.core.database.jpa")
public class SpringCoreConfiguration {

}