package lv.javaguru.java2.rentapp.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "lv.javaguru.java2.rentapp")
@PropertySource(value = "classpath:application.properties")
@EnableTransactionManagement
public class VehicleRentConfiguration {

    @Value("${jdbc.url}")
    private String jdbcUrl;

    @Value("${driverClass}")
    private String driverClass;

    @Value("${rentAppDatabase.user.name}")
    private String userName;

    @Value("${rentAppDatabase.user.password}")
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

    @Bean
    public SimpleJdbcInsert simpleJdbcInsert(JdbcTemplate jdbcTemplate) {
        return new SimpleJdbcInsert(jdbcTemplate);
    }

    @Bean
    public PlatformTransactionManager txManager() {
        return new DataSourceTransactionManager(dataSource());
    }

}
