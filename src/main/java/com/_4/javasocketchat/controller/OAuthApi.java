package com._4.javasocketchat.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com._4.javasocketchat.service.OAuthService;

@RestController
@RequestMapping("/api/login/oauth")
public class OAuthApi {
    private final OAuthService oAuthService;

    public OAuthApi(OAuthService oAuthService) {
        this.oAuthService = oAuthService;
    }

    @GetMapping("/github")
    public void githubLogin() {
        oAuthService.GithubLogin();
    }
}
