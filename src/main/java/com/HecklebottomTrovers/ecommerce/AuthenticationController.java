package com.HecklebottomTrovers.ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/signup")
    public String showSignupPage(Model model) {
        model.addAttribute("user", new User()); // adds new empty User object to model, required by Thymeleaf to bind form fields
        return "signup"; // this looks for signup.html in /templates
    }

    @PostMapping("/signup")
    public String registerUser(@RequestParam String username, @RequestParam String password) {
        User user = new User();
        user.setUsername(username);
        // Will need to implement password encoder for security purposes
        // Then figure out how to decode the password from the database
        user.setPassword(password);
        userRepository.save(user);

        return "redirect:/login";
    }
}