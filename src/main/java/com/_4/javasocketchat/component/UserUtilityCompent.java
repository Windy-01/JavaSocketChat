package com._4.javasocketchat.component;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UserUtilityCompent {
    public int getUID() throws Exception{
        return Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
