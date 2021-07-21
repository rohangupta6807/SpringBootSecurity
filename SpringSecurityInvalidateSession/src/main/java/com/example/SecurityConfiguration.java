package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    @Autowired
    private CustomInvalidateSessionStrategy customInvalidateSessionStrategy;

    // For Authentication
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {


        // For in-memory
/*        auth.inMemoryAuthentication()
                .withUser("rohan1")
                .password("rohan1")
                .roles("role1")
                .and()
                .withUser("rohan2")
                .password("rohan2")
                .roles("user")
                .and()
                .withUser("rohan3")
                .password("rohan3")
                .roles("admin");
*/

        // for custom DB
        auth.userDetailsService(customUserDetailsService);
    }

    // Created Bean for get type of Encryption used for storing the password
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


    // For Authorization
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/admin").hasRole("admin")
                .antMatchers("/user").hasAnyRole("admin", "user")
                .antMatchers("/").permitAll()
                .and().formLogin();
        http
                .sessionManagement()
                .invalidSessionStrategy(customInvalidateSessionStrategy);

    }
}
