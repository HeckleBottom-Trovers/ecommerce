package com.HecklebottomTrovers.ecommerce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Configuration class for Spring Web MVC.
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("redirect:/products"); // Redirect root to /products.
        registry.addViewController("/products").setViewName("products-view"); // Map /products to the products-view.
        registry.addViewController("/login").setViewName("login"); // Map /login to the login view.
    }
}
