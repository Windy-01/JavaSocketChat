package com._4.javasocketchat;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainPages {
    @GetMapping("/")
    public String main(){
        return "redirect:/login";
    }

    @GetMapping("/home/{uid}")
    @ResponseBody
    public String home(@PathVariable String uid){
        return uid;
    }
}
