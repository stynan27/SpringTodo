package com.seamus.springboot.myfirstwebapp.security;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User; // Proper User class definition! (security.core)
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration // Create beans from this class
public class SpringSecurityConfiguration {
    //LDAP or Database
    //In Memory

    // InMemoryUserDetailsManager 
    // InMemoryUserDetailsManager(UserDetails... users);

    @Bean
    public InMemoryUserDetailsManager createUserDetailsManager() {
        UserDetails userDetails1 = createNewUser("seamus", "dummy");
        UserDetails userDetails2 = createNewUser("john", "halo");
        
        return new InMemoryUserDetailsManager(userDetails1, userDetails2);
    }

    private UserDetails createNewUser(String username, String password) {
        Function<String, String> PasswordEncoder
        = input -> passwordEncoder().encode(input);
        UserDetails userDetails = User.builder()
            .passwordEncoder(PasswordEncoder)
            .username(username)
            .password(password)
            .roles("USER", "ADMIN")
            .build();
        return userDetails;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
