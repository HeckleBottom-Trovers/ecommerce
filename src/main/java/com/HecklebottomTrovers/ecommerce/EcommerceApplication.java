package com.HecklebottomTrovers.ecommerce;

import com.HecklebottomTrovers.ecommerce.User;
import com.HecklebottomTrovers.ecommerce.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
				userRepository.save(admin);
				System.out.println("Admin account created.");
			}

			if (!userRepository.existsByUsername("user")) {
				User defaultUser = new User();
				defaultUser.setUsername("user");
				defaultUser.setPassword(passwordEncoder.encode("password"));
				defaultUser.setRole("USER");
				userRepository.save(defaultUser);
				System.out.println("Default user account created.");
			}
		};
	}

	@Bean
	public CommandLineRunner loadSampleProducts(ProductRepository productRepository) {
		return args -> {
			productRepository.deleteAll();
			productRepository.save(new Product(1L, "Gaming Laptop", "High-performance laptop for gaming", 1299.99));
			productRepository.save(new Product(2L, "Wireless Mouse", "Ergonomic mouse with long battery life", 29.99));
			productRepository.save(new Product(3L, "Organic Cotton T-Shirt", "Soft, breathable cotton shirt", 19.99));
			productRepository.save(new Product(4L, "Java Programming Book", "Comprehensive guide to Java programming", 39.99));
			
		};
	};
}

