package com.example.demo;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/hello").hasAuthority("SCOPE_hello")
                .antMatchers(HttpMethod.GET, "/list").hasAuthority("SCOPE_user")
                .antMatchers(HttpMethod.GET, "/").permitAll()
                .anyRequest().authenticated();
        http.oauth2ResourceServer()
                .jwt();
    }
}