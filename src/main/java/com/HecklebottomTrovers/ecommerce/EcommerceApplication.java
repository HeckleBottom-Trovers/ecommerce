package com.HecklebottomTrovers.ecommerce;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class EcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

    @Bean
    public CommandLineRunner createAdminAccount(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (userRepository.findByUsername("admin") == null) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("adminpass"));
                admin.setRole("ROLE_ADMIN");
                userRepository.save(admin);
                System.out.println("Admin account created.");
            }

            if (!userRepository.existsByUsername("user")) {
                User defaultUser = new User();
                defaultUser.setUsername("user");
                defaultUser.setPassword(passwordEncoder.encode("password"));
                defaultUser.setRole("ROLE_USER");
                userRepository.save(defaultUser);
                System.out.println("Default user account created.");
            }
        };
    }

}

