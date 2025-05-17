package com.HecklebottomTrovers.ecommerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration  // Configuration class for Spring Web MVC.
@EnableWebSecurity  // Enables Spring Security in the application.
public class WebSecurityConfig {

    // Configures HTTP security settings for the application.
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/").permitAll()  // Allow unauthenticated access to the root URL.
                        .anyRequest().authenticated()  // Require authentication for all other requests.
                )
                .formLogin((form) -> form
                        .loginPage("/login")  // Custom login page.
                        .permitAll()  // Allow everyone to access the login page.
                )
                .logout((logout) -> logout.permitAll());  // Allow everyone to log out.

        return http.build();
    }

    // Creates an in-memory user details service with a sample user.
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()  // Default password encoder for simplicity.
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }
}
