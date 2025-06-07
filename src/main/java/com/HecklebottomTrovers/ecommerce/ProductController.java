package com.HecklebottomTrovers.ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import com.HecklebottomTrovers.ecommerce.security.CustomUserDetails;


import java.util.List;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public String showProducts(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("Logged in authorities: " + auth.getAuthorities());

        System.out.println("[DEBUG] Accessing /products");

        List<Product> products = productRepository.findAll();

        if (products.isEmpty()) {
            Product test = new Product();
            test.setDescription("Test GPU");
            test.setPrice(199.99);
            productRepository.save(test);
            products = productRepository.findAll();
            System.out.println("[DEBUG] Fallback test product inserted");
        }

        model.addAttribute("products", products);
        return "products-view";
    }

    @GetMapping("/manage")
    public String manageProducts(Model model, Authentication authentication) {
        System.out.println("[DEBUG] Accessing /manage");

        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            if ("ADMIN".equals(userDetails.getRole())) {
                model.addAttribute("products", productRepository.findAll());
                return "manage-products";
            }
        }
        return "redirect:/products";
    }

    // ✅ Cart route placeholder
    @GetMapping("/cart")
    public String viewCart() {
        return "cart";
    }
}

