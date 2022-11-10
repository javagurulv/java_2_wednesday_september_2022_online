package generalPackage.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "generalPackage")
@PropertySource(value = "classpath:application.properties")
public class AtmAppConfiguration {

}

