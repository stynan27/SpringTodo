package com.seamus.springboot.myfirstwebapp.login;

import org.springframework.stereotype.Service;

@Service // Similar to @Component, but specific to business logic
public class AuthenticationService {
    
    public boolean authenticate(String username, String password) {
        boolean isValidUsername = username.equalsIgnoreCase("seamus");
        boolean isValidPassword = password.equalsIgnoreCase("dummy");

        return isValidUsername && isValidPassword;
    }

}
