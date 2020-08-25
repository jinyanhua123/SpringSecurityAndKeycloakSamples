package com.example.demo;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/hello").hasAuthority("SCOPE_hello")
                .antMatchers(HttpMethod.GET, "/list").hasAuthority("SCOPE_user")
                .antMatchers(HttpMethod.GET, "/").permitAll()
                .anyRequest().authenticated()
                .and()
                .cors()
                .configurationSource(this.corsConfigurationSource());
        http.oauth2ResourceServer()
                .jwt();
    }

    private CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedMethod("GET");
        corsConfiguration.addAllowedOrigin("http://localhost:9020");
        corsConfiguration.addAllowedHeader("Authorization");

        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/", corsConfiguration);
        corsSource.registerCorsConfiguration("/list", corsConfiguration);
        corsSource.registerCorsConfiguration("/hello", corsConfiguration);
        return corsSource;
    }
}