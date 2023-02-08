package com.seamus.springboot.myfirstwebapp.security;

import java.util.function.Function;

import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User; // Proper User class definition! (security.core)
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

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

    // Below to modify existing security chain/sequence of steps
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Ensure ALL requests are authenticated
        http.authorizeHttpRequests(
            auth -> auth.anyRequest().authenticated());
        // Show login form to collect username + password
        http.formLogin(withDefaults());

        // disable cross-site-request-forgery protection (OWASP link: https://owasp.org/www-community/attacks/csrf)
        // allows attacker to trick a user into making a request to the application with malicous parameters.
        http.csrf().disable();
        // disable the X-Frame-Options header, which is a security feature to prevent "clickjacking" 
        // OWASP "clickjacking": https://owasp.org/www-community/attacks/Clickjacking
        // disableing this feature allows the application to be embedded in an iframe
        http.headers().frameOptions().disable();

        return http.build();
    }
}
