package com._4.javasocketchat.entity;

import java.time.LocalDateTime;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class GroupsEntity {
    private int group_id;
    private String name;
    private int owner_id;
    private String announce;
    private String status;
    private LocalDateTime created_at;
}
