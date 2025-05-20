package com.HecklebottomTrovers.ecommerce;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    // Method to retrieve users
    User findByUsername(String username);
}