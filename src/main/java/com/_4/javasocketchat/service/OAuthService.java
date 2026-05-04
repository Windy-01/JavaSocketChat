package com._4.javasocketchat.service;

import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Service;

@Service
public class OAuthService {
    private final ClientRegistrationRepository Repositorys;
    
    public OAuthService(ClientRegistrationRepository Repositorys) {
        this.Repositorys = Repositorys;
    }

    public void GithubLogin() {
        Repositorys.findByRegistrationId("github");
    }
}
