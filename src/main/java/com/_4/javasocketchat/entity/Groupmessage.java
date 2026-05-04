package com._4.javasocketchat.entity;

import java.time.LocalDateTime;
import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Groupmessage {
    private String session_id;
    private LocalDateTime data;
    private Long id;
    private int sender_id;
    private String group_id;
    private String status;
    private String type;
    private String contents;
    private String etc;
    private String fuc;
}
