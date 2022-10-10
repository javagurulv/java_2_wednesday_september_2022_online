package lv.javaguru.java2.cookingApp.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "lv.javaguru.java2.cookingApp")
@PropertySource(value = "classpath:application.properties")
public class CookingAppConfiguration {
}


