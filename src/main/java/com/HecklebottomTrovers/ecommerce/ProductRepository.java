package com.HecklebottomTrovers.ecommerce;

import com.HecklebottomTrovers.ecommerce.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {}
