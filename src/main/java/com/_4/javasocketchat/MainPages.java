package com._4.javasocketchat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPages {
    @GetMapping("/home")
    public String Home(){
        return "redirect:/login";
    }
}
