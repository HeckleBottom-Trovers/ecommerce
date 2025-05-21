package com.HecklebottomTrovers.ecommerce.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

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


    // Creates an in-memory user details service with a sample user.
    @Bean
    public UserDetailsService userDetailsService() {
        /*
        UserDetails user =
                User.withDefaultPasswordEncoder()  // Default password encoder for simplicity.
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
        */

        // This is used for actually authenticating registered users
        System.out.println();
        return customUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // Temporary
    }
}
