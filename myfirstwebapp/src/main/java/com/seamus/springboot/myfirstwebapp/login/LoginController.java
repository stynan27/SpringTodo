package com.seamus.springboot.myfirstwebapp.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name") // persist name attribute from model to session 
public class LoginController {

    // make use of slf4j logger
    //private Logger logger = LoggerFactory.getLogger(getClass());


    private AuthenticationService authenticationService;

    // /login => login.jsp
    //@RequestMapping("login")
    //public String goToLoginPage(@RequestParam String name, ModelMap model) { // allow URL request param i.e ?name=
        //model.put("name", name);
        //System.out.println(name);  // BAD PRACTICE
        // logger.debug("Request param is {}", name);
        // logger.info("Request param is {}", name);
    //}
    
    public LoginController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    // map login to the following GET request method
    @RequestMapping(value="login", method=RequestMethod.GET)
    public String goToLoginPage() {
        return "login";
    }

    // map welcome page to the following POST request method
    @RequestMapping(value="login", method=RequestMethod.POST)
    public String goToWelcomePage(@RequestParam String name,
        @RequestParam String password, ModelMap model) {
        
            // Basic auth handled in AuthenticationService (Business logic)
        if (authenticationService.authenticate(name, password)) {
            model.put("name", name);
            //model.put("password", password); UNSAFE!!!
    
            return "welcome";
        }
        // place error on model when bad auth
        model.put("errorMessage", "Invalid Credentials");

        return "login";
    }
}
