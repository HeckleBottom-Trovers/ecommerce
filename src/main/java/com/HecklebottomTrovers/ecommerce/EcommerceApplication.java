package com.HecklebottomTrovers.ecommerce;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EcommerceApplication {
    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

    @Bean
    CommandLineRunner loadData(ProductRepository repo) {
        return args -> {
            repo.save(new Product(null, "Laptop", 999.99, "High-performance laptop"));
            repo.save(new Product(null, "Headphones", 59.99, "Noise-cancelling headphones"));
        };
    }
}

