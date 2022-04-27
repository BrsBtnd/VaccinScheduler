package com.example.vaccinescheduler.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors()
                .and()
                .csrf().disable();
        http.authorizeRequests()
                //.mvcMatchers("/api/users/**").permitAll()
                    .antMatchers("/api/centers/**").permitAll()
                    .antMatchers(HttpMethod.POST,"/api/users").permitAll()
                    .antMatchers(HttpMethod.POST,"/api/userSchedules").permitAll()
                    .anyRequest().authenticated()
                .and()
                    .oauth2ResourceServer()
                    .jwt();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
