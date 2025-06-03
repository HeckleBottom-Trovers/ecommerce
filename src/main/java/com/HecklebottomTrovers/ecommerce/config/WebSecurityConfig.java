package com.HecklebottomTrovers.ecommerce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

// Importing custom class from project files
import com.HecklebottomTrovers.ecommerce.security.CustomUserDetailsService;

@Configuration  // Configuration class for Spring Web MVC.
@EnableWebSecurity  // Enables Spring Security in the application.
public class WebSecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    // Configures HTTP security settings for the application.
    @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .userDetailsService(customUserDetailsService)
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/", "/signup", "/login", "/css/**", "/js/**", "/images/**").permitAll()
            .anyRequest().authenticated()
        )
        .formLogin(form -> form
            .loginPage("/login")
            .defaultSuccessUrl("/products", true)
            .permitAll()
        )
        .logout(logout -> logout.permitAll());

    return http.build();
}


    @Bean
    public AuthenticationManager authenticationmanager(AuthenticationConfiguration authenticationConfig) throws Exception {
        return authenticationConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // For hashed passwords
    }
}
