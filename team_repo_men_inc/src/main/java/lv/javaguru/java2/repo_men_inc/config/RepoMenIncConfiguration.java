package lv.javaguru.java2.repo_men_inc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "lv.javaguru.java2.repo_men_inc")
@PropertySource(value = "classpath:application.properties")
public class RepoMenIncConfiguration {
}
