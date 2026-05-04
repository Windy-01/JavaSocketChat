package com._4.javasocketchat.entity;

import java.time.LocalDateTime;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class GroupMembersEntity {
    private int group_id;
    private int member_id;
    private String member_name;
    private String role;
    private String status;
    private LocalDateTime joined_at;
}
