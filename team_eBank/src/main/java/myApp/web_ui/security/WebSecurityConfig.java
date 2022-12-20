package myApp.web_ui.security;

import myApp.core.database.jpa.JpaUserRepository;
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

import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JpaUserRepository userRepository;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                    .antMatchers("/login").permitAll()
                .and()
                    .formLogin()
                    .loginProcessingUrl("/login")
                    .successHandler(myAuthenticationSuccessHandler())
                .permitAll();
    }

    @Bean
    public InMemoryUserDetailsManager configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        List<User> users = userRepository.findAll();
        List<UserDetails> userDetailsList = users.stream()
                .map(user1 -> org.springframework.security.core.userdetails.User.withUsername(user1.getPersonalCode())
                        .password(user1.getPassword())
                        .authorities(user1.getRole())
                        .build()).collect(Collectors.toList());
        return new InMemoryUserDetailsManager(userDetailsList);
    }

    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
        return new MySimpleUrlAuthenticationSuccessHandler();
    }
}