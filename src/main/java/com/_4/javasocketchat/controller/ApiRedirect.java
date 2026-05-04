package com._4.javasocketchat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/redirect")
public class ApiRedirect {

    @GetMapping("/github")
    public String githubLogin() {
        return "redirect:/oauth2/authorization/github";
    }
    
}
