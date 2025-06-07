package com.HecklebottomTrovers.ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthenticationController {

    private final SecurityFilterChain securityFilterChain;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    AuthenticationController(SecurityFilterChain securityFilterChain) {
        this.securityFilterChain = securityFilterChain;
    }

    @GetMapping("/signup")
    public String showSignupPage(Model model) {
        model.addAttribute("user", new User()); // adds new empty User object to model, required by Thymeleaf to bind form fields
        return "signup"; // this looks for signup.html in /templates
    }

    @PostMapping("/signup")
    public String registerUser(Model model, @RequestParam String username, @RequestParam String password, @RequestParam String confirmPassword) {
        User user = new User();
        user.setUsername(username);

        // Check password confirmation
        if(!password.equals(confirmPassword)) {
            // Check password requirements
            System.out.println("passwords don't match!!!");
            model.addAttribute("matchingPasswordError", "Passwords don't match! Please try again.");
            return "signup";
        } else if (password.length() < 8 || !password.matches(".*\\d.*") || !password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
            System.out.println("passwords don't meet requirements!!!");
            model.addAttribute("passwordRequirementError", "Passwords must meet requirements!");
            return "signup";
        }

        // Assign USER role
        user.setRole("ROLE_USER");

        // Encrypt password
        user.setPassword(passwordEncoder.encode(password));

        // Save user
        userRepository.save(user);

        return "redirect:/login";
    }
}