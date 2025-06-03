package com.HecklebottomTrovers.ecommerce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public String showProducts(Model model) {
        // existing logic
        return "products-view";
    }

    @GetMapping("/manage")
    public String manageProducts(Model model, Authentication authentication) {
        // existing logic
        return "manage-products";
    }

    @PostMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable Long id, Authentication authentication) {
        // existing logic
        return "redirect:/manage";
    }

    // ✅ Your new cart route
    @GetMapping("/cart")
    public String viewCart() {
        return "cart";
    }
}
