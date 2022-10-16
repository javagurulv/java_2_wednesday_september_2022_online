package lv.javaguru.java2.library.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@ComponentScan(basePackages = "lv.javaguru.java2.library")
@PropertySource(value = "classpath:application.properties")
public class BookListConfiguration {

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
