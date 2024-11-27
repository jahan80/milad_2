package com.jahan.sp1_.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class SecurityConfig {

    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        logger.info("Configuring security filter chain");
        http
                .authorizeHttpRequests()
                .requestMatchers("/admin/**").authenticated()
                .anyRequest().permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
        logger.info("Security filter chain configured successfully");
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        logger.info("Configuring in-memory user details");
        return new InMemoryUserDetailsManager(
                User.withUsername("admin")
                        .password(passwordEncoder().encode("admin"))
                        .roles("ADMIN")
                        .build()
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        logger.info("Configuring password encoder");
        return new BCryptPasswordEncoder();
    }
}
