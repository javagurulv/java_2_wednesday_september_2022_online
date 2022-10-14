package lv.javaguru.java2.library.config;

import javax.sql.DataSource;

import java.io.IOException;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan(basePackages = "lv.javaguru.java2.library")
@PropertySource(value = "classpath:application.properties")
@EnableTransactionManagement
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

	@Bean
	public Properties hibernateProperties(
			@Value("${hibernate.show_sql}") Boolean showSql,
			@Value("${hibernate.hbm2ddl.auto}") String hbm2ddl,
			@Value("${hibernate.dialect}") String dialect) {
		Properties properties = new Properties();
		properties.put("hibernate.show_sql", showSql);
		properties.put("hibernate.hbm2ddl.auto", hbm2ddl);
		properties.put("hibernate.dialect", dialect);
		return properties;
	}

	@Bean
	public SessionFactory sessionFactory(DataSource dataSource,
										 @Value("${hibernate.packagesToScan}") String packagesToScan,
										 Properties hibernateProperties
	) throws IOException {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setPackagesToScan(packagesToScan);
		sessionFactoryBean.setDataSource(dataSource);
		sessionFactoryBean.setHibernateProperties(hibernateProperties);
		sessionFactoryBean.afterPropertiesSet();
		return sessionFactoryBean.getObject();
	}

	@Bean
	public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
		return new HibernateTransactionManager(sessionFactory);
	}

}
