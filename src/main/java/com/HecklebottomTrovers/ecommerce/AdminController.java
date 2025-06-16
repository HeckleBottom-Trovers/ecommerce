package com.HecklebottomTrovers.ecommerce;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    public String addProduct(@ModelAttribute("newProduct") Product product, @RequestParam("productImageFile") MultipartFile productImageFile) throws IOException {
        // Saving image to project files
        String fileDirectory = new File("src/main/resources/static/images").getAbsolutePath();
        File saveProductImageFile = new File(fileDirectory, productImageFile.getOriginalFilename());
        productImageFile.transferTo(saveProductImageFile); // actually placing retrieved image file contents into the correct project folder

        // Setting the new Product's image file path (image name)
        product.setImageFilePath(productImageFile.getOriginalFilename());

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
