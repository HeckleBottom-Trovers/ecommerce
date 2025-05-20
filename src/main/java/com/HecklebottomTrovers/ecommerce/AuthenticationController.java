package com.HecklebottomTrovers.ecommerce;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {


    @GetMapping("/signup")
    public String showSignupPage() {
    return "signup"; // this looks for signup.html in /templates
    }
}