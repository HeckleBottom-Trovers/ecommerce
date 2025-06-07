package com.HecklebottomTrovers.ecommerce;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequestMapping("/admin")
public class AdminController {
    
    private final ProductRepository productRepository;

    public AdminController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // Display all the products for the admin to see
    @GetMapping("/manage")
    public String manageProducts(Model model) {
        model.addAttribute("products", productRepository.findAll());
        model.addAttribute("newProduct", new Product()); // for the add form to bind to
        return "admin-manage";  // admin-manage.html
    }

    // Adding new products
    @PostMapping("/add")
    public String addProduct(@ModelAttribute("newProduct") Product product) {
        productRepository.save(product);
        return "redirect:/admin/manage";
    }

    // Deleting products
    @PostMapping("/delete")
    public String deleteProduct(@RequestParam Long id) {
        productRepository.deleteById(id);
        return "redirect:/admin/manage";
    }
}
