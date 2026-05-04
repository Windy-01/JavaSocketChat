package com._4.javasocketchat.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class SigninRequest{
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
}