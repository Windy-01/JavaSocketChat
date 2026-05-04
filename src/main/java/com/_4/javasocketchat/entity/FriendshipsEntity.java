package com._4.javasocketchat.entity;

import java.time.LocalDateTime;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class FriendshipsEntity {
    private int id_a;
    private int id_b;
    private String status;
    private LocalDateTime created_at;
}
