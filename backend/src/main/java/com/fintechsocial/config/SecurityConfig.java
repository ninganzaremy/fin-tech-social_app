package com.fintechsocial.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new Argon2PasswordEncoder();  // Using Argon2 for more security
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .headers()
        .contentSecurityPolicy("script-src 'self'; report-uri /csp-report-endpoint/") // Content Security Policy
        .and()
        .frameOptions().disable() // for H2
        .and()
        .authorizeRequests()
        .antMatchers("/api/users/register", "/h2-console/**").permitAll() // Allow registration & H2 console without authentication
        .antMatchers("/actuator/**").hasRole("ADMIN") // Restrict access to actuator endpoints
        .anyRequest().authenticated()
        .and()
        .formLogin()
        .permitAll()
        .and()
        .logout()
        .permitAll()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
        .maximumSessions(1)
        .expiredUrl("/session-expired-url");
  }
}