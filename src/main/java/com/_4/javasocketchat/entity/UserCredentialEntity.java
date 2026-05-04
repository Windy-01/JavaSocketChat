package com._4.javasocketchat.entity;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UserCredentialEntity {
    private int id;
    private String password_hash;
}
