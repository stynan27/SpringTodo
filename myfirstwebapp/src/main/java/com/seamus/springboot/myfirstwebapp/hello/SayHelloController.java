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
        StringBuffer sb = new StringBuffer();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title>Some Test Title - Changed</title>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("An example HTML body. - changed");
        sb.append("</body>");
        sb.append("</html>");

        // // Throws an error by default as it is looking for View with the given string name...
        // return "Hello! What are your learning today?";

        return sb.toString();
    }
}
