package com.seamus.springboot.myfirstwebapp.login;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name") // persist name attribute from model to session 
public class WelcomeController {

    // map login to the following GET request method
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String goToWelcomePage(ModelMap model) {
        model.put("name", getLoggedinUsername());
        return "welcome";
    }

    private String getLoggedinUsername() {
        // Get Currently authenticated User (Principle)
        Authentication authentication =
            SecurityContextHolder.getContext().getAuthentication();
        // return Username
        return authentication.getName();
    }
}
