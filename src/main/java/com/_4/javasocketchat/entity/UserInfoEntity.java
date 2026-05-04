package com._4.javasocketchat.entity;

import java.time.LocalDateTime;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class UserInfoEntity {
    private int id;
    private String username;
    private String email;
    private LocalDateTime created_at;
    private String status;
    private LocalDateTime last_login_at;
}
