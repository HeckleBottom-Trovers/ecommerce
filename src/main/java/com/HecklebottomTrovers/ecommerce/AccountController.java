package com.HecklebottomTrovers.ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AccountController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/account")
    public String showAccountPage(Model model, Authentication authentication) {
        String currentUsername = authentication.getName();
        model.addAttribute("currentUsername", currentUsername);
        return "account";
    }

    @PostMapping("/account/update")
    public String updateAccount(@RequestParam String newUsername,
                                @RequestParam String newPassword,
                                Authentication authentication) {
        String currentUsername = authentication.getName();
        User user = userRepository.findByUsername(currentUsername);

        if (user != null) {
            user.setUsername(newUsername);
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
        }

        return "redirect:/products";
    }
}
