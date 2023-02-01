package com.seamus.springboot.myfirstwebapp.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {
  
    // "say-hello" => "Hello! What are your learning today?"
    // http://localhost:8080/say-hello
    @RequestMapping("say-hello")
    @ResponseBody // Returns whatever as-is to the browser directly
    public String sayHello() {
        // Throws an error by default as it is looking for View with the given string name...
        return "Hello! What are your learning today?";
    }
}
