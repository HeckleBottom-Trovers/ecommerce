package com.HecklebottomTrovers.ecommerce.security;

import com.HecklebottomTrovers.ecommerce.User;
import com.HecklebottomTrovers.ecommerce.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("Attempting login with: " + username);
        User user = userRepository.findByUsername(username);

        // Cannot find user with matching username
        if(user == null) {
            throw new UsernameNotFoundException("User not found!");
        }

        System.out.println("Found user: " + user.getUsername());
        return new CustomUserDetails(user);
    }


}
