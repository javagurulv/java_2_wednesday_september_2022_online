package myApp.web_ui.security;

import myApp.core.database.UserRepository;
import myApp.core.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .authorizeRequests().antMatchers("/login").permitAll()
                .and()
                .formLogin()
                    .loginProcessingUrl("/login")
                    .successHandler(myAuthenticationSuccessHandler())
                    .permitAll();
    }
    @Bean //@Autowired
    public InMemoryUserDetailsManager configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        /*
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select login,password,role from users" +
                        " where login = ?")
                .authoritiesByUsernameQuery("select role from users " +
                        "where login = ?");
        /*
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .usersByUsernameQuery("select users.* from users where username=?")
                .authoritiesByUsernameQuery("select bank_accounts.*, users.* from bank_accounts inner join users " +
                        "on bank_accounts.user_id = users.id where user.id = bank_accounts.id;");

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .usersByUsernameQuery("select username, password from users where username=?")
                .authoritiesByUsernameQuery("select users.*  bank_accounts.* from bank_accounts " +
                        " inner join users on bank_accounts.user_id = users.user_id Where bank_accounts.id = users.user_id");


        UserDetails user = org.springframework.security.core.userdetails.User.withUsername("u")
                .password("{noop}u")
                .authorities("Role_User")
                .build();

         */
        List<User> users = userRepository.getAllUsers();
        List<UserDetails> userDetailsList = new ArrayList<>();
            for (User user1 : users) {
                UserDetails build = org.springframework.security.core.userdetails.User.withUsername(user1.getPersonalCode())
                        .password(user1.getPassword())
                        .authorities(user1.getRole())
                        .build();
                userDetailsList.add(build);
        }
        return new InMemoryUserDetailsManager(userDetailsList);
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler(){
        return new MySimpleUrlAuthenticationSuccessHandler();
    }
}