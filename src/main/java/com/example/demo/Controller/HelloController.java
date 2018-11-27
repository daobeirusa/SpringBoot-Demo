package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {
    @RequestMapping("/")
    public String index(ModelMap map){
        map.addAttribute("host","https://github.com/daobeirusa");
        return "index";
    }
}
