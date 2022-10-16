package myApp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "myApp")
@PropertySource(value = "classpath:application.properties")
public class BankAccountConfiguration {
}
